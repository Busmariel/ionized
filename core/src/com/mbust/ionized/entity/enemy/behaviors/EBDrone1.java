package com.mbust.ionized.entity.enemy.behaviors;

import com.mbust.ionized.entity.enemy.Enemy;
import com.mbust.ionized.entity.enemy.EnemyBehavior;

public class EBDrone1 implements EnemyBehavior {

	private Enemy _owner;
	
	public EBDrone1() {

	}

	@Override
	public void init(Enemy owner) {
		_owner = owner;
	}

	@Override
	public void update(float delta) {
	
	}
	
	@Override
	public void end() {

	}
}
