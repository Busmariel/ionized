package com.mbust.ionized.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.DynamicBody;
import com.mbust.ionized.screen.GameScreen;

public class Bullet extends DynamicBody implements Poolable {
	
	private GameScreen _gameScreen;
	private boolean _alive;
	private Texture _texture;
	
	public Bullet() {
		_alive = false;
	}
	
	public void init(GameScreen gameScreen, float x, float y) {
		_gameScreen = gameScreen;
		setPosition(x, y);
		_alive = true;
		setMovementType(MovementType.MOVEMENT_DYNAMIC);
		setBoundariesCollision(false);
	}

	// Update logic
	public void update(float delta) {
		super.update(delta);
		if (isOutOfScreen()) {
			_alive = false;
		}
	}
	
	// Draw graphics
	public void draw() {
		if (_texture != null) {
			_gameScreen.getRenderer().draw(_texture, Utility.gAOrigin().x + getPosition().x - _texture.getWidth() / 2, Utility.gAOrigin().y + getPosition().y - _texture.getHeight() / 2);
		}
	}

	@Override
	public void reset() {
		super.reset();
		_texture = null;
	}
	
	public boolean isAlive() {
		return _alive;
	}
	
	public void setRadius(float radius) {
		getHitCircle().radius = radius;
	}
	
	public void setTexture(String path) {
		_texture = _gameScreen.getAssetManager().get(path, Texture.class);
	}
}
