package com.sfm;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatedCoin extends Actor {

    private int frame =0;
    private float interval=1;
    private float time=0;



    AnimatedCoin() {
        setSize(256,256);
        setPosition(266-33,SacuraFortune.SCREEN_HEIGHT-139-256-37);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(TextureService.getCoinTextureById(frame),getX(),getY(),
                getOriginX(),getOriginY(),
                TextureService.getCoinTextureById( frame).getRegionWidth(),
                TextureService.getCoinTextureById( frame).getRegionHeight(),
                getScaleX(),getScaleY(),getRotation());

    }

    private float vanishTime;
    private boolean vanishes =true;

    public void animateVanish(float delta){
        if (!vanishes)return;
        vanishTime +=delta;
        if (vanishTime >0.1){
            vanishTime =0;
            frame++;
            if (frame>61){
                frame=61;
                vanishes =false;
            }
        }
    }

    @Override
    public void act(float delta) {
        animateVanish(delta);
//        time+=delta;
//        if (time>interval){
//            if (interval<= 0)disappear();
//            time=0;
//            vanishes =true;
//        }

        super.act(delta);
    }

}
