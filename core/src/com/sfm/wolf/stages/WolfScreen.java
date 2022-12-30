package com.sfm.wolf.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.abstracts.ButtonStage;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.service.TextureService;

public class WolfScreen extends StretchedScreen {

    private WolfStage gameStage;
    private ButtonStage supportStage;

    public WolfScreen(ScreenSwitcher screenSwitcher) {
        super(screenSwitcher);
    }

    @Override
    public void init(Viewport viewport) {
        gameStage=new WolfStage(viewport);
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
        return TextureService.getTextureRegion("wolf/usual/usual.atlas","background");
    }

    @Override
    public TextureRegion getTopgroundStretchedTexture() {
        return TextureService.getTextureRegion("wolf/usual/usual.atlas","topground");
    }


}
