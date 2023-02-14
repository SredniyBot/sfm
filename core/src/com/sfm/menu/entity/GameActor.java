package com.sfm.menu.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.service.util.GameSound;
import com.sfm.service.MoneyService;
import com.sfm.service.SoundService;
import com.sfm.service.TextureService;

public class GameActor extends Group implements XGettter{

    private final Game game;
    private final XGettter xGettter;
    private final Animator animator;
    private final PlayButton playButton;

    private final Timer timer;

    private boolean fall=false;
    private boolean fallDir=false;
    private boolean rotateDir=false;

    private float rotate=0f;
    private float fallY=1;
    private float fallA=10f;
    private float fallX=0;

    private float sx=0;
    private float sy=0;
    private float sr=0;
    private boolean shake=false;
    private boolean shakeDir=true;
    private boolean shakeDirS=true;



    GameActor(Viewport viewport, final Game game, final ScreenSwitcher runnable, float x, float y,
              XGettter xGettter, Animator animator, CursorCatcher cursorCatcher){
        setX(x);
        setY(y);
        timer=new Timer();
        this.game=game;
        this.xGettter=xGettter;
        this.animator=animator;
        playButton=new PlayButton(viewport, new Runnable() {
            @Override
            public void run() {
                runnable.switchScreen(game.getScreenType());}},
                game.getBiasX(),199,330,125,this,cursorCatcher);

        if (game.isOpened()){
            addActor(playButton);
        }else if(!game.getLockTexture().equals("lock")){
            addActor(new UnlockButton(viewport,new BoolRunnable() {
                @Override
                public boolean run() {
                    SoundService.playSound(GameSound.BUTTON);
                    return unlock();}},
                    game.getBiasX(),323-130f,470,220, game.getCost(), this,cursorCatcher));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (game.isOpened()){
            batch.draw(TextureService.getTextureRegion("menu/menu/menu.atlas", game.getTexture()),getX(),getY());
        }else {
            batch.draw(TextureService.getTextureRegion("menu/menu/menu.atlas",
                    game.getLockTexture()),getX()+sx,getY()+sy,
                    TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionWidth()/2f,TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionHeight()/2f,
                    TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionWidth(),TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionHeight(),1,1,sr);
        }
        if (fall){
            batch.draw(TextureService.getTextureRegion("menu/menu/menu.atlas",
                    game.getLockTexture()),getX()+fallX,getY()+fallY,
                    TextureService.getTextureRegion("menu/menu/menu.atlas",
                    game.getLockTexture()).getRegionWidth()/2f,TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionHeight()/2f,
                    TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionWidth(),TextureService.getTextureRegion("menu/menu/menu.atlas",
                            game.getLockTexture()).getRegionHeight(),1,1,rotate);
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (shake){
            if (shakeDir){
                sx+=(25-sx)*delta*25;
                if (Math.abs(25-sx)<1)shakeDir=false;
            }else {
                sx+=(-25-sx)*delta*25;
                if (Math.abs(-25-sx)<1)shakeDir=true;
            }
            if (shakeDirS){
                sr+=(2-sr)*delta*20;
                if (Math.abs(2-sr)<0.3)shakeDirS=false;
            }else {
                sr+=(-2-sr)*delta*20;
                if (Math.abs(-2-sr)<0.3)shakeDirS=true;
            }
//            sx+=new RandomXS128().nextFloat()*4-2;
//            sy+=new RandomXS128().nextFloat()*4-2;
//            sr+=new RandomXS128().nextFloat()*4-2;
//            sx=Math.min(Math.max(sx,-10),10);
//            sy=Math.min(Math.max(sy,-10),10);
//            sr=Math.min(Math.max(sr,-10),10);
        }

        if (fall){
            fallX+=delta*250;
            if (!fallDir){
                fallA-=0.1*delta*100;
                fallY+=delta*fallA*70;
                if (fallY>70){
                    fallA=10f;
                    fallDir=true;
                }
            }else {
                if (rotateDir){
                    rotate+=delta*25;
                    if (rotate>5){
                        rotateDir=false;
                    }
                }else {
                    rotate-=delta*25;
                    if (rotate<-5){
                        rotateDir=true;
                    }
                }
                fallA+=0.5*delta*100;
                fallY-=Math.abs(delta*50*fallA);
                if (fallY<-1000){
                    fall=false;

                }
            }

        }
    }

    @Override
    public float getBiasedX() {
        return xGettter.getBiasedX()+getX();
    }
    private boolean unlock(){
        if (MoneyService.getMoney()< game.getCost())return false;
        startAnimation();
        return true;
    }

    private void startAnimation(){
        animator.setX(getX()+TextureService.getTextureRegion("menu/menu/menu.atlas",
                game.getLockTexture()).getRegionWidth()/2f);
        animator.startAnimation(game.getName());
        shake=true;

        final Timer.Task second = new Timer.Task() {
            @Override
            public void run() {
                shake=false;
                sr=0;
                sx=0;
                sy=0;
                game.open();
                fall=true;
                SoundService.playSound(GameSound.OPENSLOT);
                addActor(playButton);
            }
        };

        final Timer.Task first2 = new Timer.Task() {
            @Override
            public void run() {
                shake=false;
                sr=0;
                sx=0;
                sy=0;
                timer.scheduleTask(second,0.5f);
            }
        };
        final Timer.Task first1 = new Timer.Task() {
            @Override
            public void run() {
                shake=true;
                timer.scheduleTask(first2,0.5f);
            }
        };
        final Timer.Task first = new Timer.Task() {
            @Override
            public void run() {
                shake=false;
                sr=0;
                sx=0;
                sy=0;
                timer.scheduleTask(first1,0.5f);
            }
        };
        timer.scheduleTask(first,0.5f);
    }


}
