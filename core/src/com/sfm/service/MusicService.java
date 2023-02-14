package com.sfm.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.sfm.service.util.GameMusic;


import java.util.HashMap;

/**
 * Сервис музыки.
 */
public class MusicService {

    private static HashMap<GameMusic,Music> tracks;

    /**
     * Для проигрывания надо проинициализировать треки, предыдущие треки удалятся из памяти
     * @param mus вся музыка, которую надо воспроизвести
     */
    public static void init(GameMusic... mus) {
        dispose();
        tracks=new HashMap<>();
        for (GameMusic musInfo:mus){
            Music music=Gdx.audio.newMusic(Gdx.files.internal("music/"+musInfo.getText()));
            music.setVolume(0.5f);
            tracks.put(musInfo,music);
        }
    }

    /**
     * Начать воспроизведение в цикле
     * @param gameMusic передаваемая музыка для воспроизведения
     */
    public static void startLoop(GameMusic gameMusic){
        tracks.get(gameMusic).setLooping(true);
        tracks.get(gameMusic).play();
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
