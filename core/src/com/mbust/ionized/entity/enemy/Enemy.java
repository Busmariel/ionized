package com.mbust.ionized.entity.enemy;

import javax.swing.text.Position;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.DynamicBody;
import com.mbust.ionized.screen.GameScreen;

public class Enemy extends DynamicBody implements Poolable {
	private boolean _alive;
	private GameScreen _gameScreen;
	private EnemyBehavior _enemyBehavior;
	private Texture _texture;

	public Enemy() {
		_alive = false;
	}
	
	public void init(GameScreen gameScreen, EnemyBehavior enemyBehavior) {
		_gameScreen = gameScreen;
		_enemyBehavior = enemyBehavior;
		_enemyBehavior.init(this);
		_alive = true;
	}
	
	// Update logic
	public void update(float delta) {
		_enemyBehavior.update(delta);
	}
	
	// Draw graphics
	public void draw() {
		if (_texture != null) {
			_gameScreen.getRenderer().draw(_texture, Utility.gAOrigin().x + getPosition().x - _texture.getWidth() / 2, Utility.gAOrigin().y + getPosition().y - _texture.getHeight() / 2);
		}
	}
	
	// Kills the enemy
	public void end() {
		_alive = false;
	}
	
	// We reset all the important fields of this object
	@Override
	public void reset() {
		_texture = null;
	}
	
	// ------------------------------------------------
	
	public boolean isAlive() {
		return _alive;
	}
	
	public void setTexture(String path) {
		_texture = _gameScreen.getAssetManager().get(path, Texture.class);
	}
}
