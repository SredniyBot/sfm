package com.sfm.loader;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSetter;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.main.game_utils.Respin;
import com.sfm.menu.stages.MenuScreen;
import com.sfm.ra.stages.RaScreen;
import com.sfm.sakura.stages.SakuraScreen;
import com.sfm.service.FontService;
import com.sfm.service.MoneyService;
import com.sfm.service.Music;
import com.sfm.service.MusicService;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;
import com.sfm.wolf.stages.WolfScreen;

import java.util.ArrayList;
import java.util.List;

public class LoaderScreen extends StretchedScreen implements ScreenSwitcher{

    private AssetManager assetManager;
    private ScreenSetter screenSetter;
    private Screen currentScreen;
    private LoaderStage loaderStage;
    private PrivacyStage privacyStage;


    private final String path="load/loader/loader.atlas";

    public LoaderScreen(final ScreenSwitcher screenSwitcher){
        super(screenSwitcher);
    }

    @Override
    public void init(Viewport viewport) {

        assetManager=new AssetManager();
        assetManager.load(path, TextureAtlas.class);
        assetManager.load("load/loaderutil/util.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        TextureAtlas ta=assetManager.get(path);
        ta.addRegion("text",assetManager.<TextureAtlas>get("load/loaderutil/util.atlas").findRegion("text"));
        MoneyService.setMoneyTexture(assetManager.<TextureAtlas>get("load/loaderutil/util.atlas").findRegion("coin"));
        loaderStage=new LoaderStage(viewport,ta);
        privacyStage=new PrivacyStage();
    }

    @Override
    public Stage getMainStage() {
        return privacyStage;
    }

    @Override
    public Stage getSupportStage() {
        return loaderStage;
    }

    @Override
    public TextureRegion getBackgroundStretchedTexture() {
        return assetManager.<TextureAtlas>get(path).findRegion("back");
    }

    @Override
    public TextureRegion getTopgroundStretchedTexture() {
        return null;
    }

    @Override
    public void switchScreen(ScreenType screenType) {
        List<String> textures = new ArrayList<>();
        screenSetter.setScreen(this);
        if (currentScreen!=null) currentScreen.dispose();

        switch (screenType){
            case TOM_OF_RA:
                MusicService.init(Music.RA);
                MusicService.startLoop(Music.RA);
                textures.add("tom_of_ra/bird/bird.atlas");
                textures.add("tom_of_ra/book/book.atlas");
                textures.add("tom_of_ra/j/j.atlas");
                textures.add("tom_of_ra/k/k.atlas");
                textures.add("tom_of_ra/man/man.atlas");
                textures.add("tom_of_ra/q/q.atlas");
                textures.add("tom_of_ra/usual/usual.atlas");
                textures.add("tom_of_ra/respin/respin.atlas");
                textures.add("utilities/util/util.atlas");
                textures.add("utilities/lines/lines.atlas");
                Respin.setAtlas("tom_of_ra/respin/respin.atlas");
                TextureService.init(textures);
                FontService.init();
                loaderStage.setEndpointScreen(new Runnable() {
                    @Override
                    public void run() {
                        currentScreen=new RaScreen(LoaderScreen.this);
                        screenSetter.setScreen(currentScreen);
                    }
                });
                break;
            case SAKURA_FORTUNE:
                MusicService.init(Music.SAKURA);
                MusicService.startLoop(Music.SAKURA);
                textures.add("sakura/bronze/bronse.atlas");
                textures.add("sakura/ded/ded.atlas");
                textures.add("sakura/green/green.atlas");
                textures.add("sakura/lady/lady.atlas");
                textures.add("sakura/man/man.atlas");
                textures.add("sakura/respin/respin.atlas");
                textures.add("sakura/silver/silver.atlas");
                textures.add("sakura/sword/sword.atlas");
                textures.add("sakura/usual/usual.atlas");
                textures.add("utilities/util/util.atlas");
                textures.add("utilities/lines/lines.atlas");
                Respin.setAtlas("sakura/respin/respin.atlas");
                FontService.init();
                TextureService.init(textures);
                loaderStage.setEndpointScreen(new Runnable() {
                    @Override
                    public void run() {
                        currentScreen=new SakuraScreen(LoaderScreen.this);
                        screenSetter.setScreen(currentScreen);
                    }
                });
                break;
            case WOLF:
                MusicService.init(Music.WOLF);
                MusicService.startLoop(Music.WOLF);
                textures.add("wolf/A/A.atlas");
                textures.add("wolf/K/K.atlas");
                textures.add("wolf/Q/Q.atlas");
                textures.add("wolf/eagle/eagle.atlas");
                textures.add("wolf/puma/puma.atlas");
                textures.add("wolf/respin/respin.atlas");
                textures.add("wolf/wolf/wolf.atlas");
                textures.add("wolf/usual/usual.atlas");
                textures.add("utilities/util/util.atlas");
                textures.add("utilities/lines/lines.atlas");
                Respin.setAtlas("wolf/respin/respin.atlas");
                FontService.init();
                TextureService.init(textures);
                loaderStage.setEndpointScreen(new Runnable() {
                    @Override
                    public void run() {
                        currentScreen=new WolfScreen(LoaderScreen.this);
                        screenSetter.setScreen(currentScreen);
                    }
                });
                break;
            case MAIN_MENU:
            default:
                MusicService.init(Music.MENU);
                MusicService.startLoop(Music.MENU);
                textures.add("menu/menu/menu.atlas");
                textures.add("menu/usual/usual.atlas");
                FontService.init();
                TextureService.init(textures);
                loaderStage.setEndpointScreen(new Runnable() {
                    @Override
                    public void run() {
                        currentScreen=new MenuScreen(LoaderScreen.this);
                        screenSetter.setScreen(currentScreen);
                    }
                });
                break;
        }
    }

    public void setScreenSetter(ScreenSetter gameInitializer) {
        screenSetter=gameInitializer;
    }

}
