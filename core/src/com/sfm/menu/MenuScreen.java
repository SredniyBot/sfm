package com.sfm.menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.service.FontService;
import com.sfm.service.TextureService;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends StretchedScreen {

    private MenuStage menuStage;
    private ButtonStage supportStage;


    public MenuScreen(final ScreenSwitcher screenSwitcher) {
        super(screenSwitcher);
    }

    @Override
    public void init(Viewport viewport) {
        List<String> textures = new ArrayList<>();
        textures.add("menu/usual/usual.atlas");
        textures.add("menu/menu/menu.atlas");
        TextureService.init(textures);
        FontService.init();
        menuStage=new MenuStage(viewport,getSwitchAction());
        supportStage=new ButtonStage(viewport);
    }

    @Override
    public Stage getMainStage() {
        return menuStage;
    }

    @Override
    public Stage getSupportStage() {
        return supportStage;
    }

    @Override
    public TextureRegion getBackgroundStretchedTexture() {
        return TextureService.getTextureRegion("menu/usual/usual.atlas","background");
    }

    @Override
    public TextureRegion getTopgroundStretchedTexture() {
        return null;
    }
}
