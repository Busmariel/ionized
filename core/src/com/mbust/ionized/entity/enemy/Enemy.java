package com.mbust.ionized.entity.enemy;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.entity.DynamicBody;
import com.mbust.ionized.screen.GameScreen;

public abstract class Enemy extends DynamicBody implements Poolable {
	private boolean _alive;
	private GameScreen _gameScreen;
	
	public Enemy() {
		_alive = false;
	}
	
	public void init(GameScreen gameScreen) {
		_gameScreen = gameScreen;
		_alive = true;
	}
	
	// Update logic
	public void update(float delta) {
		
	}
	
	// Draw graphics
	public void draw() {
		
	}
	
	// Kills the enemy
	public void end() {
		_alive = false;
	}
	
	// We reset all the important fields of this object
	@Override
	public void reset() {

	}
	
	// ------------------------------------------------
	
	public boolean isAlive() {
		return _alive;
	}
}
