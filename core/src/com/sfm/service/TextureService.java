package com.sfm.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

public class TextureService {

    private static AssetManager assetManager;

    public static void init(List<String> atlasPaths){
        if (assetManager!=null)assetManager.dispose();
        assetManager=new AssetManager();
        for (String s:atlasPaths){
            assetManager.load(s,TextureAtlas.class);
        }
    }

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
