package com.sfm.service.util.money;

public interface MoneyObserver {

    void updateMoney(int money);
    void increaseMoneySlowly(int money);
    void renew();
}
