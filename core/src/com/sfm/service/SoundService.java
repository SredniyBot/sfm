package com.sfm.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.sfm.service.util.GameSound;

import java.util.HashMap;

/**
 * Сервис воспроизведения звуков
 */
public class SoundService {

    private static HashMap<GameSound, Sound> sounds;

    /**
     * Проинициализровать при запуске приложения
     */
    public static void init(){
        dispose();
        sounds=new HashMap<>();
        for (GameSound gameSound:GameSound.values()){
            try {
                sounds.put(gameSound,Gdx.audio.newSound(Gdx.files.internal(gameSound.getPath())));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Воспроизведение звук один раз
     * @param sound звук для воспроизведения
     */
    public static void playSound(GameSound sound){
        try {
            sounds.get(sound).play(0.7f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Воспроизведение звук один раз с задаваемой громкостью
     * @param sound звук для воспроизведения
     */

    public static void playSound(GameSound sound,float volume){
        try {
        sounds.get(sound).play(volume);
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    /**
     * Воспроизведение звука в цикле
     * @param sound звук для воспроизведения
     */

    public static void loopSound(GameSound sound){
        try {
        stopLoopSound(sound);
        sounds.get(sound).loop(sound.getVolume());
        }catch (Exception e){
        e.printStackTrace();
        }
    }
    /**
     * Остановка воспроизводимого в цикле звука
     * @param sound звук для остановления
     */

    public static void stopLoopSound(GameSound sound){
        try {
        sounds.get(sound).stop();
        }catch (Exception e){
        e.printStackTrace();
        }
    }
    public static void dispose() {
        if (sounds!=null)
            for (Sound sound:sounds.values()){
                sound.dispose();
            }
    }

}
