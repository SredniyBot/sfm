package com.sfm.main.money;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.sfm.service.FontService;
import com.sfm.main.GameInitializer;

class Win extends Actor {
    private Integer win =0;
    private Integer increasePart=0;
    private long time=0;
    private final GlyphLayout layout;
    private final int y;

    private int prevWin=0;
    public WinListener winListener;
    public Win(WinListener winListener){
        this.winListener=winListener;
        this.y=(int) (GameInitializer.SCREEN_HEIGHT-952-60);
        layout = new GlyphLayout();
        updateWinText();
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
                    win += increasePart / 10;
                    increasePart -= increasePart / 10;
                }else {
                    win++;
                    increasePart--;
                }

                if (increasePart <= 0) {
                    increasePart=0;
                    winListener.win(win-prevWin);
                    prevWin=win;
                }
                updateWinText();
            }
        }
    }


    public void addWin(float v) {
        increasePart+= Math.round(v);
    }

    private void updateWinText(){
        layout.setText(FontService.getFont(),String.valueOf(win),Color.valueOf("#ffffff"),360, Align.center,false);
    }

    public void renew() {
        prevWin=0;
        win=0;
        increasePart=0;
        updateWinText();
    }
}
