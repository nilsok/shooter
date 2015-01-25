package com.nilsok.shooter.utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by fimpen on 15-01-25.
 */
public final class Utils {
    private Utils() {}

    public static int gameYtoScreenY(int gameY) {
        return Gdx.graphics.getHeight() - gameY;
    }
}
