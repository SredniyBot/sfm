package com.sfm.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

/**
 * Сервис ассетов
 */
public class TextureService {

    private static AssetManager assetManager;

    /**
     * Нужно проинициализировать сервис передав в него атлас из текстур
     * @param atlasPaths атлас
     */
    public static void init(List<String> atlasPaths){
        if (assetManager!=null)assetManager.dispose();
        assetManager=new AssetManager();
        for (String s:atlasPaths){
            assetManager.load(s,TextureAtlas.class);
        }
    }

    /**
     * @param atlas атлас из которого мы хотим получить текстуру (заранее проиницализированный)
     * @param textureName название текстуры, которое прописано в атласе
     * @return текстура
     */
    public static TextureRegion getTextureRegion(String atlas,String textureName){
        return assetManager.<TextureAtlas>get(atlas).findRegion(textureName);
    }


    public static boolean isUpdated(){
        return assetManager.update();
    }
    public static float getProgress(){
        return assetManager.getProgress();
    }
}
