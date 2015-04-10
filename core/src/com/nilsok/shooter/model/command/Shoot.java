package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-01-24.
 */
public class Shoot implements Command {
    private String shooter;
    public int x, y;
    public long frame;

    public Shoot() {}

    public Shoot(String shooter, int x, int y, long frame) {
        this.shooter = shooter;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    @Override
    public String id() {
        return shooter;
    }

    @Override
    public long frame() {
        return frame;
    }
}
