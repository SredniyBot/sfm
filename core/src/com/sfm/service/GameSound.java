
package com.sfm.service;

public enum GameSound {

    AUTOPLAY("AUTOPLAY.wav",0.5f),
    BUTTON("BUTTON.mp3",1f),
    LINE("LINE.mp3",1f),
    RESPIN("RESPIN.mp3",1f),
    SLOTSTART("SLOTSTART.mp3",0.5f),
    SLOTSTOP1("SLOTSTOP1.mp3",0.5f),
    SLOTSTOP2("SLOTSTOP2.mp3",0.5f),
    SLOTSTOP3("SLOTSTOP3.mp3",0.5f),
    SLOTSTOP4("SLOTSTOP4.mp3",0.5f),
    SLOTSTOP5("SLOTSTOP5.mp3",0.5f),
    SPIN("SPIN.mp3",0.5f),
    OPENSLOT("OPENSLOT.mp3",0.3f),
    PLAYMENU("PLAYMENU.mp3",0.5f),
    WIN("WIN.mp3",1f);

    private final String path;
    private final float volume;

    GameSound(String path,float volume){
        this.path = path;
        this.volume=volume;
    }

    public float getVolume() {
        return volume;
    }

    public String getPath() {
        return "sounds/"+path;
    }
}
