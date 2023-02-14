package com.sfm.service.util.money;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.sfm.service.FontService;
import com.sfm.service.util.GameSound;
import com.sfm.service.SoundService;

public class MoneySkin extends Actor implements MoneyObserver {

    private Integer money;
    private final GlyphLayout layout;
    private TextureRegion textureRegion;
    private final int targetWidth;
    private Integer increasePart=0;
    private long time=0;

    public MoneySkin(int m, int targetWidth){
        this.targetWidth=targetWidth;
        money=m;
        layout = new GlyphLayout();
        updateText();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,getX(),getY());
        if (textureRegion!=null){
            batch.draw(textureRegion,getX()-textureRegion.getRegionWidth()/2f-20+targetWidth/2f-layout.width/2,
                    getY()-textureRegion.getRegionHeight()/2.5f+3,
                    getOriginX(),getOriginY(),textureRegion.getRegionWidth(),textureRegion.getRegionHeight(),
                    0.5f,0.5f,0);
        }
    }

    @Override
    public void act(float delta) {
        if(increasePart>0) {
            if (System.currentTimeMillis() - time > 20) {
                time = System.currentTimeMillis();
                if (increasePart>10){
                    money += increasePart / 10;
                    increasePart -= increasePart / 10;
                }else {
                    money++;
                    increasePart--;
                }
                if (increasePart <= 0) {
                    increasePart=0;
                    SoundService.stopLoopSound(GameSound.WIN);
                }
                updateText();
            }
        }
    }

    private void updateText(){
        layout.setText(FontService.getFont(),String.valueOf(money), Color.valueOf("#ffffff"),targetWidth, Align.center,false);
    }

    @Override
    public void updateMoney(int money) {
        this.money=money;
        updateText();
    }

    public void renew(){
        money=0;
        increasePart=0;
        updateText();
    }

    @Override
    public void increaseMoneySlowly(int m) {
        increasePart+=m;
        SoundService.loopSound(GameSound.WIN);
    }

    public void setMoneyTexture(TextureRegion textureRegion) {
        this.textureRegion=textureRegion;
    }
}
