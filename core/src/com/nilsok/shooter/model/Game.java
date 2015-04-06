package com.nilsok.shooter.model;

import com.badlogic.gdx.utils.AtomicQueue;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.model.command.*;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by fimpen on 15-01-24.
 */
public class Game {

        public Target target;
        private long frame = 0;

        public Map<String, Player> players;
        private AtomicQueue<Command> commandQueue;

        public Game() {
            target = null;
            players = new HashMap<String, Player>();
            commandQueue = new AtomicQueue<Command>(20);
        }


        public void addCommand(Command c) {
            this.commandQueue.put(c);
        }


        public void tick() {
            frame++;
            Command nextCommand;
            while ((nextCommand = commandQueue.poll()) != null) {
                executeCommand(nextCommand, frame);
            }

            for (Player player : this.players.values()) {
                player.tick();
            }

        }

    protected void executeCommand(Command nextCommand, long frame) {


        if (nextCommand instanceof Move) {
            Move move = (Move) nextCommand;
            Player player = players.get(move.id());
            if (player != null) {
                player.startMoving(move.getDirection());
                pushToClientIfServer(move);
            }
        }

        if (nextCommand instanceof Stop) {
            Player player = players.get(nextCommand.id());
            if (player != null) {
                player.stop();
                pushToClientIfServer(nextCommand);
            }
        }


        if (nextCommand instanceof Join) {
            Join join = (Join) nextCommand;
            players.put(join.name, new Player(join.name));
            pushToClientIfServer(nextCommand);
        }

        if (nextCommand instanceof Leave) {
            Leave leave = (Leave) nextCommand;
            players.remove(leave.name);
            pushToClientIfServer(nextCommand);
        }


    }

    protected void pushToClientIfServer(Command nextCommand) {
        // Default does nothing
    }

}
