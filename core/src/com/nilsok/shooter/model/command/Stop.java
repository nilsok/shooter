package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-04-04.
 */
public class Stop implements Command {

    private String playerName;
    private long frame;

    public Stop() {}

    public Stop(String playerName, long frame) {
        this.playerName = playerName;
        this.frame = frame;
    }


    @Override
    public String id() {
        return playerName;
    }

    @Override
    public long frame() {
        return frame;
    }
}
