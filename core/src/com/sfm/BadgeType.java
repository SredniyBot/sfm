package com.sfm;

import java.util.Random;

public enum BadgeType {
    LADY        (0,3,45,63,45,0.04f),
    SWORD       (1,2,25,30,56,0.04f),
    DED         (2,1,35 ,45 ,36,0.05f),
    MAN         (3,1,35 ,45 ,36,0.05f),
    COIN_GRAY   (4,1,27 ,38 ,60,0.05f),
    COIN_GREEN  (5,1,27 ,38 ,60,0.05f),
    COIN_BROWN  (6,1,27 ,38 ,60,0.05f);

    private final int size;
    private final int paddingT;
    private final int paddingL;
    private final int numberOfFrames;
    private final float animationInterval;



    private final int id;

    BadgeType(int id,int size,int paddingT,int paddingL,int numberOfFrames,float animationInterval){
        this.id=id;
        this.size=size;
        this.animationInterval=animationInterval;
        this.numberOfFrames=numberOfFrames;
        this.paddingT=paddingT;
        this.paddingL=paddingL;
    }

    public int getPaddingT() {
        return paddingT;
    }

    public int getPaddingL() {
        return paddingL;
    }

    public static BadgeType getRandom(){
        if (new Random().nextInt(15)==0)return LADY;
        switch (new Random().nextInt(6)){
            case 0:
                return COIN_BROWN;
            case 1:
                return SWORD;
            case 2:
                return DED;
            case 3:
                return MAN;
            case 5:
                return COIN_GRAY;
            default:
                return COIN_GREEN;
        }
    }

    public static BadgeType getRandomOne(){
        switch (new Random().nextInt(5)){
            case 0:
                return COIN_GREEN;
            case 1:
                return COIN_GRAY;
            case 2:
                return DED;
            case 3:
                return MAN;
            default:
                return COIN_BROWN;
        }
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public float getAnimationInterval() {
        return animationInterval;
    }
    public int getId() {
        return id;
    }
}
