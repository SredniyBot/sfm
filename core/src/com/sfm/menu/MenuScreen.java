package com.sfm.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;
import com.sfm.main.abstracts.Background;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.service.FontService;
import com.sfm.service.TextureService;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen implements Screen {
    private final Viewport mViewport;
    private final Viewport backViewport;
    private final Viewport menuViewport;

    private final Stage backgroundStage;
    private final Stage topgroundStage;
    private final MenuStage menuStage;

    private final ButtonStage supportStage;
    private final ScreenSwitcher switchAction;

    public MenuScreen(final ScreenSwitcher screenSwitcher){
        switchAction= screenSwitcher;
        OrthographicCamera mCamera = new OrthographicCamera();
        backViewport = new StretchViewport(GameInitializer.SCREEN_WIDTH, GameInitializer.SCREEN_HEIGHT);
        mViewport = new FitViewport(GameInitializer.SCREEN_WIDTH, GameInitializer.SCREEN_HEIGHT, mCamera);
        menuViewport = new ExtendViewport(GameInitializer.SCREEN_WIDTH, GameInitializer.SCREEN_HEIGHT,mCamera);

        List<String> textures = new ArrayList<>();
        textures.add("menu/usual/usual.atlas");
        textures.add("menu/menu/menu.atlas");
        TextureService.init(textures);
        FontService.init();
        menuStage=new MenuStage(menuViewport,getSwitchAction());

        supportStage=new ButtonStage(mViewport);
        backgroundStage = new ImageStage(backViewport, getBackgroundStretchedTexture());
        topgroundStage = new ImageStage(backViewport, getTopgroundStretchedTexture());

    }

    public ScreenSwitcher getSwitchAction() {
        return switchAction;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 255f); // просто цвет фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backViewport.apply();
        backgroundStage.draw();

        menuViewport.apply();
        menuStage.act(delta);
        menuStage.draw();

        backViewport.apply();
        topgroundStage.draw();

        mViewport.apply();
        supportStage.act(delta);
        supportStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuViewport.update(width,height,true);
        menuViewport.update(width,height,true);

        backViewport.update(width, height, true);
        backViewport.setScreenWidth(backViewport.getScreenWidth()*height/backViewport.getScreenHeight());
        mViewport.update(width, height, true);
        mViewport.setScreenWidth(mViewport.getScreenWidth()*height/mViewport.getScreenHeight());
//        menuViewport.setScreenWidth(mViewport.getScreenWidth()*height/mViewport.getScreenHeight());
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
        menuStage.dispose();
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



    public TextureRegion getBackgroundStretchedTexture() {
        return TextureService.getTextureRegion("menu/usual/usual.atlas","background");
    }

    public TextureRegion getTopgroundStretchedTexture() {
        return null;
    }
}
