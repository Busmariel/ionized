package com.mbust.ionized.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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
	private SpriteBatch _sb;
	
	private GameClass _gameClass;
	private Level _currentLevel;

	private BitmapFont _scoreFont;

	private int _nextTick = 0;
	
	public GameScreen(GameClass gameClass) {
		_gameClass = gameClass;
	}
	
	@Override
	public void show() {
		_sr = new ShapeRenderer();
		_sb = new SpriteBatch();
		_currentLevel = new Level(this);
		_scoreFont = Utility.generateBitmapFont();
		
	}

	@Override
	public void render(float delta) {
	    int updatesThisFrame = 0;
	    while (Gdx.graphics.getFrameId() >= _nextTick && updatesThisFrame < Config.MAX_FRAMESKIP) {
	    	update(delta);
	        updatesThisFrame++;
	        _nextTick += Config.SKIP_TICKS;
	    }
	   
	    float alpha = (Gdx.graphics.getFrameId() + Config.SKIP_TICKS - _nextTick) / Config.SKIP_TICKS;

	    draw();
	}
	
	// Draw graphics and interpolate
	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	    // Screen text
	    _sb.begin();
	    _currentLevel.draw();
	    _scoreFont.draw(_sb, "FPS: " + Gdx.graphics.getFramesPerSecond(), 8, Config.resolutionHeight - 8);
	    _scoreFont.draw(_sb, "Bullets: " + _currentLevel.getBulletCount(), 8, Config.resolutionHeight - 16);
	    
	    //getRenderer().draw(getAssetManager().get("bullet/bullet1.png", Texture.class), Utility.gAOrigin().x - getAssetManager().get("bullet/bullet1.png", Texture.class).getWidth() / 2, Utility.gAOrigin().y - getAssetManager().get("bullet/bullet1.png", Texture.class).getHeight() / 2);
	    _sb.end();
	    
	    
		_sr.setColor(Color.WHITE);
		
		// Game area rectangle
		_sr.begin(ShapeType.Line);
		_sr.rect(Config.resolutionWidth / 2 - Config.gameAreaWidth / 2, Config.resolutionHeight / 2 - Config.gameAreaHeight / 2, Config.gameAreaWidth, Config.gameAreaHeight);
		_sr.end();
	}
	
	public void update(float delta) {
		_currentLevel.update(delta);
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

	public SpriteBatch getRenderer() {
		return _sb;
	}
	
	public Level getCurrentLevel() {
		return _currentLevel;
	}

	public AssetManager getAssetManager() {
		return _gameClass.getAssetManager();
	}
}
