package com.sfm.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.service.TextureService;

public class GameActor extends Group implements XGettter{

    private final Game game;
    private final XGettter xGettter;

    GameActor(Viewport viewport, final Game game, final ScreenSwitcher runnable, float x, float y,XGettter xGettter){
        setX(x);
        setY(y);
        this.game=game;
        this.xGettter=xGettter;
        if (game.isOpened())
            addActor(new PlayButton(viewport, new Runnable() {
            @Override
            public void run() {
                   runnable.switchScreen(game.getScreenType());
                }
        }, game.getTexture(),game.getBiasX(),323-105f,331,127,this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(TextureService.getTextureRegion("menu/menu/menu.atlas", game.getTexture()),getX(),getY());
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public float getBiasedX() {
        return xGettter.getBiasedX()+getX();
    }
}
