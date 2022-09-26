package com.sfm.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class HoldButton extends Button {
    private Viewport viewport;
    private float pressureTimeS=0;

    public HoldButton(Viewport viewport){
        super(viewport);
        this.viewport=viewport;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            if(getRectangle().contains(touch)) {
                pressureTimeS+=delta;
                if(pressureTimeS>0.5){
                    action();
                }
            }else {
                pressureTimeS=0;
            }
        }else {
            pressureTimeS=0;
        }
    }
}
