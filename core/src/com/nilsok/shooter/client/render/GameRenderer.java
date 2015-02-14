package com.nilsok.shooter.client.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.GameOnClient;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.utils.Utils;

/**
 * Created by fimpen on 15-01-25.
 */
public class GameRenderer {

    private SpriteBatch batch;
    private Texture crosshairs;
    private Texture otherPlayersCrosshairs;
    private Texture target;

    private BitmapFont bitmapFont;

    public GameRenderer() {
        batch = new SpriteBatch();
        crosshairs = new Texture("crosshair.png");
        otherPlayersCrosshairs = new Texture("crosshair_other.png");

        target = new Texture("target.png");
        bitmapFont = new BitmapFont();
    }


    public void render(Game game) {
        Gdx.gl.glClearColor(99, 88, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (game.target != null) {
            batch.draw(target, game.target.getX() - (target.getWidth() / 2), Utils.gameYtoScreenY(game.target.getY()) - (target.getHeight() / 2));
        }

        for (Player player : game.players.values()) {
            if (player.name.equals( ((GameOnClient) game).getPlayerName())) {
                batch.draw(crosshairs, player.crosshairs.getX() - (crosshairs.getWidth() / 2), (Utils.gameYtoScreenY(player.crosshairs.getY()) - crosshairs.getHeight() /2));
            } else {
                batch.draw(otherPlayersCrosshairs, player.crosshairs.getX() - (crosshairs.getWidth() / 2), (Utils.gameYtoScreenY(player.crosshairs.getY()) - crosshairs.getHeight() /2));
                bitmapFont.draw(batch, player.name, player.crosshairs.getX(), Utils.gameYtoScreenY(player.crosshairs.getY() - (crosshairs.getHeight() / 2)));
            }
            bitmapFont.draw(batch, player.name, 40, 40);
        }

        batch.end();
    }
}
