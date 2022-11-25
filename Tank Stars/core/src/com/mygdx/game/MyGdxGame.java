package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {

  SpriteBatch batch;
  private Camera cam;
  private Viewport vp;

  @Override
  public void create() {
    cam = new PerspectiveCamera();
    vp = new FitViewport(800, 480, cam);
    batch = new SpriteBatch();
    this.setScreen(new LoadingScreen(this));
    // setScreen(lds);
  }

  @Override
  public void resize(int width, int height) {
    vp.update(width, height);
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    batch.dispose();
  }
}
