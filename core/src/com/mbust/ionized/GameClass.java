package com.mbust.ionized;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mbust.ionized.screen.GameScreen;
import com.mbust.ionized.screen.MenuScreen;

public class GameClass extends Game {
	private AssetManager _assetManager;
	private MenuScreen _menuScreen;
	private GameScreen _gameScreen;
	
	private boolean _loadingComplete = false;
	
	@Override
	public void create () {
		_assetManager = new AssetManager();
		_menuScreen = new MenuScreen(this);
		_gameScreen	= new GameScreen(this);
		
		// We load the assets needed for the level.
		_gameScreen.getAssetManager().load("enemy/drone1.png", Texture.class);
		_gameScreen.getAssetManager().load("bullet/bullet1.png", Texture.class);
		_gameScreen.getAssetManager().load("bullet/bullet_anim_1.png", Texture.class);
		_gameScreen.getAssetManager().load("player/player.png", Texture.class);
	}

	@Override
	public void render () {
		super.render();
		if (!_loadingComplete && _assetManager.update()) {
			setScreen(_gameScreen);
			_loadingComplete = true;
		}
	}
	
	@Override
	public void dispose () {
		_menuScreen.dispose();
		_gameScreen.dispose();
		_assetManager.dispose();
	}
	
	public AssetManager getAssetManager() {
		return _assetManager;
	}
}
