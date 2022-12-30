package com.sfm.menu.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.service.FontService;
import com.sfm.service.MoneyService;
import com.sfm.service.TextureService;

public class UnlockButton extends Actor {
    private final Viewport viewport;
    private float pressureTime=0;
    private final int cost;

    private boolean isPressed=false;

    private final BoolRunnable action;
    private final XGettter xGettter;

    private final GlyphLayout layout;
    private final TextureRegion textureRegion;
    private final int targetWidth;

    public UnlockButton(Viewport viewport, BoolRunnable action, float biasX, float y, int w, int h,int cost, XGettter xGettter){
        setX(biasX);
        setY(y);
        setWidth(w);
        setHeight(h);
        layout = new GlyphLayout();
        textureRegion=TextureService.getTextureRegion("menu/usual/usual.atlas","coins");
        targetWidth=360;
        this.xGettter=xGettter;
        this.viewport=viewport;
        this.action=action;
        this.cost=cost;
        updateText();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,getX()-35,getY()+200);
        batch.draw(textureRegion,getX()-textureRegion.getRegionWidth()/2f+35+targetWidth/2f+layout.width/2,
                getY()-textureRegion.getRegionHeight()/2.5f+3+200,
                getOriginX(),getOriginY(),textureRegion.getRegionWidth(),textureRegion.getRegionHeight(),
                0.5f,0.5f,0);

        if (TextureService.getTextureRegion("menu/usual/usual.atlas","unlock")!=null
                &&!isPressed&& MoneyService.getMoney()>=cost)
            batch.draw(TextureService.getTextureRegion("menu/usual/usual.atlas","unlock"),
                    getX()-65,getY()-48);
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
                if(action.run()){
                    remove();
                }
            }
        }
    }

    private boolean contains(Vector2 v){
        return v.x>=getX()+xGettter.getBiasedX()&&v.x<=getX()+xGettter.getBiasedX()+getWidth()&&v.y>=getY()&&v.y<=getY()+getHeight();
    }
    private void updateText(){
        layout.setText(FontService.getFont(),String.valueOf(cost), Color.valueOf("#ffffff"),targetWidth, Align.center,false);
    }

}
