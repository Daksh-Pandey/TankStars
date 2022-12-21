package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LoadingScreen extends ScreenAdapter {
    private TankStars newGame;
    private Sprite sprite;
    private BitmapFont font;

    public LoadingScreen(TankStars game) {
        this.newGame = game;
        sprite = new Sprite(new Texture(Gdx.files.internal("home_screen.jpg")));
        sprite.setPosition(0, 0);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font = new BitmapFont(Gdx.files.internal("Arial Black.fnt"),false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    newGame.setScreen(new HomeScreen(newGame));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        newGame.batch.begin();
        sprite.draw(newGame.batch);
        font.draw(newGame.batch, "Press Space to Continue", 225, 100);
        newGame.batch.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
