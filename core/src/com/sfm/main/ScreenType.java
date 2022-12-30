package com.sfm.main;

import com.badlogic.gdx.Screen;

public enum ScreenType {
    MAIN_MENU(null),
    TOM_OF_RA(null),
    SAKURA_FORTUNE(null),
    WOLF(null);

    private final Screen screen;
    ScreenType(Screen screen){
        this.screen=screen;
    }
    public Screen getScreen() {
        return screen;
    }
}
