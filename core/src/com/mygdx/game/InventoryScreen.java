package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class InventoryScreen extends ScreenAdapter {
    private TankStars newGame;
    private Stage stage;
    private TextButton play;
    private BlueHoop blueHoop;
    private Sprite background;
    private Sprite Abrams;
    private Sprite Coalition;
    private Sprite Spectre;
    private TextButton back;
    private BitmapFont PlayerChoose;
    ShapeRenderer shapeRenderer;

    public InventoryScreen(TankStars game) {
        this.newGame = game;
        stage = new Stage(new ScreenViewport());
        shapeRenderer = new ShapeRenderer();

        play = new TextButton("Play", newGame.gameSkin, "small");
        play.setWidth(175);
        play.setPosition(600,120);
        play.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                newGame.setScreen(new Play_Screen(newGame, "Abrams", "Coalition"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(play);

        back = new TextButton("<",newGame.gameSkin, "small");
        back.setSize(40,40);
        back.setPosition(10,Gdx.graphics.getHeight()-60);
        back.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                newGame.setScreen(new HomeScreen(newGame));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(back);

        blueHoop = new BlueHoop();
        stage.addActor(blueHoop);

        PlayerChoose = new BitmapFont(Gdx.files.internal("Arial White.fnt"),false);

        background = new Sprite(new Texture(Gdx.files.internal("main_screen.png")));
        background.setPosition(0, 0);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Abrams = new Sprite(new Texture(Gdx.files.internal("Abrams_transparent.png")));
        Coalition = new Sprite(new Texture(Gdx.files.internal("Coalition_transparent.png")));
        Spectre = new Sprite(new Texture(Gdx.files.internal("Spectre_new.png")));
        Abrams.setSize(90, 39);
        Coalition.setSize(90, 39);
        Spectre.setSize(90, 39);
        Abrams.setPosition(550, Gdx.graphics.getHeight() / 2);
        Coalition.setPosition(650, Gdx.graphics.getHeight() / 2);
        Spectre.setPosition(750, Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(blueHoop);
    }

    @Override
    public void render(float delta) {
        newGame.batch.begin();
        newGame.batch.draw(background, 0, 0);
        Abrams.draw(newGame.batch);
        Coalition.draw(newGame.batch);
        Spectre.draw(newGame.batch);
        newGame.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

class BlueHoop extends Actor {
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("blue_circle.png")));

    public BlueHoop(){
        setBounds(540, Gdx.graphics.getHeight()/2 - 20, sprite.getWidth(), sprite.getHeight());

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT){
                    if (sprite.getX() < 740) {
                        MoveByAction moveByAction = new MoveByAction();
                        moveByAction.setAmount(100f, 0f);
                        moveByAction.setDuration(0.1f);
                        BlueHoop.this.addAction(moveByAction);
                    }
                    else {
                        return true;
                    }
                }
                if (keycode == Input.Keys.LEFT) {
                    if (sprite.getX() > 540) {
                        MoveByAction moveByAction = new MoveByAction();
                        moveByAction.setAmount(-100f, 0f);
                        moveByAction.setDuration(0.1f);
                        BlueHoop.this.addAction(moveByAction);
                    } else {
                        return true;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void positionChanged(){
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.draw(batch);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }
}