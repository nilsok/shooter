package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;
import com.nilsok.shooter.model.Direction;

/**
 * Created by fimpen on 15-04-04.
 */
public class Move implements Command {

    private String playerName;
    private Direction direction;
    private long frame;

    public Move() {}

    public Move(String playerName, Direction direction, long frame) {
        this.playerName = playerName;
        this.direction = direction;
        this.frame = frame;
    }

    @Override
    public String id() {
        return playerName;
    }

    @Override
    public long frame() {
        return this.frame;
    }

    public Direction getDirection() {
        return direction;
    }
}
