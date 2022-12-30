package com.sfm.wolf.entity;

import com.sfm.main.game_utils.BadgeGenerator;
import com.sfm.main.game_utils.BadgeType;

import java.util.Random;

public enum WolfType implements BadgeType<WolfType> {
    A    (0,1,0,-3,13,0.1f, 0.7F, 1.4F, 2.1F, "wolf/A/A.atlas", "A"),
    K    (1,1,0,-3,13,0.1f, 0.6f, 1.2f, 1.8f, "wolf/K/K.atlas", "K"),
    Q    (2,1,0,-3,13,0.1f, 0.5f, 1f  , 1.5f, "wolf/Q/Q.atlas", "Q"),
    EAGLE(3,1,6,4 ,13,0.1f, 3   , 6   , 9   , "wolf/eagle/eagle.atlas", "eagle"),
    PUMA (4,1,5,8 ,13,0.1f, 1.2f, 1.8f, 2.4f, "wolf/puma/puma.atlas", "puma"),
    WOLF (6,1,0,23,13,0.1f, 2   , 4   , 6   , "wolf/wolf/wolf.atlas", "wolf");

    private final int size;
    private final int paddingT;
    private final int paddingL;
    private final int numberOfFrames;
    private final float animationInterval;
    private final float cost3;
    private final float cost4;
    private final float cost5;
    private final String atlas;
    private final String name;
    private final int id;
    public static final BadgeGenerator<WolfType> badgeGenerator =new BadgeGenerator<WolfType>() {
        @Override
        public WolfType getRandom() {
            if (new Random().nextInt(6)==0)return WOLF;
            if (new Random().nextInt(10)==0)return EAGLE;
            switch (new Random().nextInt(4)){
                case 0:
                    return A;
                case 1:
                    return K;
                case 2:
                    return Q;
                case 3:
                default:
                    return PUMA;
            }
        }

        @Override
        public WolfType getRandomOne() {
            return getRandom();
        }
    };

    WolfType(int id, int size, int paddingT, int paddingL,
             int numberOfFrames, float animationInterval,
             float cost3, float cost4, float cost5, String atlas, String name){
        this.id=id;
        this.size=size;
        this.animationInterval=animationInterval;
        this.numberOfFrames=numberOfFrames;
        this.paddingT=paddingT;
        this.paddingL=paddingL;
        this.cost3 = cost3;
        this.cost4 = cost4;
        this.cost5 = cost5;
        this.atlas = atlas;
        this.name = name;
    }

    public int getPaddingT() {
        return paddingT;
    }

    public int getPaddingL() {
        return paddingL;
    }

    public int getSize() {
        return size;
    }

    @Override
    public WolfType getType() {
        return this;
    }

    public static BadgeGenerator<WolfType> getBadgeGenerator() {
        return badgeGenerator;
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

    public String getAtlas() {
        return atlas;
    }

    public String getName(int frameNumber) {
        return name+"-"+frameNumber;
    }

    @Override
    public float getCost2() {
        return 0;
    }

    public float getCost3() {
        return cost3;
    }

    public float getCost4() {
        return cost4;
    }

    public float getCost5() {
        return cost5;
    }
}
