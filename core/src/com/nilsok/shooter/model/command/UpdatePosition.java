package com.nilsok.shooter.model.command;

import com.nilsok.shooter.Command;

/**
 * Created by fimpen on 15-01-24.
 */
public class UpdatePosition implements Command {

    public String name;
    public int x, y;
    public long frame;

    public UpdatePosition() {}

    public UpdatePosition(String name, int mouseX, int mouseY) {
        this.name = name;
        this.x = mouseX;
        this.y = mouseY;
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
