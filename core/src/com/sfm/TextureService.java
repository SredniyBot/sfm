package com.sfm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class TextureService {

    public static Array<TextureRegion> greenCoinTextures;
    private static Texture bgTexture;
    private static Texture tgTexture;
    private static Texture greenCoinTexture;
    public static Map<String ,TextureRegion> textureRegionMap;

    public static void init(){
        dispose();
        bgTexture =new Texture(Gdx.files.internal("bg.png"));
        tgTexture =new Texture(Gdx.files.internal("bgtogether2.png"));
        greenCoinTexture =new Texture(Gdx.files.internal("coin.png"));
        textureRegionMap=fillTextureRegionMap();
        greenCoinTextures=textureRegionsInit(greenCoinTexture,339,321,61,10);
    }


    public static Map<String ,TextureRegion> fillTextureRegionMap(){
        Map<String,TextureRegion> res=new HashMap<>();
        res.put("bg",new TextureRegion(bgTexture,0,0,1920,1080));
        res.put("tg",new TextureRegion(tgTexture,0,0,1920,1080));
        return res;
    }



    private static Array<TextureRegion> textureRegionsInit(Texture booksTexture,int w,int h,int count,int dest){
        Array<TextureRegion> textures = new Array<>();
        for (int i=0;i<count;i++){
            textures.add(new TextureRegion(booksTexture,(w+dest)*i,0,w,h));
        }
        return textures;
    }



    public static TextureRegion getBgTexture(){
        return textureRegionMap.get("bg");
    }
    public static TextureRegion getTgTexture(){
        return textureRegionMap.get("tg");
    }
    public static TextureRegion getCoinTextureById(int id){
        id=id%greenCoinTextures.size;
        return greenCoinTextures.get(id);
    }

    public static void dispose() {
        if (bgTexture!=null)
            bgTexture.dispose();
        if (tgTexture!=null)
            tgTexture.dispose();
        if (greenCoinTexture!=null)
            greenCoinTexture.dispose();
    }
}
