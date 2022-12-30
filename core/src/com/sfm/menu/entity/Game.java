package com.sfm.menu.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.sfm.main.ScreenType;
import com.sfm.service.MoneyService;

public enum Game {
    TOME_OF_RA("Tome of RA","ra","raL", ScreenType.TOM_OF_RA,  146,10),
    SAKURA("Sakura fortune","sakura","sakuraL", ScreenType.SAKURA_FORTUNE,  146,7000),
    WOLF("Wolf treasure","wolf","wolfL", ScreenType.WOLF,  146,15000),
    BLOCKED("","lock","lock", ScreenType.SAKURA_FORTUNE,  146,10000000);
    private final String texture;
    private final String lockTexture;
    private final ScreenType screenType;
    private boolean opened;
    private final float biasX;
    private final int cost;
    private final String name;

    Game(String name,String texture,String lockTexture, ScreenType screenType, float biasX,int cost){
        this.name=name;
        if (screenType == ScreenType.TOM_OF_RA) {
            opened = true;
        }else if(texture.equals("lock")) {
            opened=false;
        }else {
            Preferences preferences = Gdx.app.getPreferences("opened");
            opened=preferences.getBoolean(screenType.name(),false);
        }
        this.cost = cost;
        this.texture = texture;
        this.lockTexture = lockTexture;
        this.screenType = screenType;
        this.biasX = biasX;
    }

    public void open(){
        Preferences preferences = Gdx.app.getPreferences("opened");
        preferences.putBoolean(screenType.name(),true);
        preferences.flush();
        opened=true;
        MoneyService.buy(cost);
    }

    public String getTexture() {
        return texture;
    }

    public String getLockTexture() {
        return lockTexture;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public boolean isOpened() {
        return opened;
    }

    public float getBiasX() {
        return biasX;
    }

    public String getName(){
        return name;
    }

    public int getCost() {
        return cost;
    }
}
