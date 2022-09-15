package com.sfm;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class TextureService {

    private static AssetManager assetManager;
    public static Array<TextureRegion> greenCoinTextures;
    public static Map<String ,TextureRegion> textureRegionMap;


    public static Array<TextureRegion> textureRegionMapLady;
    public static Array<TextureRegion> textureRegionMapCoinB;
    public static Array<TextureRegion> textureRegionMapCoinG;
    public static Array<TextureRegion> textureRegionMapCoinGr;
    public static Array<TextureRegion> textureRegionMapDed;
    public static Array<TextureRegion> textureRegionMapMan;
    public static Array<TextureRegion> textureRegionMapSword;
    public static Array<TextureRegion> textureRegionMapLines;

    public static void init(){
        dispose();
        assetManager=new AssetManager();
        assetManager.load("back.png",Texture.class);
        assetManager.load("coinB.png",Texture.class);
        assetManager.load("coinG.png",Texture.class);
        assetManager.load("coinGr.png",Texture.class);
        assetManager.load("ded.png",Texture.class);
        assetManager.load("lady.png",Texture.class);
        assetManager.load("man.png",Texture.class);
        assetManager.load("sword.png",Texture.class);
        assetManager.load("spin.png",Texture.class);
        assetManager.load("real.png",Texture.class);
        assetManager.load("auto.png",Texture.class);
        assetManager.load("bgtogether2.png",Texture.class);

        for (int i=1;i<=9;i++){
            assetManager.load("lines/"+i+".png",Texture.class);

        }

        for (int i=1;i<=12;i++){
            assetManager.load("ded/"+i+".png",Texture.class);
            assetManager.load("man/"+i+".png",Texture.class);
        }
        for (int i=1;i<=15;i++){
            assetManager.load("lady/"+i+".png",Texture.class);
        }
        for (int i=1;i<=19;i++){
            assetManager.load("sword/"+i+".png",Texture.class);
        }
        for (int i=1;i<=20;i++){
            assetManager.load("coinbronze/"+i+".png",Texture.class);
            assetManager.load("coingreen/"+i+".png",Texture.class);
            assetManager.load("coingrey/"+i+".png",Texture.class);
        }

        assetManager.finishLoading();

        textureRegionMapLady    =getArrayFromAssets(BadgeType.LADY.getNumberOfFrames(),"lady",391,867,11);
        textureRegionMapCoinB   =getArrayFromAssets(BadgeType.COIN_BROWN.getNumberOfFrames(),"coinbronze",322,321,27);
        textureRegionMapCoinG   =getArrayFromAssets(BadgeType.COIN_GREEN.getNumberOfFrames(),"coingreen",322,321,27);
        textureRegionMapCoinGr  =getArrayFromAssets(BadgeType.COIN_GRAY.getNumberOfFrames(),"coingrey",322,321,27);
        textureRegionMapDed     =getArrayFromAssets(BadgeType.DED.getNumberOfFrames(),"ded",322,321,27);
        textureRegionMapMan     =getArrayFromAssets(BadgeType.MAN.getNumberOfFrames(),"man",322,321,27);
        textureRegionMapSword   =getArrayFromAssets(BadgeType.SWORD.getNumberOfFrames(),"sword",321,685,8);
        textureRegionMapLines=getLinesArray();
        textureRegionMap=fillTextureRegionMap();
    }

    private static Array<TextureRegion> getArrayFromAssets(int badgeQuantity,String name,int width,int height,int slide){
        Array<TextureRegion> temp=new Array<>();
        for (int i=0;i<badgeQuantity;i++){
            temp.add(new TextureRegion(assetManager.<Texture>get(name+"/"+(i/3+1)+".png"),
                    (i%3)*(width+slide),0,width,height));
            System.out.println(i/3+1+" "+i%3);
        }
        return temp;
    }


    private static Array<TextureRegion> getLinesArray(){
        Array<TextureRegion> temp=new Array<>();
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/1.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/2.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/3.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/4.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/5.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/6.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/7.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/8.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("lines/9.png")));
        return temp;
    }
    public static Map<String ,TextureRegion> fillTextureRegionMap(){
        Map<String,TextureRegion> res=new HashMap<>();
        res.put("bg",new TextureRegion(assetManager.<Texture>get("back.png"),0,0,1920,1080));
        res.put("tg",new TextureRegion(assetManager.<Texture>get("bgtogether2.png"),0,0,1920,1080));
        res.put("coinB",new TextureRegion(assetManager.<Texture>get("coinB.png"),0,0,256,256));
        res.put("coinG",new TextureRegion(assetManager.<Texture>get("coinG.png"),0,0,256,256));
        res.put("coinGr",new TextureRegion(assetManager.<Texture>get("coinGr.png"),0,0,256,256));
        res.put("ded",new TextureRegion(assetManager.<Texture>get("ded.png"),0,0,256,256));
        res.put("lady",new TextureRegion(assetManager.<Texture>get("lady.png"),0,0,345,818));
        res.put("man",new TextureRegion(assetManager.<Texture>get("man.png"),0,0,256,256));
        res.put("sword",new TextureRegion(assetManager.<Texture>get("sword.png"),0,0,302,541));
        res.put("spin",new TextureRegion(assetManager.<Texture>get("spin.png"),0,0,309,127));
        res.put("real",new TextureRegion(assetManager.<Texture>get("real.png"),0,0,415,118));
        res.put("auto",new TextureRegion(assetManager.<Texture>get("auto.png"),0,0,218,77));
        return res;
    }



    private static Array<TextureRegion> textureRegionsInit(Texture booksTexture,int w,int h,int count,int dest){
        Array<TextureRegion> textures = new Array<>();
        for (int i=0;i<count;i++){
            textures.add(new TextureRegion(booksTexture,(w+dest)*i,0,w,h));
        }
        return textures;
    }

    public static TextureRegion getLineRegion(int number){
        return textureRegionMapLines.get(number);
    }


    public static TextureRegion getBgTexture(){
        return textureRegionMap.get("bg");
    }
    public static TextureRegion getTgTexture(){
        return textureRegionMap.get("tg");
    }
    public static TextureRegion getSpinTexture(){
        return textureRegionMap.get("spin");
    }
    public static TextureRegion getAutoTexture(){
        return textureRegionMap.get("auto");
    }
    public static TextureRegion getRealTexture(){
        return textureRegionMap.get("real");
    }
    public static TextureRegion getCoinTextureById(int id){
        id=id%greenCoinTextures.size;
        return greenCoinTextures.get(id);
    }

    public static TextureRegion getBadgeTexture(BadgeType badgeType,int frameNumber){
        switch (badgeType){
            case LADY:
                return textureRegionMapLady.get(frameNumber);
            case SWORD:
                return textureRegionMapSword.get(frameNumber);
            case DED:
                return textureRegionMapDed.get(frameNumber);
            case MAN:
                return textureRegionMapMan.get(frameNumber);
            case COIN_GRAY:
                return textureRegionMapCoinGr.get(frameNumber);
            case COIN_GREEN:
                return textureRegionMapCoinG.get(frameNumber);
            default:
                return textureRegionMapCoinB.get(frameNumber);
        }
    }
    public static boolean isReady(){
        return assetManager.update();
    }
    public static void dispose() {
        if (assetManager!=null)
            assetManager.dispose();
    }
}
