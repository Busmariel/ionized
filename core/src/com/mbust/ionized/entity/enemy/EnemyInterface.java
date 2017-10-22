package com.mbust.ionized.entity.enemy;

import com.mbust.ionized.screen.GameScreen;

public interface EnemyInterface {
	public void init(GameScreen gameScreen);
	public void update(float delta);
	public void end();
}
