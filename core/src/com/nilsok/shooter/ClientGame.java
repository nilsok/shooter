package com.nilsok.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.nilsok.shooter.client.ClientNetworking;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.model.command.Join;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.UpdatePosition;
import com.nilsok.shooter.render.GameRenderer;

import java.io.IOException;
import java.util.UUID;

public class ClientGame extends ApplicationAdapter {

    Game game;
    GameRenderer renderer;
    ClientNetworking networking;
    String playerName;

	@Override
	public void create () {
        game = new Game();
        renderer = new GameRenderer();

        try {
            networking = new ClientNetworking(game);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CANNOT CONNECT TO SERVER!");
        }

        configureInputHandling();
        playerName = networking.getIp();

        networking.sendCommand(new Join(playerName));
	}

    @Override
	public void render () {
        game.tick();
        renderer.render(game);
	}

    private void configureInputHandling() {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                networking.sendCommand(new UpdatePosition(playerName, screenX, screenY));
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                networking.sendCommand(new Shoot(playerName, screenX, screenY));
                return true;
            }
        });
    }

    private int getMouseX() {
        return Gdx.input.getX();
    }

    private int getMouseY() {
        return Gdx.input.getY();
    }
}
