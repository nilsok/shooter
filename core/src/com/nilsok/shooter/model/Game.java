package com.nilsok.shooter.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.AtomicQueue;
import com.badlogic.gdx.utils.IntMap;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.command.Join;
import com.nilsok.shooter.model.command.Leave;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.UpdatePosition;


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

        private int framesSinceLastTargetHit = 0;

        public Game() {
            target = null;
            players = new HashMap<String, Player>();
            commandQueue = new AtomicQueue<Command>(20);
            framesSinceLastTargetHit = 0;
        }


        public void addCommand(Command c) {
            this.commandQueue.put(c);
        }


        public void tick() {
            frame++;

            Command nextCommand;
            while ((nextCommand = commandQueue.poll()) != null) {

                if (nextCommand instanceof Shoot) {
                    checkIfHit((Shoot) nextCommand);
                }

                if (nextCommand instanceof UpdatePosition) {
                    UpdatePosition position = (UpdatePosition) nextCommand;
                    players.get(position.id()).crosshairs.setPosition(position.x, position.y);
                }

                if (nextCommand instanceof Join) {
                    Join join = (Join) nextCommand;
                    players.put(join.name, new Player(join.name));
                }

                if (nextCommand instanceof Leave) {
                    Leave leave = (Leave) nextCommand;
                    players.remove(leave.name);
                }

            }

            if (this.target == null) {
                checkAndSpawnTarget();
            } else if (this.target.isHit) {
                this.target = null;
                framesSinceLastTargetHit= 0;
            }

            framesSinceLastTargetHit++;
        }

    private void checkAndSpawnTarget() {
        if (framesSinceLastTargetHit > Const.TARGET_SPAWN_INTERVAL) {
            target = new Target();
        }
    }

    private void checkIfHit(Shoot shot) {
        if (this.target != null) {
            target.isHit = true;
            players.get(shot.id()).score++;
        }
    }

}
