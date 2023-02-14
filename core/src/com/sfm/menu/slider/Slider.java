package com.sfm.menu.slider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.menu.entity.CursorCatcher;
import com.sfm.menu.entity.XGettter;
import com.sfm.service.TextureService;

import java.util.ArrayList;


public class Slider extends Group implements SliderParamContainer{
    private final ArrayList<Slide> array;
    private int currentSlide=0;
    private final CursorCatcher cursorCatcher;
    private final Viewport viewport;
    private final XGettter xGettter;
    private final Dot dot;
    private boolean isPressed=false;
    private float prvX=0;

    private float bias;
    public Slider(Viewport viewport, CursorCatcher cursorCatcher, XGettter xGettter){
        this.cursorCatcher=cursorCatcher;
        this.viewport=viewport;
        this.xGettter=xGettter;

        setY(115);
        array=new ArrayList<>();
        array.add(new Slide(this,new Texture("menu/slider/downloaded/bonus.png")));
        array.add(new Slide(this,new Texture("menu/slider/downloaded/bonus.png")));
        array.add(new Slide(this,new Texture("menu/slider/downloaded/bonus.png")));
        array.add(new Slide(this,new Texture("menu/slider/downloaded/bonus.png")));
        array.add(new Slide(this,new Texture("menu/slider/downloaded/bonus.png")));
        array.add(new Slide(this,new Texture("menu/slider/downloaded/bonus.png")));
        dot=new Dot(array.size(),this);
        setCurrent(currentSlide);
        for (Slide s:array){
            addActor(s);
        }
        addActor(dot);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    private final float velocity=15;
    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isPressed){
            if (bias!=0||bias!=getW()||bias!=-getW()){
                if (bias>0&&bias<getW()/2f){
                    bias-=bias*delta*velocity;
                    if (Math.abs(bias)<1)bias=0;
                } else if (bias>getW()/2f) {
                    bias+=(getW()-bias)*delta*velocity;
                    if (Math.abs(getW()-bias)<1){
                        currentSlide--;
                        if (currentSlide==-1)currentSlide=array.size()-1;
                        setCurrent(currentSlide);
                        bias=0;
                    }
                } else if (bias < 0 && bias > -getW() / 2f) {
                    bias-=bias*delta*velocity;
                    if (Math.abs(bias)<1)bias=0;
                } else if (bias < -getW() / 2f) {
                    bias+=(-getW()-bias)*delta*velocity;
                    if (Math.abs(-getW()-bias)<1){
                        currentSlide++;
                        if (currentSlide==array.size())currentSlide=0;
                        setCurrent(currentSlide);
                        bias=0;
                    }

                }
            }
        }
        if(!isPressed&&cursorCatcher.isCaught())return;
        if(array.size()<=1)return;
        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            if (contains(touch)||isPressed){
                isPressed=true;
                cursorCatcher.setCaught(true);
                if (prvX!=0) {
                    setBias(getBias() - (prvX - touch.x));
//                    if (getBias() - (prvX - touch.x) >= 0)
//                        setBias(getBias() - (prvX - touch.x));
//                    else

                    System.out.println(bias);
                }
            }
            prvX=touch.x;
        }else {
            cursorCatcher.setCaught(false);
            isPressed=false;
            prvX=0;
        }
    }

    private void setCurrent(int current){
        for (Slide s:array)s.clear();
        array.get(current).setCurrent();
        int nextSlide=current+1;
        if (nextSlide==array.size())nextSlide=0;
        array.get(nextSlide).setNext();
        int prevSlide=current-1;
        if (prevSlide==-1)prevSlide=array.size()-1;
        array.get(prevSlide).setPrevious();
        dot.setCurrent(current);
    }
    private boolean contains(Vector2 v){
        return v.x>=getX()+xGettter.getBiasedX()&&v.x<=getX()+xGettter.getBiasedX()+getW()&&v.y>=getY()&&v.y<=getY()+getH();
    }

    public float getBias() {
        return bias;
    }

    public void setBias(float bias) {
        this.bias = Math.min(getW(),Math.max(-getW(),bias));
    }

    @Override
    public int getW() {
        return 600;
    }

    @Override
    public int getH() {
        return 712;
    }

    @Override
    public int getDrawComp() {
        return 0;
    }

    @Override
    public int getDrawComp2() {
        return 0;
    }

    @Override
    public boolean isPressed() {
        return isPressed;
    }
}
