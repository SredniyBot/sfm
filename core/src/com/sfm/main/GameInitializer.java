package com.sfm.main;

import com.badlogic.gdx.Game;

import com.sfm.loader.LoaderScreen;
import com.sfm.service.FontService;
import com.sfm.service.MusicService;
import com.sfm.service.SoundService;

public class GameInitializer extends Game implements ScreenSetter{

	public static final float SCREEN_WIDTH = 1920f;
	public static final float SCREEN_HEIGHT = 1080f;
	public static float VIEWPORT_LEFT;
	public static float VIEWPORT_RIGHT;
	public static AndroidInterfaces androidInterfaces;
	private LoaderScreen loaderScreen;

	public GameInitializer(AndroidInterfaces androidInterfaces){
		GameInitializer.androidInterfaces=androidInterfaces;
	}

	@Override
	public void create () {
		FontService.init();
		SoundService.init();
		loaderScreen=new LoaderScreen(new ScreenSwitcher() {
			@Override
			public void switchScreen(ScreenType screenType) {

			}
		});
		loaderScreen.setScreenSetter(this);
		loaderScreen.switchScreen(ScreenType.MAIN_MENU);
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
		loaderScreen.dispose();
		SoundService.dispose();
		MusicService.dispose();
		super.dispose();
	}


}
