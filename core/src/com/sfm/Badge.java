package com.sfm;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Badge extends Actor {

    private final BadgeType badgeType;
    private int bias;


    private int frame =0;
    private float time=0;
    private boolean animate=false;
    Badge(BadgeType badgeType,int pos,int bias){
        this.badgeType=badgeType;

        this.bias=bias;
        setHeight(TextureService.getBadgeTexture(badgeType,0).getRegionHeight());
        setY(SacuraFortune.SCREEN_HEIGHT-664-256+bias);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(TextureService.getBadgeTexture(badgeType,frame),getX()-badgeType.getPaddingL(),getY()-badgeType.getPaddingT());
    }


    public void deleteBiasDifferent(int bias){
        setY(getY()+bias);
        this.bias+=bias;
    }

    public void animate(float delta){
        if (!animate)return;
        time +=delta;
        if (time >badgeType.getAnimationInterval()){
            time =0;
            frame++;
            if (frame>= badgeType.getNumberOfFrames()){
                frame=0;
                animate=false;
            }
        }
    }

    @Override
    public void act(float delta) {
        animate(delta);
    }

    public void startAnimation(){
        time=0;
        animate=true;
        frame=0;
    }
    public BadgeType getBadgeType() {
        return badgeType;
    }

    public int getBias() {
        return bias;
    }


    public boolean isAnimate() {
        return animate;
    }
}