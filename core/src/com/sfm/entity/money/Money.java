package com.sfm.entity.money;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.sfm.service.FontService;
import com.sfm.SacuraFortune;

class Money extends Actor {
    private Integer score;
    private final GlyphLayout layout;
    private final int y;

    public Money(){
        this.y=(int) (SacuraFortune.SCREEN_HEIGHT-35);
        layout = new GlyphLayout();
        updateScoreText();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,380,y);
    }


    public void addMoney(int money){
        score+=money;
        updateScoreText();
    }

    public int getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
        updateScoreText();
    }

    private void updateScoreText(){
        layout.setText(FontService.getFont(),"€ "+(score),Color.valueOf("#ffffff"),415, Align.center,false);
    }

}
