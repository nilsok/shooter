package com.nilsok.shooter.utils;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.nilsok.shooter.model.Direction;
import com.nilsok.shooter.model.GameEntity;
import com.nilsok.shooter.model.MovementState;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.model.command.*;

import java.util.ArrayList;

/**
 * Created by fimpen on 15-01-25.
 */
public final class Utils {
    private Utils() {}

    public static int gameYtoScreenY(int gameY) {
        return Gdx.graphics.getHeight() - gameY;
    }

    public static void registerCommandsWithKryo(Kryo kryo) {
        kryo.register(Join.class);
        kryo.register(Leave.class);
        kryo.register(Shoot.class);
        kryo.register(UpdatePosition.class);
        kryo.register(Move.class);
        kryo.register(Stop.class);
        kryo.register(ShowTarget.class);
        kryo.register(TargetHit.class);
        kryo.register(GameState.class);
        kryo.register(Player.class);
        kryo.register(GameEntity.class);
        kryo.register(ArrayList.class);
        kryo.register(Direction.class);
        kryo.register(MovementState.class);
    }


}
