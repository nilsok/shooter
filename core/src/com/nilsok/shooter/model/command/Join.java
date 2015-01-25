package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-01-24.
 */
public class Join implements Command {

    public String name;
    public long frame;

    public Join() {}

    public Join(String name) {
        this.name = name;
    }

    @Override
    public String id() {
        return name;
    }

    @Override
    public long frame() {
        return frame;
    }
}
