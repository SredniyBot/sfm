package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.service.FontService;

public class SakuraScreen extends StretchedScreen {

    private GameStage gameStage;
    private ButtonStage supportStage;

    @Override
    public void init(Viewport viewport) {
        TextureService.init();
        FontService.init();
        gameStage=new GameStage(viewport);
        supportStage=new ButtonStage(viewport,gameStage.getMoneyTracker(),gameStage,gameStage.getMoneyTracker());
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
        return TextureService.getBgTexture();
    }

    @Override
    public TextureRegion getTopgroundStretchedTexture() {
        return TextureService.getTgTexture();
    }


}
