package com.sfm.menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.GameInitializer;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.abstracts.Background;
import com.sfm.service.TextureService;


public class MenuStage extends Stage {

    MenuStage(Viewport viewport, ScreenSwitcher switchAction){
        setViewport(viewport);
        addActor(new GameChooseField(viewport,switchAction));

    }
}
