package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Setting_Play_Screen extends ScreenAdapter {
    private final TankStars newGame;
    private final Sprite bg;

    public Setting_Play_Screen(TankStars game) {
        this.newGame = game;
        bg = new Sprite(new Texture(Gdx.files.internal("settings_in_play_screen.jpeg")));
        bg.setPosition(0,0);
        bg.setSize(844,475);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = screenX;
                int y = Gdx.graphics.getHeight() - screenY;
                if (x > 353 && x < 353 + 133 && y > 332 && y < 332 + 67 && button == Input.Buttons.LEFT) {
                    //newGame.setScreen(new Play_Screen(newGame));
                }
                if (x > 353 && x < 353 + 133 && y > 45 && y < 45 + 67 && button == Input.Buttons.LEFT) {
                    newGame.setScreen(new HomeScreen(newGame));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        newGame.batch.begin();
        newGame.batch.draw(bg, 0, 0);
        newGame.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
