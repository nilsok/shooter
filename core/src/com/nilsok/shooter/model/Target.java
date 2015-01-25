package com.nilsok.shooter.model;

/**
 * Created by fimpen on 15-01-24.
 */
public class Target extends GameEntity {

    public boolean isHit;

    public Target() {
        super(80, 80, 10, 10);
        this.isHit = false;
    }




}
