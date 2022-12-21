package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Play_Screen extends ScreenAdapter {
    private TankStars newGame;
    private OrthographicCamera camera;
    private Sprite settings;
    private Sprite playscreen;
    private int width = 844;
    private int height = 475;
    private World world;
    private Body tank1;
    private Body tank2;
    private Box2DDebugRenderer b2dr;

    public Play_Screen(TankStars game) {
        this.newGame = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        world = new World(new Vector2(0, -10), true);

        playscreen = new Sprite(new Texture(Gdx.files.internal("playing_background.jpeg")));
        playscreen.setPosition(0, 0);
        playscreen.setSize(844, 475);

        settings = new Sprite(new Texture(Gdx.files.internal("settings_icon.png")));
        settings.setSize(30,30);
        settings.setPosition(10,430);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = screenX;
                int y = Gdx.graphics.getHeight() - screenY;
                if (x > settings.getX() && x < settings.getX() + settings.getWidth()
                        && y > settings.getY() && y < settings.getY() + settings.getHeight()
                        && button == Input.Buttons.LEFT) {
                    newGame.setScreen(new Setting_Play_Screen(newGame));
                }
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {
        newGame.batch.begin();

        newGame.batch.draw(playscreen, 0, 0);
        newGame.batch.draw(settings, 10, 430);

        newGame.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
