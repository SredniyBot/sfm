package com.sfm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Win extends Actor {

    private Money money;
    private Integer score=0;
    private Integer increasePart=0;
    private long time=0;
    private final GlyphLayout layout;
    private int y;

    public Win(Money money){
        this.money=money;
        this.y=(int) (SacuraFortune.SCREEN_HEIGHT-952-60);
        layout = new GlyphLayout();
        layout.setText(FontService.getFont(),String.valueOf(score), Color.valueOf("#ffffff"),360, Align.center,false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,684,y);
    }

    @Override
    public void act(float delta) {
        if(increasePart>0) {
            if (System.currentTimeMillis() - time > 20) {
                time = System.currentTimeMillis();
                if (increasePart>10){
                    score += increasePart / 10;
                    increasePart -= increasePart / 10;
                }else {
                    score++;
                    increasePart--;
                }

                if (increasePart <= 0) {
                    money.increase(score);
                }
                layout.setText(FontService.getFont(), String.valueOf(score), Color.valueOf("#ffffff"), 360, Align.center, false);
            }
        }
    }


    public void setWin(float v) {
        score=0;
        layout.setText(FontService.getFont(),String.valueOf(score),Color.valueOf("#ffffff"),360, Align.center,false);
        increasePart= Math.round(v);
    }
}
