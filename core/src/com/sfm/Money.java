package com.sfm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Money extends Actor {

    private Integer score=5000;
    private Integer increasePart=0;
    private long time=0;
    private final GlyphLayout layout;
    private int y;

    public Money(){
        this.y=(int) (SacuraFortune.SCREEN_HEIGHT-35);
        layout = new GlyphLayout();
        layout.setText(FontService.getFont(),"€ "+ score, Color.valueOf("#ffffff"),415, Align.center,false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,380,y);
    }

    @Override
    public void act(float delta) {
        if(increasePart!=0){
            if(score+increasePart>0)score+=increasePart;
            increasePart=0;
            layout.setText(FontService.getFont(),"€ "+(score),Color.valueOf("#ffffff"),415, Align.center,false);
        }
    }

    public void increase(int increasePart){
        this.increasePart+=increasePart;
    }

    public int getScore() {
        return score;
    }
}
