package com.sfm;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidAudio;
import com.badlogic.gdx.backends.android.AsynchronousAndroidAudio;
import com.sfm.main.AndroidInterfaces;
import com.sfm.main.GameInitializer;

public class AndroidLauncher extends AndroidApplication implements AndroidInterfaces {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGyroscope=false;
		config.useAccelerometer=false;
		config.useCompass=false;
		config.useImmersiveMode = true;
		initialize(new GameInitializer(this), config);
	}

	@Override
	public AndroidAudio createAudio(Context context, AndroidApplicationConfiguration config) {
		return new AsynchronousAndroidAudio(context, config);
	}

	@Override
	public void toast(final String t) {
		handler.post(() -> Toast.makeText(getContext(), t, Toast.LENGTH_SHORT).show());
	}
}
