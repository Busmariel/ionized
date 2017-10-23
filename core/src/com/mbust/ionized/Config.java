package com.mbust.ionized;

import com.badlogic.gdx.math.Vector2;

public class Config {
	public static final int MAX_FRAMESKIP = 5;
	public static final int TICKS_PER_SECOND = 25;
	public static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;

	public static int resolutionWidth = 1024;
	public static int resolutionHeight = 860;
	public static int gameAreaWidth = 600;
	public static int gameAreaHeight = 800;
	
	public static float playerConstSpeed = 500;
	public static float playerBoundingRadius = 16.0f;
	public static float playerHitRadius = 4.0f;
	public static float playerStartingFireDelay = 0.05f;
}
