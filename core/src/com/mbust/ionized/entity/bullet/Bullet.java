package com.mbust.ionized.entity.bullet;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.components.AnimationComponent;
import com.mbust.ionized.entity.components.DynamicBody;
import com.mbust.ionized.entity.components.DynamicBody.MovementType;
import com.mbust.ionized.entity.components.SpriteComponent;
import com.mbust.ionized.screen.GameScreen;

public class Bullet implements Poolable {
	
	private GameScreen _gameScreen;
	private DynamicBody _dynamicBody;
	private AnimationComponent _animationComponent;
	private SpriteComponent _textureComponent;
	private boolean _alive;

	public Bullet() {
		_alive = false;
		_dynamicBody = new DynamicBody();
		_textureComponent = new SpriteComponent();
		_animationComponent = new AnimationComponent();
	}
	
	public void init(GameScreen gameScreen, float x, float y) {
		_gameScreen = gameScreen;
		_dynamicBody.setPosition(x, y);
		_dynamicBody.setMovementType(MovementType.MOVEMENT_DYNAMIC);
		_dynamicBody.setBoundariesCollision(false);
		_alive = true;
	}

	// Update logic
	public void update(float delta) {
		_dynamicBody.update(delta);
		if (_dynamicBody.isOutOfScreen()) {
			_alive = false;
		}
	}
	
	// Draw graphics
	public void draw(SpriteBatch sb) {
		_textureComponent.draw(sb, _dynamicBody.getPosition(), 1, 1, 0);
		_animationComponent.draw(sb, _dynamicBody.getPosition(), 1, 1, 0);
	}

	@Override
	public void reset() {
		_dynamicBody.reset();
	}
	
	public DynamicBody getBody() {
		return _dynamicBody;
	}

	public boolean isAlive() {
		return _alive;
	}
	
	public void setRadius(float radius) {
		_dynamicBody.getHitCircle().radius = radius;
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
