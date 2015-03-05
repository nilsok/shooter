package com.nilsok.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.nilsok.shooter.client.ClientNetworking;
import com.nilsok.shooter.client.InputNameScreen;
import com.nilsok.shooter.client.render.GameRenderer;
import com.nilsok.shooter.client.GameOnClient;
import com.nilsok.shooter.model.command.Join;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.UpdatePosition;


import java.io.IOException;
import java.util.UUID;

public class ClientApp extends ApplicationAdapter {

    GameOnClient game;
    GameRenderer renderer;
    ClientNetworking networking;
    InputNameScreen inputNameScreen;
    String playerName;

	@Override
	public void create () {
        playerName = UUID.randomUUID().toString();
        game = new GameOnClient(playerName);
        renderer = new GameRenderer();
        //inputNameScreen = new InputNameScreen();

        try {
            networking = new ClientNetworking(game);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CANNOT CONNECT TO SERVER!");
        }

        configureInputHandling();


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
                if (!game.isShooting()) {
                    networking.sendCommand(new Shoot(playerName, screenX, screenY));
                }
                return true;
            }
        });
    }

    public String getPlayerName() {
        return this.playerName;
    }

    private int getMouseX() {
        return Gdx.input.getX();
    }

    private int getMouseY() {
        return Gdx.input.getY();
    }
}
