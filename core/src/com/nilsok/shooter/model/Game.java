package com.nilsok.shooter.model;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.AtomicQueue;
import com.badlogic.gdx.utils.IntMap;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.command.Join;
import com.nilsok.shooter.model.command.Leave;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.UpdatePosition;
import com.nilsok.shooter.server.ServerNetworking;


import java.util.HashMap;
import java.util.List;
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
                executeCommand(nextCommand);
            }
        }

    protected void executeCommand(Command nextCommand) {

        if (nextCommand instanceof UpdatePosition) {
            UpdatePosition position = (UpdatePosition) nextCommand;
            if (!players.containsKey(position.id())) {
                players.put(position.id(), new Player(position.id()));
            }
            players.get(position.id()).crosshairs.setPosition(position.x, position.y);
            pushToClientIfServer(nextCommand);
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
