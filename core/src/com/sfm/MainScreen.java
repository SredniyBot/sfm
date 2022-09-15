package com.sfm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {

    private final OrthographicCamera mCamera;
    private final Viewport mViewport;
    private Stage mActiveStage;
    private GameStage gameStage;

    public MainScreen() {
        TextureService.init();
        FontService.init();
        mCamera = new OrthographicCamera();
        mViewport = new FitViewport(SacuraFortune.SCREEN_WIDTH, SacuraFortune.SCREEN_HEIGHT, mCamera);
        gameStage=new GameStage(mViewport);
        mActiveStage = gameStage;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        if(!TextureService.isReady())return;
        mCamera.update();

        Gdx.gl.glClearColor(65 / 255f, 65 / 255f, 65 / 255f, 1f); // просто цвет фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mActiveStage.act(delta);
        mActiveStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height, true);
        mViewport.setScreenWidth(mViewport.getScreenWidth()*height/mViewport.getScreenHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        mActiveStage.dispose();
        TextureService.dispose();
    }
}
