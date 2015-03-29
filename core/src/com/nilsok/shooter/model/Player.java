package com.nilsok.shooter.model;

/**
 * Created by fimpen on 15-01-24.
 */
public class Player {

    public String name;
    public GameEntity crosshairs;
    public int score;

    public Player() {}

    public Player(String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }

        this.crosshairs = new GameEntity(20, 20, 1 ,1);
        this.name = name;
        this.score = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (score != player.score) return false;
        if (!crosshairs.equals(player.crosshairs)) return false;
        if (!name.equals(player.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + crosshairs.hashCode();
        result = 31 * result + score;
        return result;
    }
}
