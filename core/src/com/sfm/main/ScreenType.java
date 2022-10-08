package com.sfm.main;

import com.badlogic.gdx.Screen;
import com.sfm.stages.MainScreen;

public enum ScreenType {
    MAIN_MENU(null),
    SACURA_FORTUNE(null);

    private final Screen screen;
    ScreenType(Screen screen){
        this.screen=screen;
    }
    public Screen getScreen() {
        return screen;
    }
}
