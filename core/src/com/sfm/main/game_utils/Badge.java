package com.sfm.main.game_utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.main.GameInitializer;
import com.sfm.service.TextureService;

/**
 * класс значка на поле
 * @param <T> тип значка, задатся после создания нового слота
 */
public class Badge<T extends BadgeType<T>> extends Actor {

    private final T badgeType;
    private int bias;
    private int frame =0;
    private float time=0;
    private boolean animate=false;
    public Badge(T badgeType, int pos, int bias){
        this.badgeType = badgeType;

        this.bias=bias;
        setHeight(TextureService.getTextureRegion(badgeType.getAtlas(),
                badgeType.getName(0)).getRegionHeight());
        setY(GameInitializer.SCREEN_HEIGHT-664-256+bias);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(TextureService.getTextureRegion(badgeType.getAtlas()
                , badgeType.getName(frame)),getX()- badgeType.getPaddingL(),getY()- badgeType.getPaddingT());
    }


    public void deleteBiasDifferent(int bias){
        setY(getY()+bias);
        this.bias+=bias;
    }

    public void animate(float delta){
        if (!animate)return;
        time +=delta;
        if (time > badgeType.getAnimationInterval()){
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
    public BadgeType<T> getBadgeType() {
        return badgeType;
    }

    public int getBias() {
        return bias;
    }

    public boolean isAnimate() {
        return animate;
    }
}
