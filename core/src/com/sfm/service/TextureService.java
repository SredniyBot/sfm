package com.sfm.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sfm.entity.BadgeType;

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
        assetManager.load("usual/usual.atlas",TextureAtlas.class);
        assetManager.load("sword/sword.atlas",TextureAtlas.class);

        assetManager.load("autoN.png",Texture.class);
        assetManager.load("frames.png",Texture.class);
        assetManager.load("auto.png",Texture.class);
        assetManager.load("background.png",Texture.class);
        assetManager.load("buttons.png",Texture.class);
        assetManager.load("real.png",Texture.class);
        assetManager.load("spinN.png",Texture.class);
        assetManager.load("spin.png",Texture.class);
        assetManager.load("topground.png",Texture.class);
        assetManager.load("supBackground.png",Texture.class);

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


        TextureAtlas atlas = assetManager.get("sword/sword.atlas");
        TextureRegion textureRegion = atlas.findRegion("sword-0");
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
        res.put("autoDis",new TextureRegion(assetManager.<Texture>get("autoNotActive.png"),0,0,218,77));
        res.put("auto",new TextureRegion(assetManager.<Texture>get("autoOn.png"),0,0,218,77));
        res.put("bg",new TextureRegion(assetManager.<Texture>get("background.png"),0,0,1920,1080));
        res.put("btns",new TextureRegion(assetManager.<Texture>get("buttons.png"),0,0,1920,1080));
        res.put("real",new TextureRegion(assetManager.<Texture>get("real.png"),0,0,415,118));
        res.put("spinDis",new TextureRegion(assetManager.<Texture>get("spinNotActive.png"),0,0,309,127));
        res.put("spin",new TextureRegion(assetManager.<Texture>get("spinOn.png"),0,0,309,127));
        res.put("sbg",new TextureRegion(assetManager.<Texture>get("supBackground.png"),0,0,1920,1080));
        res.put("tg",new TextureRegion(assetManager.<Texture>get("topground.png"),0,0,1920,1080));
        res.put("fr",new TextureRegion(assetManager.<Texture>get("frames.png"),0,0,1920,1080));
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
    public static TextureRegion getButtonsTexture(){
        return textureRegionMap.get("btns");
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
    public static TextureRegion getFrameTexture(){
        return textureRegionMap.get("fr");
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

    public static TextureRegion getSupBackground() {
        return textureRegionMap.get("sbg");
    }

    public static TextureRegion getSpinDisTexture() {
        return textureRegionMap.get("spinDis");
    }

    public static TextureRegion getAutoDisTexture() {
        return textureRegionMap.get("autoDis");

    }
}
