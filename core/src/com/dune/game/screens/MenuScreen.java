package com.dune.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dune.game.core.Assets;

public class MenuScreen extends AbstractScreen {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public MenuScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        createButtonForMenu();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void dispose() {
    }

    public void createButtonForMenu(){
        stage = new Stage(ScreenManager.getInstance().getViewport(), ScreenManager.getInstance().getBatch());
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());
        BitmapFont font14 = Assets.getInstance().getAssetManager().get("fonts/font14.ttf");
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(skin.getDrawable("smButton"), null, null, font14);

        final TextButton startBtn = new TextButton("Start", textButtonStyle);
        startBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
            }
        });
        final TextButton exitBtn = new TextButton("Exit", textButtonStyle);
        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        startBtn.setPosition(250, 150);
        exitBtn.setPosition(930, 150);
        stage.addActor(startBtn);
        stage.addActor(exitBtn);
        skin.dispose();
    }
}