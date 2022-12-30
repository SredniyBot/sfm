package com.sfm.loader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.main.GameInitializer;

public class PrivacyActor extends Actor {
    private final TextureRegion textureRegion;

    PrivacyActor(TextureRegion textureRegion){
        this.textureRegion=textureRegion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion,277, GameInitializer.SCREEN_HEIGHT-1050);
    }
}
