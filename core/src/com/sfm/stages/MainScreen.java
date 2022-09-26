package com.sfm.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.service.FontService;
import com.sfm.SacuraFortune;
import com.sfm.service.TextureService;

public class MainScreen implements Screen {

    private final OrthographicCamera mCamera;
    private final Viewport mViewport;
    private final Viewport backViewport;
    private Stage mActiveStage;
    private GameStage gameStage;
    private BackStage backStage;

    public MainScreen() {
        TextureService.init();
        FontService.init();
        mCamera = new OrthographicCamera();
        backViewport = new StretchViewport(SacuraFortune.SCREEN_WIDTH, SacuraFortune.SCREEN_HEIGHT);
        mViewport = new FitViewport(SacuraFortune.SCREEN_WIDTH, SacuraFortune.SCREEN_HEIGHT, mCamera);

        backStage=new BackStage(backViewport);
        gameStage=new GameStage(mViewport);
        mActiveStage = gameStage;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        if(!TextureService.isReady())return;
//        mCamera.update();
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 255f); // просто цвет фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backViewport.apply();
        backStage.act(delta);
        backStage.draw();

        mViewport.apply();
        mActiveStage.act(delta);
        mActiveStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        backViewport.update(width, height, true);
//        backViewport.setScreenWidth(backViewport.getScreenWidth()*height/backViewport.getScreenHeight());
        mViewport.update(width, height, true);
//        mViewport.setScreenWidth(mViewport.getScreenWidth()*height/mViewport.getScreenHeight());
        backViewport.update(width, height, true);

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
