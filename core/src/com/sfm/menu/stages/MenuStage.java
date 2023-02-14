package com.sfm.menu.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.menu.entity.GameChooseField;
import com.sfm.menu.slider.Slider;


public class MenuStage extends Stage {

    MenuStage(Viewport viewport, ScreenSwitcher switchAction){
        setViewport(viewport);
        addActor(new GameChooseField(viewport,switchAction));

    }
}
