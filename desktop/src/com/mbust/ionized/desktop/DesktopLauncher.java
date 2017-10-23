package com.mbust.ionized.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mbust.ionized.Config;
import com.mbust.ionized.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Config.resolutionWidth;
		config.height = Config.resolutionHeight;
		config.foregroundFPS = 0;
		config.vSyncEnabled = true;
		//config.samples = 16;
		new LwjglApplication(new GameClass(), config);
	}
}
 