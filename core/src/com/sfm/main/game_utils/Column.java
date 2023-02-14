package com.sfm.main.game_utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.sfm.service.util.GameSound;
import com.sfm.service.SoundService;


import java.util.Comparator;

/**
 * Класс столбика в слоте,
 * @param <T> тип карточки в слоте, из которой собирается столбик
 */
public class Column<T extends BadgeType<T>> extends Group {

    /**
     * Список карточек идущих подряд
     */
    private Array<Badge<T>> badges;

    private SpinMoment spinMoment;

    private final ResultChecker resultChecker;
    private int finalOffset=0;
    private final int sizeOfBadge = 255;
    private float v;
    private final BadgeGenerator<T> badgeGenerator;

    public Column(int x, float v, ResultChecker resultChecker,BadgeGenerator<T> badgeGenerator) {
        this.v = v;
        this.resultChecker = resultChecker;
        this.badgeGenerator=badgeGenerator;
        spinMoment=SpinMoment.STOP;
        setX(x);
        complementColumn();
    }

    /**
     * Добавляет элементы в колонку
     */
    private void complementColumn(){
        Array<Badge<T>> newBadges=new Array<>();
        int i=0;
        int bias=0;
        if (badges==null){
            badges=new Array<>();
        }else {
            badges.sort(new Comparator<Badge<T>>() {
                @Override
                public int compare(Badge o1, Badge o2) {
                    return Integer.compare(o1.getBias(),o2.getBias());
                }
            });
            bias = badges.get(badges.size-1).getBias()+sizeOfBadge;
            i=badges.size;
        }
        while (badges.size+newBadges.size<10){
            BadgeType<T> badgeType = badgeGenerator.getRandom();
            if (badgeType.getSize() == 3) {
                newBadges.add(new Badge<>( badgeGenerator.getRandomOne(), i++, bias));
                newBadges.add(new Badge<>( badgeGenerator.getRandomOne(), i++, bias + sizeOfBadge));
                newBadges.add(new Badge<>(badgeType.getType(), i++, bias + sizeOfBadge * 2));
                newBadges.add(new Badge<>(badgeGenerator.getRandomOne(), i++, bias + sizeOfBadge * 5));
                newBadges.add(new Badge<>(badgeGenerator.getRandomOne(), i++, bias + sizeOfBadge * 6));
                bias += sizeOfBadge * 7;
            } else if (badgeType.getSize() == 2) {
                newBadges.add(new Badge<>(badgeGenerator.getRandomOne(), i++, bias));
                newBadges.add(new Badge<>(badgeGenerator.getRandomOne(), i++, bias + sizeOfBadge));
                newBadges.add(new Badge<>(badgeType.getType(), i++, bias + sizeOfBadge * 2));
                newBadges.add(new Badge<>(badgeGenerator.getRandomOne(), i++, bias + sizeOfBadge * 4));
                newBadges.add(new Badge<>(badgeGenerator.getRandomOne(), i++, bias + sizeOfBadge * 5));
                bias += sizeOfBadge * 6;
            } else {
                newBadges.add(new Badge<T>(badgeType.getType(), i++, bias));
                bias += sizeOfBadge;
            }
        }
        for (Badge<T> badge : newBadges) {
            badges.add(badge);
            addActor(badge);
            if (badge.getBadgeType().getSize() == 3 || badge.getBadgeType().getSize() == 2)
                badge.setZIndex(1000);
            else badge.setZIndex(1);
        }
    }


    /**
     * отрисовка каждой карточки по отдельности
     * @param batch автосгенерированнный параметр
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *           children.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    /**
     * метод движения колонки. выполняется во время игры на столько быстро на сколько возможно
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        if (badges.get(0).getY()+badges.get(0).getHeight()+getY()<0){// удаление карточек, которые вышли за поле
            badges.get(0).remove();
            badges.removeIndex(0);
            complementColumn();
        }
        switch (spinMoment){// у колонки несколько фаз движений. тут описан процесс перехода из одной фазы в другую
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
                    SoundService.playSound(GameSound.SLOTSTOP1);
                    spinMoment=SpinMoment.END;
                }
                setY(getY()+v*delta);
                break;
            case END:
                setY(getY()+(-getY()-finalOffset)*delta*10);
                if(Math.abs(-getY()-finalOffset)<=1){
                    setY(0);
                    for (Badge<T> badge:badges){
                        badge.deleteBiasDifferent(-finalOffset);
                    }
                    finalOffset=0;
                    spinMoment=SpinMoment.STOP;
                    if (resultChecker!=null)resultChecker.onSpinStopped();
                }
                break;
            case CORRECTION:
                setY(getY()+(-getY()-finalOffset)*delta*2.5f);

                if(Math.abs(-getY()-finalOffset)<=1){
                    setY(0);
                    for (Badge<T> badge:badges){
                        badge.deleteBiasDifferent(-finalOffset);
                    }
                    finalOffset=0;
                    spinMoment=SpinMoment.STOP;
                    if (resultChecker!=null)resultChecker.onSpinFixed();
                }
                break;
        }

    }

    /**
     * выравнивает wild, при необходимости
     * @return true если есть что выравнивать
     */
    public boolean balanceBadge(){
        badges.sort(new Comparator<Badge<T>>() {
            @Override
            public int compare(Badge o1, Badge o2) {
                return Integer.compare(o1.getBias(),o2.getBias());
            }
        });
        for (Badge<T> badge:badges) {
            if (badge.getBadgeType().getSize()== 3 && badge.getBias() >= -sizeOfBadge * 2 && badge.getBias() <= sizeOfBadge * 2) {
                if (badge.getBias() != 0) {
                    spinMoment = SpinMoment.CORRECTION;
                    finalOffset = badge.getBias();
                    badge.startAnimation();
                } else {
                    badge.startAnimation();
                    if (resultChecker!=null)resultChecker.onSpinFixed();
                }
                return true;
            }
        }
        if (resultChecker!=null)resultChecker.onSpinFixed();
        return false;
    }

    /**
     * @return состояние в котором находится колонка
     */
    public Array<Badge<T>> getResult(){
        Array<Badge<T>> result=new Array<>();
        badges.sort(new Comparator<Badge<T>>() {
            @Override
            public int compare(Badge o1, Badge o2) {
                return Integer.compare(o1.getBias(),o2.getBias());
            }
        });
        for (Badge<T> badge:badges){
            if(badge.getBadgeType().getSize()==3&&badge.getBias()==0){
                result.clear();
                result.add(badge);
                result.add(badge);
                result.add(badge);
                return result;
            }
            for (int i=1;i<=badge.getBadgeType().getSize();i++){
                if(i*sizeOfBadge+badge.getBias()>0&&i*sizeOfBadge+badge.getBias()<=sizeOfBadge*3){
                    result.add(badge);
                    if (result.size==3){
                        result.reverse();
                        return result;
                    }
                }
            }
        }
        result.reverse();
        return result;
    }

    public boolean isSpinning(){
        return spinMoment != SpinMoment.STOP;
    }

    /**
     * @param spin количество ячеек, на которые надо сдвинуть колонку
     */
    public void spinTo(int spin){
        finalOffset= spin*sizeOfBadge;
        spinMoment=SpinMoment.START;
    }

    private enum SpinMoment {
        STOP,
        START,
        CONTINUE,
        END,
        CORRECTION
    }
}