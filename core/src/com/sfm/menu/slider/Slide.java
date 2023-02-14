package com.sfm.menu.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;

import javax.swing.text.View;

public class Slide extends Actor {

    private Texture texture;
    private final SliderParamContainer paramContainer;

    private boolean isPrevious;
    private boolean isCurrent;
    private boolean isNext;
    Slide(SliderParamContainer paramContainer,Texture texture){

        this.paramContainer=paramContainer;
        this.texture=texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isNext){
            float bias=paramContainer.getBias()+paramContainer.getW();
            if (paramContainer.getBias() < 0) {
                batch.draw(texture, bias, 0, 0, 0,
                        (int) (paramContainer.getW() - bias), paramContainer.getH());
            }
        }
        if (isPrevious){
            if (paramContainer.getBias() > 0) {
                batch.draw(texture, 0, 0, (int)( -paramContainer.getBias()+paramContainer.getW()), 0,
                        (int) (paramContainer.getBias()), paramContainer.getH());
            }
        }
        if (isCurrent) {
            if (paramContainer.getBias() < 0) {
                batch.draw(texture, 0, 0, (int) -paramContainer.getBias(), 0,
                        (int) (paramContainer.getW() + paramContainer.getBias()), paramContainer.getH());
            } else {
                batch.draw(texture, paramContainer.getBias(), 0, 0, 0,
                        (int) (paramContainer.getW() - paramContainer.getBias()), paramContainer.getH());
            }
        }
    }


    public void clear(){
        isCurrent=false;
        isPrevious=false;
        isNext=false;
    }

    public void setPrevious() {
        isPrevious = true;
    }

    public void setCurrent() {
        isCurrent = true;
    }

    public void setNext() {
        isNext = true;
    }

    @Override
    public void act(float delta) {
    }
}
