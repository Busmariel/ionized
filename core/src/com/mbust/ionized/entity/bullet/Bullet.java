package com.mbust.ionized.entity.bullet;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.entity.DynamicBody;
import com.mbust.ionized.entity.DynamicBody.MovementType;
import com.mbust.ionized.screen.GameScreen;

public class Bullet extends DynamicBody implements Poolable {
	
	private GameScreen _gameScreen;
	private boolean _alive;
	
	public Bullet(GameScreen gameScreen) {
		_gameScreen = gameScreen;
		//super(position, radius);
		setMovementType(MovementType.MOVEMENT_DYNAMIC);
		_alive = false;
	}
	
	public void init(float x, float y) {
		setPosition(x, y);
		_alive = true;
	}

	
	public void render(float delta) {
		super.update(delta);
		_gameScreen.getRenderer().circle(getPosition().x, getPosition().y, getHitCircle().radius);
		if (isOutOfScreen()) {
			_alive = false;
		}
	}

	@Override
	public void reset() {
		setPosition(0, 0);
	}
	
	public boolean isAlive() {
		return _alive;
	}
}
