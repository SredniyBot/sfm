package com.sfm.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sfm.main.GameInitializer;
import com.sfm.service.util.money.MoneySkin;
import com.sfm.service.util.money.SlotMoneySkin;

/**
 * Сервис взаимодействия с деньгами
 */
public class MoneyService {

    private static int money;
    private static int bet;
    private static int win;
    
    private static final MoneySkin moneySkin;
    private static final SlotMoneySkin slotMoneySkin;

    static {// Инициация денег, по умолчанию 5000
        Preferences prefs = Gdx.app.getPreferences("money");
        money=prefs.getInteger("money",5000);
        bet=Math.min(100,money);
        win=0;
        MoneySkin ms=new MoneySkin(money,415);
        MoneySkin bs=new MoneySkin(bet,360);
        MoneySkin ws=new MoneySkin(win,360);
        ms.setPosition(400, GameInitializer.SCREEN_HEIGHT-34);
        bs.setPosition(215,GameInitializer.SCREEN_HEIGHT-952-60);
        ws.setPosition(684,GameInitializer.SCREEN_HEIGHT-952-60);
        slotMoneySkin =new SlotMoneySkin(ms,bs,ws);
        moneySkin=new MoneySkin(money,415);
        moneySkin.setPosition(200, GameInitializer.SCREEN_HEIGHT-34);
    }

    /**
     * Изменение ставки
     * @param grow рост или понижение
     */
    public static void changeBet(boolean grow){
        if (money==0){
            bet=0;
            slotMoneySkin.updateBetSkin(bet);
            return;
        }
        if (grow){
            if(bet<10){
                bet+=1;
            }else {
                bet+=10;
            }
            bet=Math.min(200,Math.min(money,bet));
        }else {
            if (bet<=10&&bet>1){
                bet-=1;
            }else if (bet>10){
                if (bet%10==0) bet-=10;
                else bet-=bet%10;
            }
        }
        slotMoneySkin.updateBetSkin(bet);
    }

    /**
     * Старт спина
     * @return false если ставка 0
     */
    public static boolean startAndCheckForNull(){
        if (bet==0)return true;
        commit();
        money-=bet;
        moneySkin.updateMoney(money);
        slotMoneySkin.updateMoneySkin(money);
        slotMoneySkin.renewWinSkin();
        return false;
    }

    /**
     * Завершение спина, присваивание выигрыша игроку
     */
    public static void commit(){
        money+=win;
        win=0;
        bet=Math.min(bet,money);
        slotMoneySkin.updateBetSkin(bet);
        moneySkin.updateMoney(money);
        slotMoneySkin.updateMoneySkin(money);
        saveMoney();
    }

    public static void win(float c){
        int k=(int)Math.ceil(c*bet);
        win+=k;
        slotMoneySkin.increaseWinSkin(k);
    }

    /**
     * Покупка
     * @param cost стоимость покупки
     */
    public static void buy(int cost){
        money-=cost;
        moneySkin.updateMoney(money);
        slotMoneySkin.updateMoneySkin(money);
        saveMoney();
    }

    /**
     * Сохранение счета
     */
    private static void saveMoney(){
        Preferences prefs = Gdx.app.getPreferences("money");
        prefs.putInteger("money",money);
        prefs.flush();
    }

    /**
     * Скин денег для меню
     * @return стейдж
     */
    public static MoneySkin getMoneySkin(){
        return moneySkin;
    }
    /**
     * Скин денег для слота
     * @return стейдж
     */
    public static SlotMoneySkin getSlotMoneySkin(){
        return slotMoneySkin;
    }

    public static void setMoneyTexture(TextureRegion textureRegion){
        moneySkin.setMoneyTexture(textureRegion);
        slotMoneySkin.setMoneyTexture(textureRegion);
    }

    public static int getMoney() {
        return money;
    }
}
