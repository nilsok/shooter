package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-04-04.
 */
public class Stop implements Command {

    private String playerName;

    public Stop() {}

    public Stop(String playerName) {
        this.playerName = playerName;
    }


    @Override
    public String id() {
        return playerName;
    }

    @Override
    public long frame() {
        return 0;
    }
}
