package com.sfm.main.game_utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.service.TextureService;

public class Respin extends Actor {

    private final int id;
    private final int xp, yp;
    private float time=0;
    private boolean draws=false;

    private static String atlas;

    public Respin(int id, int xp, int yp){
        this.id=id;
        this.xp=xp;
        this.yp=yp;
    }

    public void startShowing(){
        draws=true;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (draws){
            batch.draw(TextureService.getTextureRegion(atlas,String.valueOf(id+1)),xp,yp);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (draws){
            time+=delta;
            if (time>3){
                draws=false;
                time=0;
            }
        }
    }
    public boolean isDrawing() {
        return draws;
    }

    public static void setAtlas(String atlas) {
        Respin.atlas = atlas;
    }
}