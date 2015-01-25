package com.nilsok.shooter.model;

/**
 * Created by fimpen on 15-01-24.
 */
public class Player {

    public String name;
    public GameEntity crosshairs;
    public int score;

    public Player(String name) {
        this.crosshairs = new GameEntity(20, 20, 1 ,1);
        this.name = name;
        this.score = 0;
    }






}
