package com.sfm.entity.money;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.sfm.service.FontService;
import com.sfm.main.SacuraFortune;

class Bet extends Actor {
    private Integer bet=100;
    private final GlyphLayout layout;
    private final int y;

    public Bet(){
        this.y=(int) (SacuraFortune.SCREEN_HEIGHT-952-60);
        layout = new GlyphLayout();
        updateBetText();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,215,y);
    }

    public void changeBet(int changePart){
        bet +=changePart;
        updateBetText();
    }

    public void setBet(Integer bet) {
        this.bet = bet;
        updateBetText();
    }

    private void updateBetText(){
        layout.setText(FontService.getFont(),String.valueOf(bet),Color.valueOf("#ffffff"),360, Align.center,false);
    }

    public Integer getBet() {
        return bet;
    }
}
