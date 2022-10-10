package com.sfm.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sfm.main.money.MoneyTracker;
import com.sfm.menu.MenuScreen;
import com.sfm.sakura.stages.SakuraScreen;
import com.sfm.service.FontService;

public class GameInitializer extends Game implements ScreenSwitcher{
	public static final float SCREEN_WIDTH = 1920f;
	public static final float SCREEN_HEIGHT = 1080f;
	public static float VIEWPORT_LEFT;
	public static float VIEWPORT_RIGHT;

	private static MoneyTracker moneyTracker;
	private Screen currentScreen;
	@Override
	public void create () {
		FontService.init();
		moneyTracker = new MoneyTracker();
		currentScreen=new MenuScreen(this);
		setScreen(currentScreen);
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
		Screen newScreen;
		switch (screenType){
			case MAIN_MENU:
				newScreen=new MenuScreen(this);
				setScreen(newScreen);
				currentScreen.dispose();
				currentScreen=newScreen;
				break;
			case SAKURA_FORTUNE:
				newScreen=new SakuraScreen(this);
				setScreen(newScreen);
				currentScreen.dispose();
				currentScreen=newScreen;
				break;
		}

	}

	public static MoneyTracker getMoneyTracker(){
		return moneyTracker;
	}
}
