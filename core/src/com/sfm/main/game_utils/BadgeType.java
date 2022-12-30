package com.sfm.main.game_utils;



public interface BadgeType<T extends BadgeType> {

    int getPaddingT();

    int getPaddingL();

    int getSize();
    T getType();


    int getNumberOfFrames();
    float getAnimationInterval();
    int getId();

    String getAtlas();

    String getName(int frameNumber);

    float getCost2();
    float getCost3();
    float getCost4();
    float getCost5();

}
