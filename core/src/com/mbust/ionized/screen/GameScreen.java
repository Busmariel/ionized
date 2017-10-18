package com.mbust.ionized.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Config;
import com.mbust.ionized.GameClass;
import com.mbust.ionized.Utility;
import com.mbust.ionized.entity.Player;
import com.mbust.ionized.level.Level;

public class GameScreen implements Screen {
	private ShapeRenderer _sr;
	
	private GameClass _gameClass;
	private Level _currentLevel;
	private Player _player;
	private long _time;

	
	public GameScreen(GameClass gameClass) {
		_gameClass = gameClass;
	}
	
	@Override
	public void show() {
		_sr = new ShapeRenderer();
		_currentLevel = new Level(this);
		_player = new Player(this);
		_player.setPosition(Utility.gANPos(0.5f, 0.1f));
		_time = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_time++;
		_sr.setColor(Color.WHITE);
		
		// Rectangulo del area de juego
		_sr.begin(ShapeType.Line);
		_sr.rect(Config.resolutionWidth / 2 - Config.gameAreaWidth / 2, Config.resolutionHeight / 2 - Config.gameAreaHeight / 2, Config.gameAreaWidth, Config.gameAreaHeight);
		
		// Render level
		_sr.setColor(Color.RED);
		_currentLevel.render(delta);
		
		// Render player
		_sr.setColor(Color.WHITE);
		_player.render(delta);
		
		_sr.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		_sr.dispose();
	}

	public ShapeRenderer getRenderer() {
		return _sr;
	}
	
	public Level getCurrentLevel() {
		return _currentLevel;
	}
	
	public long getTime() {
		return _time;
	}
}
