package com.nilsok.shooter.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.nilsok.shooter.ClientApp;

import static com.badlogic.gdx.scenes.scene2d.ui.Label.*;


/**
 * Created by fimpen on 15-02-07.
 */
public class InputNameScreen {

    private Stage stage;


    public InputNameScreen(final ClientApp game) {
        this.stage = new Stage();

        BitmapFont font = new BitmapFont();
        Drawable background =  new NinePatchDrawable(new NinePatch(new Texture("text_field_bg.png")));
        Drawable cursor =  new NinePatchDrawable(new NinePatch(new Texture("cursor.png")));

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(font, Color.WHITE, cursor, null, background);
        LabelStyle labelStyle = new LabelStyle(font, Color.GRAY);
        final TextField textField = new TextField("", textFieldStyle);



        textField.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (event instanceof InputEvent) {
                    InputEvent inputEvent = (InputEvent) event;
                    if (inputEvent.getType() == InputEvent.Type.keyTyped &&  inputEvent.getKeyCode() == Input.Keys.ENTER) {
                        System.out.println("getMessageText():" + textField.getText());
                        game.startGame(textField.getText());
                        return true;
                    }
                }
                return false;
            }
        });


        Table table = new Table();
        table.setFillParent(true);
        table.add(new Label("Enter your name: ", labelStyle));
        table.add(textField).width(300);
//        table.setDebug(true);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }

    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.stage.draw();
    }
}
