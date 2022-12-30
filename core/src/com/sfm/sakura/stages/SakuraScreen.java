package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.abstracts.ButtonStage;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.main.game_utils.Respin;
import com.sfm.service.FontService;
import com.sfm.service.TextureService;

import java.util.ArrayList;
import java.util.List;

public class SakuraScreen extends StretchedScreen {

    private SakuraStage gameStage;
    private ButtonStage supportStage;

    public SakuraScreen(ScreenSwitcher screenSwitcher) {
        super(screenSwitcher);
    }

    @Override
    public void init(Viewport viewport) {
        gameStage=new SakuraStage(viewport);
        supportStage=new ButtonStage(viewport, gameStage.getButtonGuardian(), getSwitchAction());
    }

    @Override
    public Stage getMainStage() {
        return gameStage;
    }

    @Override
    public Stage getSupportStage() {
        return supportStage;
    }

    @Override
    public TextureRegion getBackgroundStretchedTexture() {
        return TextureService.getTextureRegion("sakura/usual/usual.atlas","background");
    }

    @Override
    public TextureRegion getTopgroundStretchedTexture() {
        return TextureService.getTextureRegion("sakura/usual/usual.atlas","topground");
    }


}
