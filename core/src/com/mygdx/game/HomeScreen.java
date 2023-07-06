package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HomeScreen extends ScreenAdapter {
    private  TankStars newGame;
    private  Sprite background;
    private Stage stage;
    private Table table;
    private TextButton vsComp;
    private TextButton vsFriend;
    private TextButton savedGames;
    private Sprite setting;
    float screenWidth = 100;
    float screenHeight = 50;
    float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

    public HomeScreen(TankStars game) {
        this.newGame = game;
        stage = new Stage(new ScreenViewport());

//        table = new Table();
//        table.setWidth(30);
//        table.align(Align.right | Align.top);
//        table.setPosition(, (float) (0.75 * stage.getHeight()));

        background = new Sprite(new Texture(Gdx.files.internal("main_screen.png")));
        background.setPosition(0, 0);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        vsComp = new TextButton("vsComp", newGame.gameSkin, "small");
        vsComp.setWidth(150);
        vsComp.setPosition(600,275);
        vsComp.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                newGame.setScreen(new InventoryScreen(newGame));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(vsComp);

        vsFriend = new TextButton("vsFriend", newGame.gameSkin, "small");
        vsFriend.setWidth(150);
        vsFriend.setPosition(600,200);
        vsFriend.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                newGame.setScreen(new InventoryScreen(newGame));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(vsFriend);

        savedGames = new TextButton("Resume", newGame.gameSkin, "small");
        savedGames.setWidth(150);
        savedGames.setPosition(600,125);
        savedGames.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                newGame.setScreen(new Saved_Screen(newGame));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(savedGames);

//        setting = new Sprite(new Texture(Gdx.files.internal("settings_icon.png")));
//        setting.setPosition(10,430);
//        setting.setSize(30,30);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);/* {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = screenX;
                int y = Gdx.graphics.getHeight() - screenY;
                if (x > vsComp.getX() && x < vsComp.getX() + vsComp.getWidth() && y > vsComp.getY() && y < vsComp.getY() + vsComp.getY() + vsComp.getHeight() && button == Input.Buttons.LEFT) {
                    newGame.setScreen(new InventoryScreen(newGame));
                }
                if (x > vsFriend.getX() && x < vsFriend.getX() + vsFriend.getWidth() && y > vsFriend.getY() && y < vsFriend.getY() + vsFriend.getHeight() && button == Input.Buttons.LEFT) {
                    newGame.setScreen(new InventoryScreen(newGame));
                }
                if (x > setting.getX() && x < setting.getX() + setting.getWidth() && y > setting.getY() && y < setting.getY() + setting.getHeight() && button == Input.Buttons.LEFT) {
                    newGame.setScreen(new Home_Setting(newGame));
                }
                if (x > savedGames.getX() && x < savedGames.getX() + savedGames.getWidth() && y > savedGames.getY() && y < savedGames.getY() + savedGames.getHeight() && button == Input.Buttons.LEFT) {
                    newGame.setScreen(new Saved_Screen(newGame));
                }

                return true;
            }
        });*/
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        newGame.batch.begin();
        background.draw(newGame.batch);
//        newGame.batch.draw(background, 0, 0);
//        newGame.batch.draw(vsComp, 615, 300);
//        newGame.batch.draw(vsFriend, 615, 200);
//        newGame.batch.draw(savedGames, 615, 100);
//        newGame.batch.draw(setting, 10, 700);
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
