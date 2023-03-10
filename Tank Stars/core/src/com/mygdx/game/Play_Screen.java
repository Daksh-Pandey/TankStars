package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Play_Screen extends ScreenAdapter {
    private final TankStars newGame;
    private final Sprite settings;
    private final Sprite playscreen;
    private final TiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final World world;
    private final BodyDef bodyDef;

    public Play_Screen(TankStars game) {
        this.newGame = game;
        playscreen = new Sprite(new Texture(Gdx.files.internal("bg.png")));
        playscreen.setPosition(0, 0);
        playscreen.setSize(844, 475);

        settings = new Sprite(new Texture(Gdx.files.internal("settings_icon.png")));
        settings.setSize(30, 30);
        settings.setPosition(10, 430);

        TiledMap map = new TmxMapLoader().load("terrain1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 844, 475);
        camera.update();

        world = new World(new Vector2(0, -10), true);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = screenX;
                int y = Gdx.graphics.getHeight() - screenY;
                if (x > settings.getX() && x < settings.getX() + settings.getWidth() && y > settings.getY() && y < settings.getY() + settings.getHeight() && button == Input.Buttons.LEFT) {
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

        renderer.setView(camera);
        renderer.render();

        camera.update();

        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);


    }

    public void tank() {
        MapObject tank = new MapObject();
        tank.getProperties().put("tank", "tank");
        MapObjects objects = new MapObjects();
        objects.add(tank);

        
    }
}
