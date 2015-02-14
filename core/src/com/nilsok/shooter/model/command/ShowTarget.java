package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-01-31.
 */
public class ShowTarget implements Command {
    public int x, y;

    public ShowTarget() {}

    public ShowTarget(int x, int y) {
        this.x = x;
        this.y = y;
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
