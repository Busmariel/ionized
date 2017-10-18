package com.mbust.ionized.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Bullet extends DynamicBody implements Poolable {

	public Bullet(Vector2 position, float radius) {
		//super(position, radius);
	}

	
	public void render(SpriteBatch sb) {
		
	}


	@Override
	public void reset() {

	}
}
