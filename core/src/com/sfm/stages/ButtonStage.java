package com.sfm.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.entity.Background;
import com.sfm.entity.ButtonActivator;
import com.sfm.entity.money.MoneyTracker;
import com.sfm.service.TextureService;

public class ButtonStage extends Stage {

    ButtonStage(Viewport viewport, MoneyTracker moneyTracker, ButtonReaction buttonReaction, ButtonActivator buttonActivator) {
        setViewport(viewport);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getButtonsTexture();
            }
        });
        addActor(moneyTracker);
        addActor(new ButtonHandler(viewport,buttonReaction,buttonActivator));

    }


}
