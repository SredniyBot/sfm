package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.abstracts.Background;
import com.sfm.sakura.entity.ButtonActivator;
import com.sfm.main.money.MoneyTracker;
import com.sfm.service.TextureService;

public class ButtonStage extends Stage {

    ButtonStage(Viewport viewport, MoneyTracker moneyTracker, ButtonReaction buttonReaction, ButtonActivator buttonActivator, ScreenSwitcher switchAction) {
        setViewport(viewport);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("sakura/usual/usual.atlas","buttons");
            }
        });
        addActor(moneyTracker);
        addActor(new ButtonHandler(viewport,buttonReaction,buttonActivator,switchAction));
    }


}
