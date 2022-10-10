package com.sfm.main;

import com.badlogic.gdx.Screen;

public enum ScreenType {
    MAIN_MENU(null),
    SAKURA_FORTUNE(null);

    private final Screen screen;
    ScreenType(Screen screen){
        this.screen=screen;
    }
    public Screen getScreen() {
        return screen;
    }
}
