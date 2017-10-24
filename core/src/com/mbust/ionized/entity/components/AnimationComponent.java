package com.mbust.ionized.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Utility;
import com.mbust.ionized.screen.GameScreen;

public class AnimationComponent {
	private Texture 					_texture;
	private Animation<TextureRegion> 	_animation; 
	private float 						_time;
	private boolean						_active;
	
	public AnimationComponent() {

	}
	
	public void init(int sheetCols, int sheetRows, int totalFrames) {
		TextureRegion[][] tmp = TextureRegion.split(_texture, _texture.getWidth() / sheetCols, _texture.getHeight() / sheetRows);
		
		TextureRegion[] frames = new TextureRegion[totalFrames];
		int index = 0;
		for (int i = 0; i < sheetRows; i++) {
			for (int j = 0; j < sheetCols; j++) {
				if (index == totalFrames) break;
				frames[index++] = tmp[i][j];
			}
		}
		
		_animation = new Animation<TextureRegion>(0.025f, frames);
		_time = 0.0f;
		_active = true;
	}
	
	public void draw(SpriteBatch sb, Vector2 position, float scaleX, float scaleY, float rotation) {
		if (_active) {
			_time += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = _animation.getKeyFrame(_time, true);

			float x = Utility.gAOrigin().x + position.x - currentFrame.getRegionWidth() / 2;
			float y = Utility.gAOrigin().y + position.y - currentFrame.getRegionHeight() / 2;
			float xOrig = Utility.gAOrigin().x + position.x;
			float yOrig = Utility.gAOrigin().y + position.y;
			
			sb.draw(currentFrame, x, y, xOrig, yOrig, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), scaleX, scaleY, rotation, true);
		}
	}
	
	public void dispose() {
		// TODO: should we deactivate it?
		_texture.dispose();
	}
	
	public void setTexture(AssetManager am, String path) { 
		_texture = am.get(path, Texture.class);
	}

	public Texture getTexture() {
		return _texture;
	}
}
