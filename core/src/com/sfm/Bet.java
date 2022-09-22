package com.sfm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Bet extends Actor {

    private final Money money;
    private final Win win;
    private Integer score=100;
    private Integer increasePart=0;
    private final GlyphLayout layout;
    private final int y;
    private boolean lock=false;

    public Bet(Money money,Win win){
        this.win=win;
        this.money=money;
        this.y=(int) (SacuraFortune.SCREEN_HEIGHT-952-60);
        layout = new GlyphLayout();
        layout.setText(FontService.getFont(),String.valueOf(score), Color.valueOf("#ffffff"),360, Align.center,false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        FontService.getFont().draw(batch,layout,215,y);
    }

    @Override
    public void act(float delta) {
        if(increasePart!=0){
            if(score+increasePart>0&&score+increasePart<=money.getScore())score+=increasePart;
            increasePart=0;
            layout.setText(FontService.getFont(),String.valueOf(score),Color.valueOf("#ffffff"),360, Align.center,false);
        }
    }

    public void increaseBet(int increasePart){
        if (!lock)
            this.increasePart+=increasePart;
    }

    public void setWin(float cost){
        win.setWin(score*cost);
        layout.setText(FontService.getFont(),String.valueOf(score),Color.valueOf("#ffffff"),360, Align.center,false);
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public void spin(boolean respin){
        if (!respin){
            money.increase(-score);
            if(money.getScore()<score)score=money.getScore();
            setWin(0);
            setLock(true);
        }
    }
}
