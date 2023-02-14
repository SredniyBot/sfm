package com.sfm.service.util;

public enum GameMusic {
    RA("tom.mp3"),
    SAKURA("sakura.mp3"),
    MENU("menu.mp3"),
    WOLF("wolf.mp3");

    private final String text;
    GameMusic(String text){
        this.text=text;
    }

    public String getText(){
        return text;
    }
}
