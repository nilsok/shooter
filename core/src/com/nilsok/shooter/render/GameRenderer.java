package com.nilsok.shooter.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.utils.Utils;

/**
 * Created by fimpen on 15-01-25.
 */
public class GameRenderer {

    private SpriteBatch batch;
    private Texture crosshairs;
    private Texture target;

    private BitmapFont bitmapFont;

    public GameRenderer() {
        batch = new SpriteBatch();
        crosshairs = new Texture("crosshair.png");
        target = new Texture("target.png");
        bitmapFont = new BitmapFont();
    }


    public void render(Game game) {
        Gdx.gl.glClearColor(99, 88, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (game.target != null) {
            batch.draw(target, game.target.getX(), Utils.gameYtoScreenY(game.target.getY()));
        }

        for (Player player : game.players.values()) {
            batch.draw(crosshairs, player.crosshairs.getX(), Utils.gameYtoScreenY(player.crosshairs.getY()));
            bitmapFont.draw(batch, player.name, 40, 40);
        }

        batch.end();
    }
}
