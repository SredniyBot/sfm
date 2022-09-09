package com.sfm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SacuraFortune extends Game {
	public static final float SCREEN_WIDTH = 1920f;
	public static final float SCREEN_HEIGHT = 1080f;
	public static float VIEWPORT_LEFT;
	public static float VIEWPORT_RIGHT;

	private MainScreen mMainScreen;

	@Override
	public void create () {
		mMainScreen = new MainScreen();

		setScreen(mMainScreen);
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

}
