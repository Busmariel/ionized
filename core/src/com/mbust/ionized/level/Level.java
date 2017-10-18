package com.mbust.ionized.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mbust.ionized.entity.bullet.Bullet;
import com.mbust.ionized.screen.GameScreen;

public class Level {
	
	private GameScreen _gameScreen;
	
	private final Array<Bullet> _activeBullets = new Array<Bullet>();
	
    private final Pool<Bullet> _bulletPool = new Pool<Bullet>() {
		@Override
		protected Bullet newObject() {
			return new Bullet(_gameScreen);
		}
    };
    
	public Level(GameScreen gameScreen) {
		_gameScreen = gameScreen;
	}
	
	public void render(float delta) {
		// free dead bullets, TODO: trigger
		freeDeadBullets();
		for (Bullet bullet : _activeBullets) {
			bullet.render(delta);
		}
	}
	
	public Bullet spawnBullet(Vector2 origin) {
        Bullet item = _bulletPool.obtain();
        item.init(origin.x, origin.y);
        _activeBullets.add(item);
		return item;
	}
	
	public Bullet spawnBullet(float x, float y) {
		return spawnBullet(new Vector2(x, y));
	}
	
	public void freeDeadBullets() {
    	Bullet item;
    	int len = _activeBullets.size;
    	for (int i = len; --i >= 0;) {
    	    item = _activeBullets.get(i);
    	    if (!item.isAlive()) {
    	    	_activeBullets.removeIndex(i);
    	        _bulletPool.free(item);
    	    }
    	}
	}
}
