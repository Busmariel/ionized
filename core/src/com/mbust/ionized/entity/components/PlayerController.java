package com.mbust.ionized.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mbust.ionized.entity.player.Player;

public class PlayerController implements InputProcessor {
	private Player _player;
	
	public PlayerController(Player player) {
		_player = player;
	}
	
	public void update() {
		boolean[] dir = new boolean[4]; 
		int inputs = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			dir[0] = true; 
			inputs++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			dir[1] = true; 
			inputs++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			dir[2] = true; 
			inputs++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			dir[3] = true; 
			inputs++;
		}
		_player.onInputDirection(dir, inputs);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.SPACE) {
			_player.setFiringState(true);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.SPACE) {
			_player.setFiringState(false);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}
}
