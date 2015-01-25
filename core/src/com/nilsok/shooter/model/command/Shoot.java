package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-01-24.
 */
public class Shoot implements Command {
    public String shooter;
    public int x, y;

    public Shoot() {}

    public Shoot(String shooter, int x, int y) {
        this.shooter = shooter;
        this.x = x;
        this.y = y;
    }

    @Override
    public String id() {
        return shooter;
    }

    @Override
    public long frame() {
        return 0;
    }
}
