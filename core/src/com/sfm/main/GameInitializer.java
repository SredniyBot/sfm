package com.sfm.main;

import com.badlogic.gdx.Game;
import com.sfm.sakura.stages.SakuraScreen;

public class GameInitializer extends Game implements ScreenSwitcher{
	public static final float SCREEN_WIDTH = 1920f;
	public static final float SCREEN_HEIGHT = 1080f;
	public static float VIEWPORT_LEFT;
	public static float VIEWPORT_RIGHT;

	@Override
	public void create () {
		setScreen(new SakuraScreen());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		float aspectRatio = (float) width / height;
		float viewportWidth = SCREEN_HEIGHT * aspectRatio;
		VIEWPORT_LEFT = (SCREEN_WIDTH - viewportWidth) / 2;
		VIEWPORT_RIGHT = VIEWPORT_LEFT + viewportWidth;
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void switchScreen(ScreenType screenType) {
		setScreen(screenType.getScreen());
	}
}
