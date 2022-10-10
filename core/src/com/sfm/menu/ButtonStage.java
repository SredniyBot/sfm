package com.sfm.menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.abstracts.Background;
import com.sfm.main.money.MoneyTracker;
import com.sfm.service.TextureService;

public class ButtonStage extends Stage {

    ButtonStage(Viewport viewport){
        setViewport(viewport);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("menu/usual/usual.atlas","buttons");
            }
        });
        MoneyTracker m = GameInitializer.getMoneyTracker();
        m.showBet(false);
        m.setNX(180);
        addActor(m);
    }
}
