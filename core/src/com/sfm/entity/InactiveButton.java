package com.sfm.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class InactiveButton extends Button{

    public InactiveButton(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isActive()){
            super.draw(batch, parentAlpha);
        } else {
            if (getInactiveTextureRegion()!=null)
                batch.draw(getInactiveTextureRegion(),getRectX(),getRectY());
        }
    }
    public abstract TextureRegion getInactiveTextureRegion();
    public abstract boolean isActive();
}
