package com.sfm;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage  {

    public GameStage(Viewport viewport) {
        setViewport(viewport);
        addActor(new Background());
        addActor(new AnimatedCoin());
        addActor(new TopGround());
    }



}
