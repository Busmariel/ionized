package com.mbust.ionized.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Config;
import com.mbust.ionized.Utility;
import com.mbust.ionized.screen.GameScreen;

public class Player extends Character {
	private GameScreen _gameScreen;
	private PlayerController _playerController;

	public Player(GameScreen gameScreen) {
		_gameScreen = gameScreen;
		_playerController = new PlayerController(this);
		Gdx.input.setInputProcessor(_playerController);
		getHitCircle().radius = Config.playerBoundingRadius;
		setMovementType(MovementType.MOVEMENT_CONSTANT);
	}
	
	public void render(float delta) {
		super.update(delta);
		_playerController.update();
		
		_gameScreen.getRenderer().circle(Utility.gAOrigin().x + getPosition().x, Utility.gAOrigin().y + getPosition().y, Config.playerBoundingRadius);
		_gameScreen.getRenderer().circle(Utility.gAOrigin().x + getPosition().x, Utility.gAOrigin().y + getPosition().y, getHitCircle().radius);
	}

	// Input events
	public void onInputDirection(boolean[] dir, int inputs) {  
		setConstSpeed(Config.playerConstSpeed);
		if (dir[0] && dir[2]) {
			setConstDirection(-1, 1); 
		} else if (dir[0] && dir[3]) {
			setConstDirection(-1, -1); 
		} else if (dir[1] && dir[2]) {
			setConstDirection(1, 1); 
		} else if (dir[1] && dir[3]) {
			setConstDirection(1, -1); 
		} else if (inputs == 1) {
			if (dir[0]) setConstDirection(-1, 0); 
			if (dir[1]) setConstDirection(1, 0); 
			if (dir[2]) setConstDirection(0, 1); 
			if (dir[3]) setConstDirection(0, -1); 
		} else {
			setConstSpeed(0.0f);
		}
	}

	public void onInputShoot() { 
		_gameScreen().getCurrentLevel()
	}
}
