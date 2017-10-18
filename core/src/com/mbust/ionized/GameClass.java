package com.mbust.ionized;

import com.badlogic.gdx.Game;
import com.mbust.ionized.screen.GameScreen;
import com.mbust.ionized.screen.MenuScreen;

public class GameClass extends Game {

	private MenuScreen _menuScreen = new MenuScreen(this);
	private GameScreen _gameScreen = new GameScreen(this);
	
	@Override
	public void create () {

		setScreen(_gameScreen);
		
	}

	@Override
	public void render () {
	
		super.render();
	
	}
	
	@Override
	public void dispose () {

	}
}
