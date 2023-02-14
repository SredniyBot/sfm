package com.sfm.main.game_utils;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.sfm.main.GameInitializer;
import com.sfm.main.abstracts.ButtonGuardian;
import com.sfm.service.util.GameSound;
import com.sfm.service.MoneyService;
import com.sfm.service.SoundService;
import com.sfm.service.WinService;

/**
 * Абстрактный класс поля слота, содержит 5 колонок, описывает взаимодействие между ними
 * Особенности слотов расписываются при наследовании от этого класса и реализации абстрактных методов
 * @param <T>
 */
public abstract class Field<T extends BadgeType<T>> extends Group
        implements ResultChecker, ButtonGuardian {

    private final WinService winService =new WinService();

    private final Column<T> c1;
    private final Column<T> c2;
    private final Column<T> c3;
    private final Column<T> c4;
    private final Column<T> c5;

    private boolean locked=false;

    private int finishCount=0;
    private int respinCount=0;

    private boolean autoRespin;
    private final Timer timer;
    private final Timer autoTimer;

    public Field(BadgeGenerator<T> badgeGenerator){
        timer=new Timer();
        autoTimer=new Timer();
        c1=new Column<>(269, 0,this,badgeGenerator);
        c2=new Column<>(547, 0,this,badgeGenerator);
        c3=new Column<>(824, 0,this,badgeGenerator);
        c4=new Column<>(1104,0,this,badgeGenerator);
        c5=new Column<>(1382,0,this,badgeGenerator);
        addActor(c5);
        addActor(c4);
        addActor(c3);
        addActor(c2);
        addActor(c1);
        addActor(winService);
    }

    /**
     * начинает спин, пройдя все проверки и списав деньги
     * @return начался ли спин после вызова
     */
    public boolean startSpin(){
        if (isLocked())return false;
        if (c1.isSpinning() && c2.isSpinning() && c3.isSpinning() && c4.isSpinning() && c5.isSpinning())return false;
        if (MoneyService.startAndCheckForNull()){
            GameInitializer.androidInterfaces.toast("You don't have enough money to make a bet");
            return false;
        }
        spin();
        return true;
    }

    /**
     * начинает спин без проверок
     */
    private void spin() {
        timer.clear();
        locked=true;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                SoundService.loopSound(GameSound.SLOTSTART);
            }
        },0.7f);

        c1.spinTo(30);
        c2.spinTo(45);
        c3.spinTo(60);
        c4.spinTo(75);
        c5.spinTo(90);
    }

    @Override
    public void onSpinStopped() {
        if(checkAllColumnsFinished())return;
        SoundService.stopLoopSound(GameSound.SLOTSTART);
        boolean sound;
        sound=c1.balanceBadge();
        sound=c2.balanceBadge()||sound;
        sound=c3.balanceBadge()||sound;
        sound=c4.balanceBadge()||sound;
        sound=c5.balanceBadge()||sound;
        if (sound){
            SoundService.playSound(GameSound.OPENSLOT);
        }
    }

    /**
     * Метод, выполняющийся при остановке всех пяти слотов.
     * Здесь подсчитываются очки, проигрывается анимация,
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onSpinFixed() {
        if(checkAllColumnsFinished())return;
        Array<Badge<T>> r1 = c1.getResult();
        Array<Badge<T>> r2 = c2.getResult();
        Array<Badge<T>> r3 = c3.getResult();
        Array<Badge<T>> r4 = c4.getResult();
        Array<Badge<T>> r5 = c5.getResult();

        Array<Array<Badge<T>>> l= new Array<>();
        // ниже формирование всевозможных линий победы
        l.add(new Array<>(Array.with(r1.get(1),r2.get(1),r3.get(1),r4.get(1),r5.get(1))));
        l.add(new Array<>(Array.with(r1.get(0),r2.get(0),r3.get(0),r4.get(0),r5.get(0))));
        l.add(new Array<>(Array.with(r1.get(2),r2.get(2),r3.get(2),r4.get(2),r5.get(2))));
        l.add(new Array<>(Array.with(r1.get(0),r2.get(1),r3.get(2),r4.get(1),r5.get(0))));
        l.add(new Array<>(Array.with(r1.get(2),r2.get(1),r3.get(0),r4.get(1),r5.get(2))));
        l.add(new Array<>(Array.with(r1.get(1),r2.get(0),r3.get(0),r4.get(0),r5.get(1))));
        l.add(new Array<>(Array.with(r1.get(1),r2.get(2),r3.get(2),r4.get(2),r5.get(1))));
        l.add(new Array<>(Array.with(r1.get(0),r2.get(0),r3.get(1),r4.get(2),r5.get(2))));
        l.add(new Array<>(Array.with(r1.get(2),r2.get(2),r3.get(1),r4.get(0),r5.get(0))));

        int currentRespins=0;
        float cost=0;
        for (int i=0;i<l.size;i++){
            BadgeType<T> badgeType =findFirstBadgeType(l.get(i));
            float currentCost = getCost(badgeType,l.get(i));
            int cr=getRespins(l.get(i));
            if (cr>currentRespins)currentRespins=cr;
            if (currentCost > 0) {
                for (int k=0;k<badgeCount(badgeType,l.get(i));k++){
                    l.get(i).get(k).startAnimation();
                }
                winService.showLine(i);
                cost+=currentCost;
            }
        }

        if (currentRespins>0){
            SoundService.playSound(GameSound.LINE,1);
            SoundService.playSound(GameSound.RESPIN,1);
            winService.showRespin(currentRespins);
        }
        respinCount+=currentRespins;
        if (cost>0) {
            SoundService.playSound(GameSound.LINE,1);
            MoneyService.win(cost);
        }
        if (respinCount>0){
            respinCount--;
            if (cost>0)timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    spin();
                }
            },3f);
            else timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    spin();
                }
            },1f);
            return;
        }
        float delay;
        if (cost>0)delay=2.5f;
        else delay=0.5f;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                MoneyService.commit();
                locked=false;
            }
        },delay);

        if(autoRespin){
            autoTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    startSpin();
                }
            },delay+1.5f);
        }

    }
    private boolean checkAllColumnsFinished(){
        finishCount++;
        if(finishCount>=5){
            finishCount=0;
            return false;
        }
        return true;
    }


    private float getCost(BadgeType<T> firstBadge,Array<Badge<T>> badges){
        int i=badgeCount(firstBadge,badges);
        if (i==2) return firstBadge.getCost2();
        if (i==3) return firstBadge.getCost3();
        if (i==4) return firstBadge.getCost4();
        if (i==5) return firstBadge.getCost5();
        else return 0;
    }

    @Override
    public final boolean setAutoRespin(boolean autoRespin) {
        if (isLocked()&&autoRespin)return true;
        this.autoRespin = autoRespin;
        autoTimer.clear();
        if (autoRespin){
            if (isLocked())return true;
            if (c1.isSpinning() && c2.isSpinning() && c3.isSpinning() && c4.isSpinning() && c5.isSpinning())return true;
            if (MoneyService.startAndCheckForNull()){
                GameInitializer.androidInterfaces.toast("You don't have enough money to make a bet");
                locked=false;
                return false;
            }
            spin();
        }
        return true;
    }

    @Override
    public void increaseBet(boolean inc){
        if (isLocked())return;
        MoneyService.changeBet(inc);
    }

    @Override
    public final boolean isLocked() {
        return locked;
    }

    @Override
    public final void cancel() {
        autoTimer.clear();
        locked=false;
        autoRespin=false;
        MoneyService.commit();
    }

    /**
     * @param badges состояние поля при его остановке
     * @return количество респинов в зависимости от того, что выпало
     */
    protected abstract int getRespins(Array<Badge<T>> badges);

    /**
     * @param badges состояние поля при его остановке
     * @return первый тип бейджа, который попался (относительно которого надо строить линию)
     */
    public abstract BadgeType<T> findFirstBadgeType(Array<Badge<T>> badges);

    /**
     * @param badgeType первый элемент в линии
     * @param badges линия
     * @return бонус поля, получающийся при его остановке
     */
    public abstract int badgeCount(BadgeType<T> badgeType, Array<Badge<T>> badges);

}
