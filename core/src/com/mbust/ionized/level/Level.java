package com.mbust.ionized.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.Player;
import com.mbust.ionized.entity.bullet.Bullet;
import com.mbust.ionized.entity.enemy.Enemy;
import com.mbust.ionized.entity.enemy.EnemyBehavior;
import com.mbust.ionized.entity.enemy.behaviors.EBDrone1;
import com.mbust.ionized.screen.GameScreen;

public class Level {
	
	private GameScreen _gameScreen;
	private Player _player;
	
	private final Array<Bullet> _activeBullets = new Array<Bullet>();
	private final Pool<Bullet> _bulletPool = Pools.get(Bullet.class);
    
	private final Array<Enemy> _activeEnemies = new Array<Enemy>();
	private final Pool<Enemy> _enemyPool = Pools.get(Enemy.class);
	
    
	// TODO
	public static Level loadFromFile() {
		FileHandle file = Gdx.files.internal("level.txt");
		Gson gson = new Gson();
		return gson.fromJson(file.readString(), Level.class);
	}
	
	// TODO
	public static void saveToFile(Level level) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		FileHandle file = Gdx.files.local("level.txt");
		file.writeString(gson.toJson(level), false);
	}
	
    
	public Level(GameScreen gameScreen) {
		_gameScreen = gameScreen;
		_player = new Player(gameScreen);
		_player.setPosition(Utility.gANPos(0.5f, 0.1f));
		_player.setTexture("player/player.png");
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	Enemy enemy = spawnEnemy(new EBDrone1());
		    	enemy.setPosition(200, 200);
		    }
		}, 5);
		//Enemy enemy = spawnEnemy(new EBDrone1());
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	Enemy enemy = spawnEnemy(new EBDrone1());
		    	enemy.setPosition(200, 200);
		    }
		}, 6);
	}
	
	// Update logic
	public void update(float delta) {
		
		/*Bullet bull = spawnBullet(new Vector2(120, 50));
		bull.setRadius(10.0f);
		bull.setVelocity(0.0f, 1.1f);*/
		// free dead bullets, TODO: trigger
		freeDeadBullets();
		freeDeadEnemies();
		for (Bullet bullet : _activeBullets) {
			bullet.update(delta);
		}
		for (Enemy enemy : _activeEnemies) {
			enemy.update(delta);
		}
		_player.update(delta);
	}
	
	// Draw graphics
	public void draw() {
		for (Bullet bullet : _activeBullets) {
			bullet.draw();
		}
		for (Enemy enemy : _activeEnemies) {
			enemy.draw();
		}
		_player.draw();
	}
	
	public Bullet spawnBullet(Vector2 origin) {
        Bullet bullet = _bulletPool.obtain();
        bullet.init(_gameScreen, origin.x, origin.y);
        bullet.setTexture("bullet/bullet1.png");
        _activeBullets.add(bullet);
		return bullet;
	}
	
	public Bullet spawnBullet(float x, float y) {
		return spawnBullet(new Vector2(x, y));
	}
	
	public void freeDeadBullets() {
    	Bullet bullet;
    	int size = _activeBullets.size;
    	for (int i = size; --i >= 0;) {
    		bullet = _activeBullets.get(i);
    	    if (!bullet.isAlive()) {
    	    	_activeBullets.removeIndex(i);
    	        _bulletPool.free(bullet);
    	    }
    	}
	}
	
	public Enemy spawnEnemy(EnemyBehavior enemyBehavior) {
        Enemy enemy = _enemyPool.obtain();
        enemy.init(_gameScreen, enemyBehavior);
        _activeEnemies.add(enemy);
		return enemy;
	}
	
	public void freeDeadEnemies() {
    	Enemy enemy;
    	int size = _activeEnemies.size;
    	for (int i = size; --i >= 0;) {
    		enemy = _activeEnemies.get(i);
    	    if (!enemy.isAlive()) {
    	    	_activeEnemies.removeIndex(i);
    	        _enemyPool.free(enemy);
    	    }
    	}
	}
	
	public int getBulletCount() {
		return _activeBullets.size;
	}
}
