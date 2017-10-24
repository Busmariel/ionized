package com.mbust.ionized.entity.enemy;

import javax.swing.text.Position;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.components.AnimationComponent;
import com.mbust.ionized.entity.components.DynamicBody;
import com.mbust.ionized.entity.components.SpriteComponent;
import com.mbust.ionized.screen.GameScreen;

public class Enemy implements Poolable {
	private boolean _alive;
	private GameScreen _gameScreen;
	private DynamicBody _dynamicBody;
	private EnemyBehavior _enemyBehavior;
	private AnimationComponent _animationComponent;
	private SpriteComponent _textureComponent;

	public Enemy() {
		_alive = false;
		_dynamicBody = new DynamicBody();
		_textureComponent = new SpriteComponent();
		_animationComponent = new AnimationComponent();
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
	public void draw(SpriteBatch sb) {
		_textureComponent.draw(sb, _dynamicBody.getPosition(), 1, 1, 0);
		_animationComponent.draw(sb, _dynamicBody.getPosition(), 1, 1, 0);
	}
	
	// Kills the enemy
	public void end() {
		_alive = false;
	}
	
	// We reset all the important fields of this object
	@Override
	public void reset() {
		_dynamicBody.reset();
	}
	
	// ------------------------------------------------
	
	public DynamicBody getBody() {
		return _dynamicBody;
	}
	
	public boolean isAlive() {
		return _alive;
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
