package com.nilsok.shooter.model;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by fimpen on 15-01-24.
 */
public class GameEntity {
    private int x, y;
    private Rectangle rectangle;

    public GameEntity(int startX, int startY, int width, int height) {
        this.rectangle = new Rectangle(startX, startY, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
