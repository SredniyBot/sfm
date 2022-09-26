package com.sfm.entity;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.sfm.entity.money.MoneyTracker;
import com.sfm.service.WinService;


public class Field extends Group implements ResultChecker {

    private final Column c1=new Column(269, 0,this);
    private final Column c2=new Column(547, 0,this);
    private final Column c3=new Column(824, 0,this);
    private final Column c4=new Column(1104,0,this);
    private final Column c5=new Column(1382,0,this);
    private final WinService winService =new WinService();

    private boolean startingRespin=false;
    private boolean endRespin=false;
    private float backTimer=0;
    private int finishCount=0;
    private int respinCount=0;
    private final MoneyTracker moneyTracker;

    public Field(MoneyTracker moneyTracker){
        this.moneyTracker=moneyTracker;
        addActor(c5);
        addActor(c4);
        addActor(c3);
        addActor(c2);
        addActor(c1);
        addActor(winService);
    }

    public void spin(boolean respin) {
        if (c1.isSpinning() && c2.isSpinning() && c3.isSpinning() && c4.isSpinning() && c5.isSpinning()){
            moneyTracker.startSpin();
            c1.spinTo(30);
            c2.spinTo(45);
            c3.spinTo(60);
            c4.spinTo(75);
            c5.spinTo(90);
        }

    }

    @Override
    public void finishedSpin() {
        finishCount++;
        if (finishCount>=5){
            finishCount=0;
            c1.moveSacura();
            c2.moveSacura();
            c3.moveSacura();
            c4.moveSacura();
            c5.moveSacura();
        }

    }

    @Override
    public void sacuraMoved() {
        finishCount++;
        if (finishCount >= 5) {
            finishCount=0;
            Array<Badge> r1 = c1.getResult();
            Array<Badge> r2 = c2.getResult();
            Array<Badge> r3 = c3.getResult();
            Array<Badge> r4 = c4.getResult();
            Array<Badge> r5 = c5.getResult();

            Array<Array<Badge>> l= new Array<>();
            l.add(new Array<>(new Badge[]{r1.get(1),r2.get(1),r3.get(1),r4.get(1),r5.get(1)}));
            l.add(new Array<>(new Badge[]{r1.get(0),r2.get(0),r3.get(0),r4.get(0),r5.get(0)}));
            l.add(new Array<>(new Badge[]{r1.get(2),r2.get(2),r3.get(2),r4.get(2),r5.get(2)}));
            l.add(new Array<>(new Badge[]{r1.get(0),r2.get(1),r3.get(2),r4.get(1),r5.get(0)}));
            l.add(new Array<>(new Badge[]{r1.get(2),r2.get(1),r3.get(0),r4.get(1),r5.get(2)}));
            l.add(new Array<>(new Badge[]{r1.get(1),r2.get(0),r3.get(0),r4.get(0),r5.get(1)}));
            l.add(new Array<>(new Badge[]{r1.get(1),r2.get(2),r3.get(2),r4.get(2),r5.get(1)}));
            l.add(new Array<>(new Badge[]{r1.get(0),r2.get(0),r3.get(1),r4.get(2),r5.get(2)}));
            l.add(new Array<>(new Badge[]{r1.get(2),r2.get(2),r3.get(1),r4.get(0),r5.get(0)}));
            int currentRespins=0;
            float cost=0;
            for (int i=0;i<l.size;i++){
                float currentCost = getCost(l.get(i));
                int cr=getRespins(l.get(i));
                if (cr>currentRespins)currentRespins=cr;
                if (currentCost > 0) {
                    for (int k=0;k<l.get(i).size;k++){
                        if (l.get(i).get(k).getBadgeType()==findFirstBadgeType(l.get(i))||
                                l.get(i).get(k).getBadgeType()== BadgeType.LADY){
                            l.get(i).get(k).startAnimation();
                        }
                        else break;
                    }
                    winService.showLine(i);
                    cost+=currentCost;
                }
            }

            if (currentRespins>0){
                winService.showRespin(currentRespins);
            }
            respinCount+=currentRespins;
            if (cost>0) moneyTracker.win(cost);
            if (respinCount>0){
                respinCount--;
                startingRespin=true;
                backTimer=1;
                return;
            }
            moneyTracker.endSpin();
        }

    }


    private float getCost(Array<Badge> badges){
        BadgeType badgeType=findFirstBadgeType(badges);
        int i=badgeCount(badgeType,badges);
        float s=0;
        if (i>=3){
            switch (badgeType){
                case LADY:
                    return 6;
                case SWORD:
                    if(i==3) {
                        s = 0.5f;
                    }else if (i==4){
                        s=1f;
                    }else if (i==5){
                        s=2f;
                    }
                    return s;
                case DED:
                    if(i==3)s=2f;
                    else if (i==4) s=4f;
                    else if (i==5) s=6f;
                    return s;
                case MAN:
                    if(i==3)s=1f;
                    else if (i==4) s=2f;
                    else if (i==5) s=3f;
                    return s;
                case COIN_GRAY:
                    if(i==3)s=0.4f;
                    else if (i==4) s=0.5f;
                    else if (i==5) s=1.2f;
                    return s;
                case COIN_GREEN:
                    if(i==3)s=0.6f;
                    else if (i==4) s=1.2f;
                    else if (i==5) s=1.8f;
                    return s;
                case COIN_BROWN:
                    if(i==3)s=0.3f;
                    else if (i==4) s=0.6f;
                    else if (i==5) s=0.9f;
                    return s;
            }
        }
        return 0;
    }
    private int getRespins(Array<Badge> badges){
        BadgeType badgeType=findFirstBadgeType(badges);
        int i=badgeCount(badgeType,badges);
        if (badgeType==BadgeType.SWORD) {
            if (i >= 3) {
                if (i == 3) {
                    return 1;
                } else if (i == 4) {
                    return 2;
                } else if (i == 5) {
                    return 3;
                }
            }
        }
        return 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (backTimer>-10) backTimer-=delta;
        if(startingRespin&&backTimer<=0){
            if (winService.isReady()) {
                startingRespin=false;
                spin(true);
            }
        }
//        if(endRespin&&backTimer<=0){
//            if (winService.isReady()) {
//                endRespin=true;
//                moneyTracker.endSpin();
//            }
//        }
    }

    private BadgeType findFirstBadgeType(Array<Badge> badges){
        for (Badge badge:badges){
            if (badge.getBadgeType()!=BadgeType.LADY)return badge.getBadgeType();
        }
        return BadgeType.LADY;
    }
    private int badgeCount(BadgeType badgeType,Array<Badge> badges){
        int i=0;
        for (Badge badge:badges){
            if (badge.getBadgeType()==badgeType||badge.getBadgeType()==BadgeType.LADY)
                i++;
            else return i;
        }
        return i;
    }
}
