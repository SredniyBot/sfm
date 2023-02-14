package com.sfm.menu.slider;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfm.service.TextureService;

public class Dot extends Actor {
    private final int count;
    private int current=0;
    private final TextureRegion invise;
    private final TextureRegion vise;
    private final SliderParamContainer paramContainer;

    public Dot(int count,SliderParamContainer paramContainer) {
        this.count = count;
        this.paramContainer = paramContainer;
        invise=TextureService.getTextureRegion("menu/slider/slider.atlas","invise");
        vise=TextureService.getTextureRegion("menu/slider/slider.atlas","vise");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        int bias= paramContainer.getW()/2-count*36/2-(count-1)*36/2;
        for (int i=0;i<count;i++){
            if (i==current){
                batch.draw(vise,bias,17);
            }else{
                batch.draw(invise,bias,15);
            }
            bias+=36*2;
        }
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
