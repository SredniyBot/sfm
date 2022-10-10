package com.sfm.sakura.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;


import java.util.Comparator;


public class Column extends Group {

    private Array<Badge> badges;

    private SpinMoment spinMoment;

    private final ResultChecker resultChecker;
    private int finalOffset=0;
    private final int sizeOfBadge = 255;
    private float v;

    public Column(int x, float v, ResultChecker resultChecker) {
        this.v = v;
        this.resultChecker = resultChecker;
        spinMoment=SpinMoment.STOP;
        setX(x);
        complementColumn();
    }

    private void complementColumn(){
        Array<Badge> newBadges=new Array<>();
        int i=0;
        int bias=0;
        if (badges==null){
            badges=new Array<>();
        }else {
            badges.sort(new Comparator<Badge>() {
                @Override
                public int compare(Badge o1, Badge o2) {
                    return Integer.compare(o1.getBias(),o2.getBias());
                }
            });
            bias = badges.get(badges.size-1).getBias()+sizeOfBadge;
            i=badges.size;
        }
        while (badges.size+newBadges.size<10){
            BadgeType badgeType = BadgeType.getRandom();
            if (badgeType == BadgeType.LADY) {
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias));
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias + sizeOfBadge));
                newBadges.add(new Badge(badgeType, i++, bias + sizeOfBadge * 2));
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias + sizeOfBadge * 5));
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias + sizeOfBadge * 6));
                bias += sizeOfBadge * 7;
            } else if (badgeType == BadgeType.SWORD) {
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias));
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias + sizeOfBadge));
                newBadges.add(new Badge(badgeType, i++, bias + sizeOfBadge * 2));
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias + sizeOfBadge * 4));
                newBadges.add(new Badge(BadgeType.getRandomOne(), i++, bias + sizeOfBadge * 5));
                bias += sizeOfBadge * 6;
            } else {
                newBadges.add(new Badge(badgeType, i++, bias));
                bias += sizeOfBadge;
            }
        }
        for (Badge badge : newBadges) {
            badges.add(badge);
            addActor(badge);
            if (badge.getBadgeType() == BadgeType.LADY || badge.getBadgeType() == BadgeType.SWORD)
                badge.setZIndex(1000);
            else badge.setZIndex(1);
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (badges.get(0).getY()+badges.get(0).getHeight()+getY()<0){
            badges.get(0).remove();
            badges.removeIndex(0);
            complementColumn();
        }
        switch (spinMoment){
            case STOP:
                v=0;
                break;
            case START:
                if(v>=200){
                    spinMoment=SpinMoment.CONTINUE;
                    v=200;
                }else {
                    v+=10;
                }
                setY(getY()+v*delta);
                break;
            case CONTINUE:
                if(v<-5000){
                    v=-5000;
                }else {
                    v-=100;
                }
                if (-getY()>=finalOffset+15){
                    spinMoment=SpinMoment.END;
                }
                setY(getY()+v*delta);
                break;
            case END:
                setY(getY()+(-getY()-finalOffset)*delta*10);
                if(Math.abs(-getY()-finalOffset)<=1){
                    setY(0);
                    for (Badge badge:badges){
                        badge.deleteBiasDifferent(-finalOffset);
                    }
                    finalOffset=0;
                    spinMoment=SpinMoment.STOP;
                    if (resultChecker!=null)resultChecker.finishedSpin();
                }
                break;
            case CORRECTION:
                setY(getY()+(-getY()-finalOffset)*delta*2.5f);

                if(Math.abs(-getY()-finalOffset)<=1){
                    setY(0);
                    for (Badge badge:badges){
                        badge.deleteBiasDifferent(-finalOffset);
                    }
                    finalOffset=0;
                    spinMoment=SpinMoment.STOP;
                    if (resultChecker!=null)resultChecker.sakuraMoved();
                }
                break;
        }

    }

    public void moveSacura(){
        badges.sort(new Comparator<Badge>() {
            @Override
            public int compare(Badge o1, Badge o2) {
                return Integer.compare(o1.getBias(),o2.getBias());
            }
        });
        for (Badge badge:badges) {
            if (badge.getBadgeType() == BadgeType.LADY && badge.getBias() >= -sizeOfBadge * 2 && badge.getBias() <= sizeOfBadge * 2) {
                if (badge.getBias() != 0) {
                    spinMoment = SpinMoment.CORRECTION;
                    finalOffset = badge.getBias();
                    badge.startAnimation();
                } else {
                    badge.startAnimation();
                    if (resultChecker!=null)resultChecker.sakuraMoved();
                }
                return;
            }
        }
        if (resultChecker!=null)resultChecker.sakuraMoved();
    }

    public Array<Badge> getResult(){
        Array<Badge> result=new Array<>();
        badges.sort(new Comparator<Badge>() {
            @Override
            public int compare(Badge o1, Badge o2) {
                return Integer.compare(o1.getBias(),o2.getBias());
            }
        });
        for (Badge badge:badges){
            if(badge.getBadgeType()==BadgeType.LADY&&badge.getBias()==0){
                result.clear();
                result.add(badge);
                result.add(badge);
                result.add(badge);
                return result;
            }
//            if(badge.getBias()+badge.getBadgeType().getSize()*sizeOfBadge<=sizeOfBadge*3&&
//                    badge.getBias()+badge.getBadgeType().getSize()*sizeOfBadge>0){
            for (int i=1;i<=badge.getBadgeType().getSize();i++){
                if(i*sizeOfBadge+badge.getBias()>0&&i*sizeOfBadge+badge.getBias()<=sizeOfBadge*3){
                    result.add(badge);
                    if (result.size==3){
                        result.reverse();
                        return result;
                    }
                }
            }
//            }
        }
        result.reverse();
        return result;
    }

    public boolean isSpinning(){
        return spinMoment == SpinMoment.STOP;
    }
    public void spinTo(int spin){
        finalOffset= spin*sizeOfBadge;
        spinMoment=SpinMoment.START;
    }


    public enum SpinMoment {
        STOP,
        START,
        CONTINUE,
        END,
        CORRECTION
    }
}