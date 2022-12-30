package com.sfm.ra.stages;

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

public class RaScreen extends StretchedScreen {

    private RaStage raStage;
    private ButtonStage supportStage;

    public RaScreen(ScreenSwitcher screenSwitcher) {
        super(screenSwitcher);
    }

    @Override
    public void init(Viewport viewport) {
        raStage =new RaStage(viewport);
        supportStage=new ButtonStage(viewport, raStage.getButtonGuardian(), getSwitchAction());
    }

    @Override
    public Stage getMainStage() {
        return raStage;
    }

    @Override
    public Stage getSupportStage() {
        return supportStage;
    }

    @Override
    public TextureRegion getBackgroundStretchedTexture() {
        return TextureService.getTextureRegion("tom_of_ra/usual/usual.atlas","background");
    }

    @Override
    public TextureRegion getTopgroundStretchedTexture() {
        return TextureService.getTextureRegion("tom_of_ra/usual/usual.atlas","topground");
    }


}
