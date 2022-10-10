package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.abstracts.StretchedScreen;
import com.sfm.service.FontService;
import com.sfm.service.TextureService;

import java.util.ArrayList;
import java.util.List;

public class SakuraScreen extends StretchedScreen {

    private GameStage gameStage;
    private ButtonStage supportStage;

    public SakuraScreen(ScreenSwitcher screenSwitcher) {
        super(screenSwitcher);
    }

    @Override
    public void init(Viewport viewport) {
        List<String> textures = new ArrayList<>();
        textures.add("sakura/bronze/bronse.atlas");
        textures.add("sakura/ded/ded.atlas");
        textures.add("sakura/green/green.atlas");
        textures.add("sakura/lady/lady.atlas");
        textures.add("lines/lines.atlas");
        textures.add("sakura/man/man.atlas");
        textures.add("respin/respin.atlas");
        textures.add("sakura/silver/silver.atlas");
        textures.add("sakura/sword/sword.atlas");
        textures.add("sakura/usual/usual.atlas");
        TextureService.init(textures);
        FontService.init();
        gameStage=new GameStage(viewport);
        supportStage=new ButtonStage(viewport,gameStage.getMoneyTracker(),gameStage,gameStage.getMoneyTracker(),getSwitchAction());
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
