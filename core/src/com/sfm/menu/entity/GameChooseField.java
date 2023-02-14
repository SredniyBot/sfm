package com.sfm.menu.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.menu.slider.Slider;
import com.sfm.service.TextureService;

public class GameChooseField extends Group implements XGettter,WindowShower{

    private float prvX=0;
    private float maxX=600;
    private boolean isPressed=false;
    private final Viewport viewport;
    private final Window window;

    private final CursorCatcher cursorCatcher;

    public GameChooseField(Viewport viewport, ScreenSwitcher screenSwitcher){
        this.viewport=viewport;
        window = new Window(viewport);
        cursorCatcher=new CursorCatcher();
        Animator animator = new Animator(window);

        addActor(animator);



        for (Game game:Game.values()){
            addActor(new GameActor(viewport, game, screenSwitcher,maxX,0,this, animator,cursorCatcher));
            maxX+= TextureService.getTextureRegion("menu/menu/menu.atlas",game.getTexture()).getRegionWidth();
        }
        addActor(new Slider(viewport,cursorCatcher,this));
    }

    @Override
    public void act(float delta) {
        window.act(delta);
        if (window.getVisible())return;

        super.act(delta);

        if(!isPressed&&cursorCatcher.isCaught())return;

        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            cursorCatcher.setCaught(true);
            if (isPressed){

                if(getX()-(prvX-touch.x)>=0)
                    setX(0);
                else
                    setX(Math.max(getX() - (prvX - touch.x), -maxX + GameInitializer.SCREEN_WIDTH));
            }

            prvX=touch.x;
            isPressed=true;
        }else {
            cursorCatcher.setCaught(false);
            isPressed=false;
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        window.draw(batch,parentAlpha);
        if (window.getVisible())return;
        super.draw(batch, parentAlpha);
    }

    @Override
    public float getBiasedX() {
        return getX();
    }

    public void open(String name){
        window.show(name);
    }
}
