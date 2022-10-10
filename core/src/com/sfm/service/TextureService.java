package com.sfm.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

public class TextureService {

    private static AssetManager assetManager;

    public void init(List<String> atlasPaths){
        if (assetManager!=null)assetManager.dispose();
        assetManager=new AssetManager();
        for (String s:atlasPaths){
            assetManager.load(s,TextureAtlas.class);
        }
        assetManager.finishLoading();
    }

    public static TextureRegion getTextureRegion(String atlas,String textureName){
        return assetManager.<TextureAtlas>get(atlas).findRegion(textureName);
    }

}
