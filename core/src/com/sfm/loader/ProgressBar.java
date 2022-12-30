package com.sfm.loader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.main.GameInitializer;
import com.sfm.service.TextureService;

public class ProgressBar extends Actor {

    private final Runnable runnable;
    private final TextureAtlas textureAtlas;
    int frame=0;
    int maxFrame=0;
    ProgressBar(TextureAtlas textureAtlas, Runnable runnable){
        this.runnable=runnable;
        this.textureAtlas=textureAtlas;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureAtlas.findRegion(
                "load-"+frame),
                507, GameInitializer.SCREEN_HEIGHT-977);
        if (frame<maxFrame)frame++;
    }

    @Override
    public void act(float delta) {
        frame=Math.min(frame,maxFrame);
        maxFrame=(int)(TextureService.getProgress()*90);
        if (TextureService.isUpdated()&&frame==maxFrame){
            frame=0;
            runnable.run();
        }
    }

}
