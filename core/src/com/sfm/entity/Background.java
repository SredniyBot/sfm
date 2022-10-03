package com.sfm.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.service.TextureService;

public abstract class Background extends Actor {
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(getTextureRegion(),0,0);
    }

    public abstract TextureRegion getTextureRegion();

}
