package com.sfm.grounds;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.service.TextureService;

public class BackgroundRam extends Actor {

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(TextureService.getRamkaTexture(),0,0);
    }


}
