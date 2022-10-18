package com.sfm.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.service.TextureService;

public class PlayButton extends Actor {
    private final Viewport viewport;
    private float pressureTime=0;

    private boolean isPressed=false;

    private Runnable action;
    private String texture;
    private XGettter xGettter;


    public PlayButton(Viewport viewport, Runnable action, String texture, float biasX, float y, int w, int h, XGettter xGettter){
        setX(biasX);
        setY(y);
        setWidth(w);
        setHeight(h);
        this.xGettter=xGettter;
        this.viewport=viewport;
        this.action=action;
        this.texture=texture;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (TextureService.getTextureRegion("menu/usual/usual.atlas","play")!=null&&isPressed)
            batch.draw(TextureService.getTextureRegion("menu/usual/usual.atlas","play"),getX(),getY());
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            if(contains(touch)) {
                pressureTime+=delta;
                isPressed=true;
            }else {
                isPressed=false;
                pressureTime=0;
            }
        }else {
            isPressed=false;
            if (pressureTime>0&&pressureTime<1){
                pressureTime=0;
                action.run();
            }
        }
    }

    private boolean contains(Vector2 v){
        return v.x>=getX()+xGettter.getBiasedX()&&v.x<=getX()+xGettter.getBiasedX()+getWidth()&&v.y>=getY()&&v.y<=getY()+getHeight();
    }

}
