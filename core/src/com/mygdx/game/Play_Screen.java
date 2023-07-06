package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.HashMap;

public class Play_Screen extends ScreenAdapter {

    private String gametank1, gametank2;
    private Body tankBody1, tankBody2;
    private HashMap<String, Sprite> tankImages;
    private HashMap<Sprite, Body> tanksInfo;
    private static final int WORLD_WIDTH = 100;
    private static final int WORLD_HEIGHT = 50;
    private TankStars newGame;
    private Sprite settings;
    private Sprite playscreen;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private World world;
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();

    public Play_Screen(TankStars game, String Tank1, String Tank2) {
        this.newGame = game;
        renderer = new Box2DDebugRenderer();

        gametank1 = Tank1; gametank2 = Tank2;

        tankImages = new HashMap<String, Sprite>();
        tanksInfo = new HashMap<Sprite, Body>();

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT * (WORLD_HEIGHT / WORLD_WIDTH));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.update();

        Sprite tank1 = new Sprite(new Texture(Gdx.files.internal("Abrams_transparent.png")));
        tank1.setSize(8, 4);

        Sprite tank2 = new Sprite(new Texture(Gdx.files.internal("Coalition_transparent.png")));
        tank2.setSize(8, 4);

        Sprite tank3 = new Sprite(new Texture(Gdx.files.internal("Spectre_new.png")));
        tank3.setSize(8, 4);

        tankImages.put("Abrams", tank1);
        tankImages.put("Coalition", tank2);
        tankImages.put("Spectre", tank3);

        world = new World(new Vector2(1, -10), true);

        tankBody1 = createTank(Tank1, 1);
        tankBody2 = createTank(Tank2, 2);

        tanksInfo.put(tankImages.get(Tank1), tankBody1);
        tanksInfo.put(tankImages.get(Tank2), tankBody2);

        Sprite gameTank1 =  tankImages.get(Tank1);
        gameTank1.setPosition(tanksInfo.get(gameTank1).getPosition().x - 2.5f,
                                tanksInfo.get(gameTank1).getPosition().y - 1.25f);
        Sprite gameTank2 =  tankImages.get(Tank2);
        gameTank2.setPosition(tanksInfo.get(gameTank2).getPosition().x - 2.5f,
                tanksInfo.get(gameTank2).getPosition().y - 1.25f);

        playscreen = new Sprite(new Texture(Gdx.files.internal("bg.png")));
        playscreen.setPosition(0, 0);
        playscreen.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        settings = new Sprite(new Texture(Gdx.files.internal("settings_icon.png")));
        settings.setSize(5, 5);
        settings.setPosition(3, 45);

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(new Vector2(camera.viewportWidth / 2, 0));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth / 2, 10f);
        groundBody.createFixture(groundBox, 1f);

        groundBox.dispose();

//        TiledMap map = new TmxMapLoader().load("terrain1.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map);
//
//
//        bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(0, 0);


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        camera.update();
        moveTanks();
        Sprite gameTank1 =  tankImages.get(gametank1);
        gameTank1.setPosition(tanksInfo.get(gameTank1).getPosition().x - 2.5f,
                tanksInfo.get(gameTank1).getPosition().y - 1.25f);
        Sprite gameTank2 =  tankImages.get(gametank2);
        gameTank2.setPosition(tanksInfo.get(gameTank2).getPosition().x - 2.5f,
                tanksInfo.get(gameTank2).getPosition().y - 1.25f);

        newGame.batch.setProjectionMatrix(camera.combined);
        newGame.batch.begin();
        playscreen.draw(newGame.batch);
        settings.draw(newGame.batch);
        tankImages.get(gametank1).draw(newGame.batch);
        tankImages.get(gametank2).draw(newGame.batch);
        newGame.batch.end();

        newGame.shapeRenderer.setProjectionMatrix(camera.combined);
        newGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        newGame.shapeRenderer.setColor(Color.BROWN);
        newGame.shapeRenderer.rect(0, 0, WORLD_WIDTH, 10);
        newGame.shapeRenderer.end();
//        renderer.setView(camera);
        renderer.render(world, camera.combined);

        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void hide() {


    }

    @Override
    public void dispose(){
        world.dispose();
    }

    public Body createTank(String tankName, int num) {
        Body tank;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        def.fixedRotation = true;
        if (num == 1){
            def.position.set(10, 10);
        }
        else if (num == 2){
            def.position.set(80, 10);
            tankImages.get(tankName).flip(true, false);
        }
        tank = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(2.5f, 1.25f);
        tank.createFixture(poly, 1f);
        poly.dispose();

        return tank;
    }

    public void moveTanks(){
        if (Gdx.input.isButtonPressed(Input.Keys.A)){
            tankBody1.setLinearVelocity(-5, tankBody1.getLinearVelocity().y);
        }
        if (Gdx.input.isButtonPressed(Input.Keys.D)){
            tankBody1.setLinearVelocity(5, tankBody1.getLinearVelocity().y);
        }
        if (Gdx.input.isButtonPressed(Input.Keys.LEFT)){
            tankBody2.setLinearVelocity(-5, tankBody1.getLinearVelocity().y);
        }
        if (Gdx.input.isButtonPressed(Input.Keys.RIGHT)){
            tankBody2.setLinearVelocity(5, tankBody1.getLinearVelocity().y);
        }
    }
}
