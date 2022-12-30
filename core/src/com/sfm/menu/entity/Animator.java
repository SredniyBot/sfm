package com.sfm.menu.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.service.TextureService;

public class Animator extends Actor {

    private boolean animates;
    private final TextureRegion textureRegion;
    private float scale;
    private boolean scaleDest;
    private String name;
    private final Window window;

    Animator(Window window){
        this.window=window;
        scale=1;
        this.textureRegion= TextureService.getTextureRegion("menu/usual/usual.atlas","glow");
        setOrigin(textureRegion.getRegionWidth()/2f, textureRegion.getRegionHeight()/2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (animates)
            batch.draw(textureRegion,getX()-textureRegion.getRegionWidth()/2f,
                    getY()-50,getOriginX(),getOriginY(),
                textureRegion.getRegionWidth(),textureRegion.getRegionHeight(),
                scale,scale,getRotation());
    }

    @Override
    public void act(float delta) {
        if (animates){
            setRotation(getRotation()+delta*75);
            if (scaleDest){
                scale+=delta/2;
                if(scale>=1.1){
                    scaleDest=false;
                }
            }else {
                scale-=delta/2;
                if(scale<=0.7){
                    scaleDest=true;
                }
            }
            if (getRotation()>=360){
                animates=false;
                window.show(name);
            }
        }
    }


    public void startAnimation(String name){
        this.name=name;
        setRotation(0);
        scale=1;
        animates=true;
    }
}
