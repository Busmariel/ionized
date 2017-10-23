package com.mbust.ionized.entity.enemy;

public interface EnemyBehavior {
	public void init(Enemy owner);
	public void update(float delta);
	public void end();
}
