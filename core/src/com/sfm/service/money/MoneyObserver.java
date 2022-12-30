package com.sfm.service.money;

public interface MoneyObserver {

    void updateMoney(int money);
    void increaseMoneySlowly(int money);
    void renew();
}
