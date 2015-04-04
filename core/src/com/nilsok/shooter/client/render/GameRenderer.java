package com.nilsok.shooter.client.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nilsok.shooter.client.GameOnClient;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.utils.Utils;

import java.util.Collections;

/**
 * Created by fimpen on 15-01-25.
 */
public class GameRenderer {

    private SpriteBatch batch;
    private Texture crosshairs;
    private Texture otherPlayersCrosshairs;
    private Texture target;

    private BitmapFont bitmapFont;

    private Sound gunshot;

    public GameRenderer() {
        batch = new SpriteBatch();
        crosshairs = new Texture("crosshair.png");
        otherPlayersCrosshairs = new Texture("crosshair_other.png");

        target = new Texture("target.png");
        bitmapFont = new BitmapFont();

        this.gunshot = Gdx.audio.newSound(Gdx.files.internal("21513^GUNSHOT.mp3"));
    }


    public void render(GameOnClient game) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (game.target != null) {
            batch.draw(target, game.target.getX() - (target.getWidth() / 2), Utils.gameYtoScreenY(game.target.getY()) - (target.getHeight() / 2));
        }



        int playerCount = 0;
        for (Player player : game.players.values()) {
            playerCount++;
            if (player.name.equals(game.getPlayerName())) {
                batch.draw(crosshairs, player.crosshairs.getX() - (crosshairs.getWidth() / 2), (Utils.gameYtoScreenY(player.crosshairs.getY()) - (crosshairs.getHeight() /2)));
            } else {
                batch.draw(otherPlayersCrosshairs, player.crosshairs.getX() - (crosshairs.getWidth() / 2), (Utils.gameYtoScreenY(player.crosshairs.getY()) - crosshairs.getHeight() /2));
                bitmapFont.draw(batch, player.name, player.crosshairs.getX(), Utils.gameYtoScreenY(player.crosshairs.getY() - (crosshairs.getHeight() / 2)));
            }
            bitmapFont.draw(batch, player.name + ": " + String.valueOf(player.score), Gdx.graphics.getWidth() - 100, (Gdx.graphics.getHeight()-40) - (playerCount*17));
        }

        if (game.isShooting() && game.getShootingDelay() == 1) {
            gunshot.play(1.0f);
        }

        bitmapFont.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 20, 20);

        batch.end();
    }

    public void dispose() {
        crosshairs.dispose();
        otherPlayersCrosshairs.dispose();
        target.dispose();
        bitmapFont.dispose();
        gunshot.dispose();
        batch.dispose();
    }
}
