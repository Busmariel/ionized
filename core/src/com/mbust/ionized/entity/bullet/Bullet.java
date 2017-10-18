package com.mbust.ionized.entity.bullet;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.DynamicBody;
import com.mbust.ionized.screen.GameScreen;

public class Bullet extends DynamicBody implements Poolable {
	
	private GameScreen _gameScreen;
	private boolean _alive;
	
	public Bullet(GameScreen gameScreen) {
		//super(position, radius);
		_gameScreen = gameScreen;
		setMovementType(MovementType.MOVEMENT_DYNAMIC);
		_alive = false;
	}
	
	public void init(float x, float y) {
		setPosition(x, y);
		_alive = true;
	}

	
	public void render(float delta) {
		super.update(delta);
		_gameScreen.getRenderer().circle(Utility.gAOrigin().x + getPosition().x, Utility.gAOrigin().y +  getPosition().y, getHitCircle().radius);
		if (isOutOfScreen()) {
			_alive = false;
		}
	}

	@Override
	public void reset() {
		super.reset();
	}
	
	public boolean isAlive() {
		return _alive;
	}
	
	public void setRadius(float radius) {
		getHitCircle().radius = radius;
	}
}
