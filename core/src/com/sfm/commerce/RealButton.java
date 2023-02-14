package com.sfm.commerce;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.Button;
import com.sfm.service.CommercialService;
import com.sfm.service.util.GameSound;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

/**
 * Реализация кнопки Real
 */
public class RealButton extends Button {
    private boolean isAvailable=false;

    public RealButton(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isAvailable)
            super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        if (isAvailable)
            super.act(delta);
    }

    @Override
    public void action() {
        SoundService.playSound(GameSound.BUTTON);
        CommercialService.openURL();
    }

    @Override
    public TextureRegion getTextureRegion() {
        return TextureService.getTextureRegion("utilities/util/util.atlas","real");
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(1113, GameInitializer.SCREEN_HEIGHT-113,415,118);
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
