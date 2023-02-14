package com.sfm.sakura.entity;

import com.sfm.main.game_utils.BadgeGenerator;
import com.sfm.main.game_utils.BadgeType;

import java.util.Random;

/**
 * Реализация BadgeType в виде enum. Перечисляются все карточки с параметрами, указваются отступы с
 * двух сторон, размеры карточек, цена, и положение в assets
 *
 */
public enum SakuraType implements BadgeType<SakuraType> {
    LADY        (0,3,45,63,  45,0.04f, 6   , 6   , 6, "sakura/lady/lady.atlas", "lady"),
    SWORD       (1,2,25,30,  56,0.04f, 3f  , 4f  , 5f, "sakura/sword/sword.atlas", "sword"),
    DED         (2,1,35 ,45 ,36,0.05f, 3f  , 4.5f, 7f, "sakura/ded/ded.atlas", "ded"),
    MAN         (3,1,35 ,45 ,36,0.05f, 1.5f, 2.5f, 4f, "sakura/man/man.atlas", "man"),
    COIN_GRAY   (4,1,27 ,38 ,60,0.05f, 0.3f, 0.6f, 1.2f, "sakura/silver/silver.atlas", "silver"),
    COIN_GREEN  (5,1,27 ,38 ,60,0.05f, 0.6f, 1f, 1.4f, "sakura/green/green.atlas", "green"),
    COIN_BROWN  (6,1,27 ,38 ,60,0.05f, 0.15f, 0.3f, 0.6f, "sakura/bronze/bronse.atlas", "bronse");

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
    public static final BadgeGenerator<SakuraType> badgeGenerator =new BadgeGenerator<SakuraType>() {
        @Override
        public SakuraType getRandom() {
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

        @Override
        public SakuraType getRandomOne() {
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
    };

    SakuraType(int id, int size, int paddingT, int paddingL,
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
    public SakuraType getType() {
        return this;
    }

    public static BadgeGenerator<SakuraType> getBadgeGenerator() {
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
