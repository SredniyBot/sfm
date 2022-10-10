package com.sfm.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.service.TextureService;

import java.util.ArrayList;
import java.util.List;

public class GameChooseField extends Group implements XGettter{

    private float prvX=0;
    private boolean isPressed=false;
    private Viewport viewport;
    GameChooseField(Viewport viewport, ScreenSwitcher screenSwitcher){
        this.viewport=viewport;
        float bias=0;
        for (Game game:Game.values()){

            addActor(new GameActor(viewport, game, screenSwitcher,bias,0,this));
            bias+= TextureService.getTextureRegion("menu/menu/menu.atlas",game.getTexture()).getRegionWidth();
        }
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            if (isPressed){
                setX(getX()-(prvX-touch.x));
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
        super.draw(batch, parentAlpha);
    }

    @Override
    public float getBiasedX() {
        return getX();
    }
}
