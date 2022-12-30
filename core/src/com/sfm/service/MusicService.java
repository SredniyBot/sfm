package com.sfm.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


import java.util.HashMap;

public class MusicService {

    private static HashMap<com.sfm.service.Music,Music> tracks;

    public static void init(com.sfm.service.Music... texts) {
        dispose();
        tracks=new HashMap<>();
        for (com.sfm.service.Music text:texts){
            Music music=Gdx.audio.newMusic(Gdx.files.internal("music/"+text.getText()));
            music.setVolume(0.5f);
            tracks.put(text,music);
        }
    }

    public static void startLoop(com.sfm.service.Music text){
        tracks.get(text).setLooping(true);

        tracks.get(text).play();
    }

    public static void dispose(){
        if (tracks!=null){
            for (Music music:tracks.values()){
                music.stop();
                music.dispose();
            }
            tracks.clear();
        }
    }
}
