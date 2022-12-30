package com.sfm.menu.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.service.TextureService;

public class GameChooseField extends Group implements XGettter,WindowShower{

    private float prvX=0;
    private float maxX=0;
    private boolean isPressed=false;
    private final Viewport viewport;
    private final Window window;


    public GameChooseField(Viewport viewport, ScreenSwitcher screenSwitcher){
        this.viewport=viewport;
        window = new Window(viewport);
        Animator animator = new Animator(window);

        addActor(animator);

        for (Game game:Game.values()){
            addActor(new GameActor(viewport, game, screenSwitcher,maxX,0,this, animator));
            maxX+= TextureService.getTextureRegion("menu/menu/menu.atlas",game.getTexture()).getRegionWidth();
        }
    }

    @Override
    public void act(float delta) {
        window.act(delta);
        if (window.getVisible())return;
        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            if (isPressed){
                if(getX()-(prvX-touch.x)>=0){
                    setX(0);
                } else
                    setX(Math.max(getX() - (prvX - touch.x), -maxX + GameInitializer.SCREEN_WIDTH));
            }
            prvX=touch.x;
            isPressed=true;
        }else {
            isPressed=false;
        }
        super.act(delta);
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
