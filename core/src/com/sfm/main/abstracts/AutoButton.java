package com.sfm.main.abstracts;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.service.GameSound;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

public class AutoButton extends InactiveButton{

    private final ButtonGuardian buttonGuardian;
    private boolean isActivated;

    public AutoButton(Viewport viewport, ButtonGuardian buttonGuardian) {
        super(viewport);
        this.buttonGuardian = buttonGuardian;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!isActivated){
            super.draw(batch, parentAlpha);
        } else {
            if (getActivatedTextureRegion()!=null)
                batch.draw(getActivatedTextureRegion(),getRectX()-13,getRectY()-12);
        }
    }



    @Override
    public TextureRegion getInactiveTextureRegion() {
        return TextureService.getTextureRegion("utilities/util/util.atlas","autoN");
    }
    public TextureRegion getActivatedTextureRegion() {
        return TextureService.getTextureRegion("utilities/util/util.atlas","stop");
    }
    @Override
    public boolean isActive() {
        return !buttonGuardian.isLocked();
    }

    @Override
    public void action() {
        if (isActivated){
            SoundService.playSound(GameSound.AUTOPLAY);
            isActivated=false;
        }else if (isActive()){
            SoundService.playSound(GameSound.AUTOPLAY);
            isActivated=true;
        }
        if (!buttonGuardian.setAutoRespin(isActivated)){
            SoundService.playSound(GameSound.AUTOPLAY);
            isActivated=false;
        }
    }

    @Override
    public TextureRegion getTextureRegion() {
        return TextureService.getTextureRegion("utilities/util/util.atlas","auto");
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(1670, GameInitializer.SCREEN_HEIGHT-985-77,218,77);
    }
}
