package com.sfm;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BackStage extends Stage  {

     BackStage(Viewport viewport) {
        setViewport(viewport);
        addActor(new Background());
    }

}
