package com.nilsok.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.server.ServerNetworking;

/**
 * Created by fimpen on 15-01-25.
 */
public class ServerGame implements ApplicationListener {

    private Game game;
    private ServerNetworking networking;

    @Override
    public void create() {
        this.game = new Game();
        this.networking = new ServerNetworking(game);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        game.tick();
        networking.pushCommands();
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
