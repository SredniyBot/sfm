package com.sfm.menu.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.service.util.GameSound;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

public class PlayButton extends Actor {
    private final Viewport viewport;
    private float pressureTime=0;

    private boolean isPressed=false;

    private final Runnable action;
    private final XGettter xGettter;
    private final CursorCatcher cursorCatcher;


    public PlayButton(Viewport viewport, Runnable action, float biasX, float y, int w, int h, XGettter xGettter, CursorCatcher cursorCatcher){
        setX(biasX+20);
        setY(y);
        setWidth(w);
        setHeight(h);
        this.cursorCatcher=cursorCatcher;
        this.xGettter=xGettter;
        this.viewport=viewport;
        this.action=action;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (TextureService.getTextureRegion("menu/usual/usual.atlas","play")!=null&&isPressed)
            batch.draw(TextureService.getTextureRegion("menu/usual/usual.atlas","play"),getX()-20,getY()-20);
    }

    @Override
    public void act(float delta) {
        if (!isPressed&&cursorCatcher.isCaught())return;
        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            if(contains(touch)) {
                cursorCatcher.setCaught(true);
                pressureTime+=delta;
                isPressed=true;
            }else {
                cursorCatcher.setCaught(false);
                isPressed=false;
                pressureTime=0;
            }
        }else {
            isPressed=false;
            cursorCatcher.setCaught(false);
            if (pressureTime>0&&pressureTime<1){
                pressureTime=0;
                SoundService.playSound(GameSound.PLAYMENU);
                action.run();
            }
        }
    }

    private boolean contains(Vector2 v){
        return v.x>=getX()+xGettter.getBiasedX()&&v.x<=getX()+xGettter.getBiasedX()+getWidth()&&v.y>=getY()&&v.y<=getY()+getHeight();
    }

}
