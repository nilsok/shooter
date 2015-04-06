package com.nilsok.shooter.model;

import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.command.Move;

/**
 * Created by fimpen on 15-01-24.
 */
public class Player {

    public String name;
    public GameEntity crosshairs;
    public int score;
    public MovementState movementState;
    public Direction direction;

    public Player() {}

    public Player(String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        this.crosshairs = new GameEntity(20, 20, 1 ,1);
        this.name = name;
        this.score = 0;
        this.direction = Direction.UP;
        this.movementState = MovementState.STILL;
    }

    public void startMoving(Direction direction) {
        this.movementState = MovementState.MOVING;
        this.direction = direction;
    }

    public void stop() {
        this.movementState = MovementState.STILL;
    }


    public void tick() {
        if (this.movementState == MovementState.MOVING) {
            switch (this.direction) {
                case UP:
                    this.crosshairs.setPosition(this.crosshairs.getX(), this.crosshairs.getY() - Const.MOVEMENT_SPEED);
                    break;
                case DOWN:
                    this.crosshairs.setPosition(this.crosshairs.getX(), this.crosshairs.getY() + Const.MOVEMENT_SPEED);
                    break;
                case LEFT:
                    this.crosshairs.setPosition(this.crosshairs.getX() - Const.MOVEMENT_SPEED, this.crosshairs.getY());
                    break;
                case RIGHT:
                    this.crosshairs.setPosition(this.crosshairs.getX() + Const.MOVEMENT_SPEED, this.crosshairs.getY());
                    break;
            }
        }
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
