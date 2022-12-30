package com.sfm.main.abstracts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.service.MoneyService;
import com.sfm.service.TextureService;

public class ButtonStage extends Stage {

    public ButtonStage(Viewport viewport, ButtonGuardian buttonGuardian, ScreenSwitcher switchAction) {
        setViewport(viewport);
        addActor(new Background() {
            @Override
            public TextureRegion getTextureRegion() {
                return TextureService.getTextureRegion("utilities/util/util.atlas","buttons");
            }
        });
        addActor(new ButtonHandler(viewport,buttonGuardian, switchAction));
        addActor(MoneyService.getSlotMoneySkin());
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
