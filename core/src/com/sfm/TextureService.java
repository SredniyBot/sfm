package com.sfm;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class TextureService {

    private static AssetManager assetManager;
    public static Map<String ,TextureRegion> textureRegionMap;
    public static Array<TextureRegion> textureRegionMapLady;
    public static Array<TextureRegion> textureRegionMapCoinB;
    public static Array<TextureRegion> textureRegionMapCoinG;
    public static Array<TextureRegion> textureRegionMapCoinGr;
    public static Array<TextureRegion> textureRegionMapDed;
    public static Array<TextureRegion> textureRegionMapMan;
    public static Array<TextureRegion> textureRegionMapSword;
    public static Array<TextureRegion> textureRegionMapLines;
    public static Array<TextureRegion> textureRegionMapRespins;

    public static void init(){
        dispose();
        assetManager=new AssetManager();
        assetManager.load("back.png",Texture.class);
        assetManager.load("real.png",Texture.class);
        assetManager.load("auto.png",Texture.class);
        assetManager.load("bg.png",Texture.class);
        assetManager.load("ramka.png",Texture.class);
        assetManager.load("bgtogether2.png",Texture.class);
        assetManager.load("tg.png",Texture.class);

        for (int i=1;i<=3;i++){
            assetManager.load("respin/"+i+".png",Texture.class);
        }

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
        textureRegionMapRespins=getRespinsArray();
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

    private static Array<TextureRegion> getRespinsArray(){
        Array<TextureRegion> temp=new Array<>();
        temp.add(new TextureRegion(assetManager.<Texture>get("respin/1.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("respin/2.png")));
        temp.add(new TextureRegion(assetManager.<Texture>get("respin/3.png")));
        return temp;
    }
    public static Map<String ,TextureRegion> fillTextureRegionMap(){
        Map<String,TextureRegion> res=new HashMap<>();
        res.put("bg",new TextureRegion(assetManager.<Texture>get("bg.png"),0,0,1920,1080));
        res.put("ramka",new TextureRegion(assetManager.<Texture>get("ramka.png"),0,0,1920,1080));
        res.put("tg",new TextureRegion(assetManager.<Texture>get("bgtogether2.png"),0,0,1920,1080));
        res.put("tg2",new TextureRegion(assetManager.<Texture>get("tg.png"),0,0,1920,1080));
        res.put("real",new TextureRegion(assetManager.<Texture>get("real.png"),0,0,415,118));
        res.put("auto",new TextureRegion(assetManager.<Texture>get("auto.png"),0,0,218,77));
        return res;
    }

    public static TextureRegion getLineRegion(int number){
        return textureRegionMapLines.get(number);
    }

    public static TextureRegion getRespinRegion(int number){
        return textureRegionMapRespins.get(number);
    }


    public static TextureRegion getBgTexture(){
        return textureRegionMap.get("bg");
    }
    public static TextureRegion getRamkaTexture(){
        return textureRegionMap.get("ramka");
    }
    public static TextureRegion getTgTexture(){
        return textureRegionMap.get("tg");
    }
    public static TextureRegion getTg2Texture(){
        return textureRegionMap.get("tg2");
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
    public static void dispose() {
        if (assetManager!=null)
            assetManager.dispose();
    }
}
