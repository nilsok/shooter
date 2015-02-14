package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-01-31.
 */
public class TargetHit implements Command {
    public String player;

    public TargetHit() {}
    public TargetHit(String player) {
        this.player = player;
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public long frame() {
        return 0;
    }
}
