package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

class LoadingScreen implements Screen {

  Sprite sprite;

  private MyGdxGame game;

  public LoadingScreen(MyGdxGame game) {
    this.game = game;
    sprite = new Sprite(new Texture(Gdx.files.internal("home_screen.jpg")));
    sprite.setPosition(0, 0);
    sprite.setSize(800, 400);
  }

  @Override
  public void show() {}

  @Override
  public void render(float delta) {
    // TODO Auto-generated method stub
    game.batch.begin();
    sprite.draw(game.batch);
    game.batch.end();
  }

  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub

  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }

  @Override
  public void resume() {
    // TODO Auto-generated method stub

  }

  @Override
  public void hide() {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    sprite.getTexture().dispose();
    // TODO Auto-generated method stub

  }
}
