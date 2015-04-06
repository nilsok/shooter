package com.nilsok.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.nilsok.shooter.client.ClientNetworking;
import com.nilsok.shooter.client.InputNameScreen;
import com.nilsok.shooter.client.render.GameRenderer;
import com.nilsok.shooter.client.GameOnClient;
import com.nilsok.shooter.model.Direction;
import com.nilsok.shooter.model.command.*;


import java.io.IOException;
import java.util.UUID;

public class ClientApp extends ApplicationAdapter {

    public enum State {
        ENTERING_NAME, PLAYING
    }

    GameOnClient game;
    GameRenderer renderer;
    ClientNetworking networking;
    InputNameScreen inputNameScreen;
    String playerName;
    State state;

	@Override
	public void create () {
        renderer = new GameRenderer();
        inputNameScreen = new InputNameScreen(this);
        state = State.ENTERING_NAME;
	}

    @Override
	public void render () {

        if (this.state == State.ENTERING_NAME) {
            inputNameScreen.render();
        } else { // State == PLAYING
            game.tick();
            renderer.render(game);
            networking.sendCommand(new GameState(game));
        }
	}


    public void startGame(String playerName) {
        this.playerName = playerName;
        game = new GameOnClient(playerName);
        try {
            networking = new ClientNetworking(game);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CANNOT CONNECT TO SERVER!");
        }
        networking.sendCommand(new Join(playerName));
        configureGameInputHandling();
        this.state = State.PLAYING;
    }


    private void configureGameInputHandling() {


        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean keyDown(int keycode) {

                if (keycode == Input.Keys.UP) {
                    networking.sendCommand(new Move(playerName, Direction.UP));
                    return true;
                } else if (keycode == Input.Keys.DOWN) {
                    networking.sendCommand(new Move(playerName, Direction.DOWN));
                    return true;
                } else if (keycode == Input.Keys.LEFT) {
                    networking.sendCommand(new Move(playerName, Direction.LEFT));
                    return true;
                }  else if (keycode == Input.Keys.RIGHT) {
                    networking.sendCommand(new Move(playerName, Direction.RIGHT));
                    return true;
                } else if (keycode == Input.Keys.SPACE) {
                    networking.sendCommand(new Shoot(playerName,
                            game.players.get(playerName).crosshairs.getX(),
                            game.players.get(playerName).crosshairs.getY()));
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                networking.sendCommand(new Stop(playerName));
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

    @Override
    public void dispose() {
        if (networking != null) {
            networking.sendCommand(new Leave(this.playerName));
        }
        renderer.dispose();
        System.out.print("dispose()");
    }

}
