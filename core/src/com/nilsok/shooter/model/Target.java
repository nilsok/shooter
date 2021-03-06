package com.nilsok.shooter.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.nilsok.shooter.model.command.Shoot;

/**
 * Created by fimpen on 15-01-24.
 */
public class Target extends GameEntity {

    public boolean isHit;
    public Circle circle;


    public Target(int x, int y) {
        super(x, y, 100, 100);
        this.circle = new Circle(x, y, 110);
    }


    public boolean shotHit(Shoot shot) {

        System.out.println("shot.x: " + shot.x);
        System.out.println("shot.y: " + shot.y);

        System.out.println("target.x: " + this.getX());
        System.out.println("target.y: " + this.getY());

        return circle.contains(shot.x, shot.y);
    }
}
