package com.mbust.ionized.entity.components;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Utility;
import com.mbust.ionized.screen.GameScreen;

public class SpriteComponent {
	private Sprite 						_sprite;
	private boolean 					_active;
	
	public SpriteComponent() {
	
	}
	
	public void init() {
		_active = true;
	}
	
	public void draw(SpriteBatch sb, Vector2 position, float scaleX, float scaleY, float rotation) {
		if (_active) {
			float x = Utility.gAOrigin().x + position.x - _sprite.getWidth() / 2;
			float y = Utility.gAOrigin().y + position.y - _sprite.getHeight() / 2;

			_sprite.setOrigin(_sprite.getWidth() / 2, _sprite.getHeight() / 2);
			_sprite.setPosition(x, y);
			_sprite.setRotation(rotation);
			_sprite.draw(sb);
		}
	}
	
	public void dispose() {
		// TODO: should we deactivate it?
	}
	
	public void setTexture(AssetManager am, String path) { 
		_sprite = new Sprite(am.get(path, Texture.class));
	}
}
