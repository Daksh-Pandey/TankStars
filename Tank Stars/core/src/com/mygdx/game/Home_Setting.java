package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Home_Setting extends ScreenAdapter {
    private final TankStars newGame;
    private final Sprite bg;
    private final Sprite back;

    public Home_Setting(TankStars game) {
        this.newGame = game;
        bg = new Sprite(new Texture(Gdx.files.internal("settings_screen.jpeg")));
        bg.setPosition(0,0);
        bg.setSize(844,475);

        back = new Sprite(new Texture(Gdx.files.internal("arrow.png")));
        back.setPosition(10,430);
        back.setSize(30,30);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = screenX;
                int y = Gdx.graphics.getHeight() - screenY;
                if (x > back.getX() && x < back.getX() + back.getWidth() && y > back.getY() && y < back.getY() + back.getY() + back.getHeight() && button == Input.Buttons.LEFT) {
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
        newGame.batch.draw(back, 10, 430);
        newGame.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
