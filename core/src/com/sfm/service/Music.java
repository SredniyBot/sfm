package com.sfm.service;

public enum Music {
    RA("tom.mp3"),
    SAKURA("sakura.mp3"),
    MENU("menu.mp3"),
    WOLF("wolf.mp3");

    private final String text;
    Music(String text){
        this.text=text;
    }

    public String getText(){
        return text;
    }
}
