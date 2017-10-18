package com.mbust.ionized;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public final class Utility {
	public AssetManager _assetManager = new AssetManager();
	
	public void loadCharacterTexture(String name) {
		_assetManager.load("character/" + name + ".png", Texture.class);
	}
	
	public void loadEffectsTexture(String name) {
		_assetManager.load("effects/" + name + ".png", Texture.class);
	}
	
	public static Vector2 polarToCart(float dist, float degrees) {
		return new Vector2(dist * MathUtils.cos((float) Math.toRadians(degrees + 270)), dist * MathUtils.sin((float) Math.toRadians(degrees - 270)));
	}
	
	// Position relative to the area
	public static Vector2 gAOrigin() {
		return new Vector2(Config.resolutionWidth / 2 - Config.gameAreaWidth / 2, Config.resolutionHeight / 2 - Config.gameAreaHeight / 2);
	}
	
	// Returns the game area position given by a value from 0 to 1
	public static Vector2 gANPos(float x, float y) {
		return new Vector2(Config.gameAreaWidth * x, Config.gameAreaHeight * y);
	}
}
