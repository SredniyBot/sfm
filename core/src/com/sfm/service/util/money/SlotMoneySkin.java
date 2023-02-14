package com.sfm.service.util.money;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;

public class SlotMoneySkin extends Group {
    private final MoneySkin betSkin;
    private final MoneySkin moneySkin;
    private final MoneySkin winSkin;

    public SlotMoneySkin(MoneySkin moneySkin, MoneySkin betSkin, MoneySkin winSkin) {
        this.betSkin = betSkin;
        this.moneySkin = moneySkin;
        this.winSkin = winSkin;
        addActor(moneySkin);
        addActor(betSkin);
        addActor(winSkin);
    }
    public void updateBetSkin(int money){
        betSkin.updateMoney(money);
    }

    public void updateMoneySkin(int money){
        moneySkin.updateMoney(money);
    }

    public void increaseWinSkin(int money){
        winSkin.increaseMoneySlowly(money);
    }
    public void renewWinSkin(){
        winSkin.renew();
    }
    public void setMoneyTexture(TextureRegion textureRegion){
        moneySkin.setMoneyTexture(textureRegion);
    }
}