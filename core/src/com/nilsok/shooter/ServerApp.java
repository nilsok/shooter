package com.nilsok.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.GameOnServer;
import com.nilsok.shooter.server.ServerNetworking;

/**
 * Created by fimpen on 15-01-25.
 */
public class ServerApp implements ApplicationListener {

    private GameOnServer game;
    private ServerNetworking networking;

    @Override
    public void create() {
        this.game = new GameOnServer();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        game.tick();
        game.getNetworking().pushCommands();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
