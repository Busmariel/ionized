package com.mbust.ionized.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Config;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.bullet.Bullet;
import com.mbust.ionized.entity.components.AnimationComponent;
import com.mbust.ionized.entity.components.DynamicBody;
import com.mbust.ionized.entity.components.PlayerController;
import com.mbust.ionized.entity.components.SpriteComponent;
import com.mbust.ionized.entity.components.DynamicBody.MovementType;
import com.mbust.ionized.screen.GameScreen;

public class Player {
	private GameScreen _gameScreen;
	private PlayerController _playerController;
	private DynamicBody _dynamicBody;
	private AnimationComponent _animationComponent;
	private SpriteComponent _textureComponent;
	
	private boolean _bulletFire;
	private double _fireDelay, _fireCounter;
	
	public Player(GameScreen gameScreen) {
		_gameScreen = gameScreen;
		_playerController = new PlayerController(this);
		_dynamicBody = new DynamicBody();
		_textureComponent = new SpriteComponent();
		_animationComponent = new AnimationComponent();
		Gdx.input.setInputProcessor(_playerController);
		_dynamicBody.getHitCircle().radius = Config.playerBoundingRadius;
		_dynamicBody.setMovementType(MovementType.MOVEMENT_CONSTANT);
		_bulletFire = false;
		_fireDelay = Config.playerStartingFireDelay;
	}
	
	// Update logic
	public void update(float delta) {
		_dynamicBody.update(delta);
		_playerController.update();
		
		if (_bulletFire) {
			_fireCounter += delta;
			if (_fireCounter >= _fireDelay) {
				Bullet bullet = _gameScreen.getCurrentLevel().spawnBullet(_dynamicBody.getPosition());
				bullet.setRadius(10.0f);
				bullet.getBody().setVelocity(0.0f, 800.0f);
				bullet.setAnimation(_gameScreen.getAssetManager(), "bullet/bullet_anim_1.png", 8, 8, 13);
				_fireCounter = 0;
			}
		}
	}
	
	// Draw graphics
	public void draw(SpriteBatch sb) {
		_textureComponent.draw(sb, _dynamicBody.getPosition(), 1, 1, 0);
		_animationComponent.draw(sb, _dynamicBody.getPosition(), 1, 1, 0);
	}
	
	// Kills the player
	public void kill() {
		
	}
	
	// ------------------------------------------------

	
	public DynamicBody getBody() {
		return _dynamicBody;
	}
	
	// Input events
	public void onInputDirection(boolean[] dir, int inputs) {  
		_dynamicBody.setConstSpeed(Config.playerConstSpeed);
		if (dir[0] && dir[2]) {
			_dynamicBody.setConstDirection(-1, 1); 
		} else if (dir[0] && dir[3]) {
			_dynamicBody.setConstDirection(-1, -1); 
		} else if (dir[1] && dir[2]) {
			_dynamicBody.setConstDirection(1, 1); 
		} else if (dir[1] && dir[3]) {
			_dynamicBody.setConstDirection(1, -1); 
		} else if (inputs == 1) {
			if (dir[0]) _dynamicBody.setConstDirection(-1, 0); 
			if (dir[1]) _dynamicBody.setConstDirection(1, 0); 
			if (dir[2]) _dynamicBody.setConstDirection(0, 1); 
			if (dir[3]) _dynamicBody.setConstDirection(0, -1); 
		} else {
			_dynamicBody.setConstSpeed(0.0f);
		}
	}

	public void setFiringState(boolean value) { 
		_bulletFire = value;
	}
	
	public void setTexture(AssetManager am, String path) {
		_textureComponent.setTexture(am, path);
		_textureComponent.init();
	}
	
	public void setAnimation(AssetManager am, String path, int sheetCols, int sheetRows, int totalFrames) {
		_animationComponent.setTexture(am, path);
		_animationComponent.init(sheetCols, sheetRows, totalFrames);
	}
}
