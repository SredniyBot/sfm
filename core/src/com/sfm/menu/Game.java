package com.sfm.menu;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfm.main.ScreenSwitcher;
import com.sfm.main.ScreenType;

public enum Game {
    SAKURA("sakura", ScreenType.SAKURA_FORTUNE, true, 153),
    SAKURA1("sakura", ScreenType.SAKURA_FORTUNE, true, 153),
    TOME_OF_RA("ra", ScreenType.MAIN_MENU, true, 187),
    TOME_OF_RA1("ra", ScreenType.MAIN_MENU, true, 187),
    SAKURA2("sakura", ScreenType.SAKURA_FORTUNE, true, 153);
    private final String texture;
    private final ScreenType screenType;
    private boolean opened;
    private final float biasX;

    Game(String texture, ScreenType screenType, boolean opened, float biasX){
        this.texture = texture;
        this.screenType = screenType;

        this.opened = opened;
        this.biasX = biasX;
    }

    public String getTexture() {
        return texture;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public boolean isOpened() {
        return opened;
    }

    public float getBiasX() {
        return biasX;
    }
}
