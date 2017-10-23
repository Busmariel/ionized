package com.mbust.ionized.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Config;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.bullet.Bullet;
import com.mbust.ionized.screen.GameScreen;

public class Player extends DynamicBody {
	private GameScreen _gameScreen;
	private PlayerController _playerController;
	private Texture _texture;
	
	private boolean _bulletFire;
	private float _fireDelay, _fireCounter;
	
	public Player(GameScreen gameScreen) {
		_gameScreen = gameScreen;
		_playerController = new PlayerController(this);
		Gdx.input.setInputProcessor(_playerController);
		getHitCircle().radius = Config.playerBoundingRadius;
		setMovementType(MovementType.MOVEMENT_CONSTANT);
		_bulletFire = false;
		_fireDelay = Config.playerStartingFireDelay;
	}
	
	// Update logic
	public void update(float delta) {
		super.update(delta);
		_playerController.update();
		
		if (_bulletFire) {
			_fireCounter += delta;
			if (_fireCounter >= _fireDelay) {
				Bullet bullet = _gameScreen.getCurrentLevel().spawnBullet(getPosition());
				bullet.setRadius(10.0f);
				bullet.setVelocity(0.0f, 20.0f);
				_fireCounter = 0;
			}
		}
	}
	
	// Draw graphics
	public void draw() {
		_gameScreen.getRenderer().setColor(Color.WHITE);
		
		if (_texture != null) {
			_gameScreen.getRenderer().draw(_texture, Utility.gAOrigin().x + getPosition().x - _texture.getWidth() / 2, Utility.gAOrigin().y + getPosition().y - _texture.getHeight() / 2);
		}
		//System.out.println(getPosition().y);
		//_gameScreen.getRenderer().circle(Utility.gAOrigin().x + getPosition().x, Utility.gAOrigin().y + getPosition().y, Config.playerBoundingRadius);
		//_gameScreen.getRenderer().circle(Utility.gAOrigin().x + getPosition().x, Utility.gAOrigin().y + getPosition().y, getHitCircle().radius);
	}
	
	// Kills the player
	public void end() {
		
	}
	
	// ------------------------------------------------

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

	public void setFiringState(boolean value) { 
		_bulletFire = value;
	}
	
	public void setTexture(String path) {
		_texture = _gameScreen.getAssetManager().get(path, Texture.class);
	}
}
