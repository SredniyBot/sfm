package com.sfm.ra.entity;

import com.sfm.main.game_utils.BadgeGenerator;
import com.sfm.main.game_utils.BadgeType;

import java.util.Random;

public enum RaType implements BadgeType<RaType> {
    BIRD(0,1,-1,0,33,0.05f, 0.4f,0.8f, 1.2f, 1.6f, "tom_of_ra/bird/bird.atlas", "bird"),
    BOOK(1,1,-1,0,33,0.05f, 0   ,3f  , 4.5f  , 6f  , "tom_of_ra/book/book.atlas", "book"),
    J   (2,1,-1,0,33,0.05f, 0   ,0.3f, 0.6f, 0.9f, "tom_of_ra/j/j.atlas", "j"),
    K   (3,1,-1,0,33,0.05f, 0   ,0.6f, 1.2f, 1.8f, "tom_of_ra/k/k.atlas", "k"),
    MAN (4,1,-1,0,34,0.05f, 1f,1.5f, 2f, 2.5f, "tom_of_ra/man/man.atlas", "man"),
    Q   (5,1,-1,0,33,0.05f, 0   ,0.4f, 0.8f, 1.2f, "tom_of_ra/q/q.atlas", "q");

    private final int size;
    private final int paddingT;
    private final int paddingL;
    private final int numberOfFrames;
    private final float animationInterval;
    private final float cost2;
    private final float cost3;
    private final float cost4;
    private final float cost5;
    private final String atlas;
    private final String name;
    private final int id;
    public static final BadgeGenerator<RaType> badgeGenerator =new BadgeGenerator<RaType>() {
        @Override
        public RaType getRandom() {
            if(new Random().nextInt(10)==0)return BOOK;
            if(new Random().nextInt(10)==0)return MAN;
            switch (new Random().nextInt(4)){
                case 0:
                    return BIRD;
                case 1:
                    return K;
                case 2:
                    return J;
                default:
                    return Q;
            }
        }

        @Override
        public RaType getRandomOne() {
            return getRandom();
        }
    };

    RaType(int id, int size, int paddingT, int paddingL,
           int numberOfFrames, float animationInterval,
           float cost2,float cost3, float cost4,
           float cost5, String atlas, String name){
        this.id=id;
        this.size=size;
        this.animationInterval=animationInterval;
        this.numberOfFrames=numberOfFrames;
        this.paddingT=paddingT;
        this.paddingL=paddingL;
        this.cost2 = cost2;
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
    public RaType getType() {
        return this;
    }

    public static BadgeGenerator<RaType> getBadgeGenerator() {
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

    public float getCost2() {
        return cost2;
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
