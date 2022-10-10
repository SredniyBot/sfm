package com.sfm.main.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;

public abstract class StretchedScreen implements Screen {

    private final Viewport mViewport;
    private final Viewport backViewport;

    private final Stage backgroundStage;
    private final Stage topgroundStage;
    private final Stage mainStage;
    private final Stage supportStage;
    private final ScreenSwitcher switchAction;

    public StretchedScreen(final ScreenSwitcher screenSwitcher){
        switchAction= screenSwitcher;
        OrthographicCamera mCamera = new OrthographicCamera();
        backViewport = new StretchViewport(GameInitializer.SCREEN_WIDTH, GameInitializer.SCREEN_HEIGHT);
        mViewport = new FitViewport(GameInitializer.SCREEN_WIDTH, GameInitializer.SCREEN_HEIGHT, mCamera);
        init(mViewport);
        backgroundStage = new ImageStage(backViewport, getBackgroundStretchedTexture());
        topgroundStage = new ImageStage(backViewport, getTopgroundStretchedTexture());
        mainStage=getMainStage();
        supportStage=getSupportStage();
    }

    public ScreenSwitcher getSwitchAction() {
        return switchAction;
    }
    public abstract void init(Viewport viewport);
    public abstract Stage getMainStage();
    public abstract Stage getSupportStage();
    public abstract TextureRegion getBackgroundStretchedTexture();
    public abstract TextureRegion getTopgroundStretchedTexture();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 255f); // просто цвет фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backViewport.apply();
        backgroundStage.draw();

        mViewport.apply();
        mainStage.act(delta);
        mainStage.draw();

        backViewport.apply();
        topgroundStage.draw();

        mViewport.apply();
        supportStage.act(delta);
        supportStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        backViewport.update(width, height, true);
        backViewport.setScreenWidth(backViewport.getScreenWidth()*height/backViewport.getScreenHeight());
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
        mainStage.dispose();
        topgroundStage.dispose();
        backgroundStage.dispose();
        supportStage.dispose();
    }



    private static class ImageStage extends Stage{
        ImageStage(Viewport viewport, final TextureRegion textureRegion){
            setViewport(viewport);
            if (textureRegion==null)return;
            addActor(new Background() {
                @Override
                public TextureRegion getTextureRegion() {
                    return textureRegion;
                }
            });
        }
    }
}
