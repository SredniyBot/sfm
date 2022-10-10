package com.sfm.sakura.stages;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.abstracts.Background;
import com.sfm.sakura.entity.ButtonActivator;
import com.sfm.main.money.MoneyTracker;

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
