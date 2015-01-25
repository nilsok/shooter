package com.nilsok.shooter.headless;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.nilsok.shooter.ServerGame;

// import com.badlogic.gdx.backends.headless;

/**
 * Created by fimpen on 15-01-18.
 */
public class HeadlessLauncher {
    public static void main (String[] arg) {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new ServerGame(), config);
    }
}
