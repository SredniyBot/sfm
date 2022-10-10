package com.sfm.main.money;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.sfm.sakura.entity.ButtonActivator;

public class MoneyTracker extends Group  implements WinListener, ButtonActivator {
    private final Bet bet;
    private final Money money;
    private final Win win;



    private boolean locked=false;
    public MoneyTracker(){
        money=new Money();
        money.setScore(5000);
        bet=new Bet();
        win=new Win(this);
        addActor(money);
        addActor(bet);
        addActor(win);
    }

    public void setNX(float x){
        money.setSX(x);
    }
    public void showBet(boolean b){
        win.setVisible(b);
        bet.setVisible(b);
    }

    public void startSpin(){
        if (locked)return;
        locked=true;
        win.renew();
        money.addMoney(-bet.getBet());

    }


    public void win(float k){
        win.addWin(k*bet.getBet());
    }

    public void increaseBet(int inc) {
        if (locked)return;
        if (bet.getBet()+inc>money.getScore())bet.setBet(money.getScore());
        else if (bet.getBet() + inc < 0) bet.setBet(0);
        else bet.changeBet(inc);
    }

    @Override
    public void win(int win) {
        money.addMoney(win);
    }

    public void endSpin() {
        if (bet.getBet()>money.getScore())bet.setBet(money.getScore());
        locked=false;
    }

    @Override
    public boolean isSpinActive() {
        return !locked;
    }

    @Override
    public boolean isAutoActive() {
        return !locked;
    }
}
