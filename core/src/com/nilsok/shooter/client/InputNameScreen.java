package com.nilsok.shooter.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by fimpen on 15-02-07.
 */
public class InputNameScreen {

    private Stage stage;
    private SpriteBatch batch;

    public InputNameScreen() {
        this.batch = new SpriteBatch();
        this.stage = new Stage();

        BitmapFont font = new BitmapFont();
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(font, Color.BLACK, null, null, null);
        TextField textField = new TextField("Enter name", textFieldStyle);
        textField.setMessageText("Enter your name:");

        textField.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("text-field-event!");
                return true;
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.addActor(textField);
        stage.addActor(table);

    }

    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.stage.draw();
    }
}
