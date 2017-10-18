package com.mbust.ionized.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mbust.ionized.Config;
import com.mbust.ionized.Utility;

public class DynamicBody {
	
	public class MovementType {
		public static final int MOVEMENT_DYNAMIC = 0, MOVEMENT_CONSTANT = 1, MOVEMENT_DISPLACEMENT = 2;
	}
	private int _movementType = 0;
	private Vector2 _position, _velocity, _acceleration, _displacement, _constDirection;
	private Circle _hitCircle;
	private float _angle, _constSpeed;

	// arreglar deltas velocidades c
	
	public DynamicBody() {
		_hitCircle = new Circle();
		_position = new Vector2();
		_velocity = new Vector2();
		_acceleration = new Vector2();
		_displacement = new Vector2();
		_constDirection = new Vector2();
		_constSpeed = 0.0f;
		_angle = 0.0f;
	}
	
	public void update(float delta) {
		Vector2 futurePos = _position.cpy();
		if (_movementType == MovementType.MOVEMENT_DYNAMIC) {
			futurePos.add(_velocity.cpy().scl(delta));
		} else if (_movementType == MovementType.MOVEMENT_CONSTANT) {
			futurePos.add(_constDirection.nor().scl(_constSpeed * delta));
		} else if (_movementType == MovementType.MOVEMENT_DISPLACEMENT) {
			futurePos.add(_displacement);
		}
		_position = boundariesCollision(futurePos);
		_hitCircle.setPosition(_position);
	}
	
	private Vector2 boundariesCollision(Vector2 futurePos) {
		// Check for collision with the game area boundaries
		if (futurePos.x - _hitCircle.radius <= 0) futurePos.x = _hitCircle.radius;
		if (futurePos.y - _hitCircle.radius <= 0) futurePos.y = _hitCircle.radius;
		if (futurePos.x + _hitCircle.radius >= Config.gameAreaWidth) futurePos.x = Config.gameAreaWidth - _hitCircle.radius;
		if (futurePos.y + _hitCircle.radius >= Config.gameAreaHeight) futurePos.y = Config.gameAreaHeight - _hitCircle.radius;
		return futurePos;
	}

	public Vector2 getPosition() {
		return _position;
	}
	
	public void setPosition(Vector2 position) {
		_position = position;
	}
	
	public Vector2 getDisplacement() {
		return _displacement;
	}
	
	public void setDisplacement(Vector2 displacement) {
		_displacement = displacement;
	}
	
	public Vector2 getConstDirection() {
		return _constDirection;
	}
	
	public void setConstDirection(Vector2 constDirection) {
		_constDirection = constDirection;
	}
	
	public void setConstDirection(float x, float y) {
		_constDirection.set(x, y);
	}
	
	public void setMovementType(int movementType) {
		_movementType = movementType;
	}
	
	public Vector2 getVelocity() {
		return _velocity;
	}
	
	public void setVelocity(Vector2 velocity) {
		_velocity = velocity;
	}
	
	public void setConstSpeed(float constSpeed) {
		_constSpeed = constSpeed;
	}
	
	public Vector2 getAcceleration() {
		return _acceleration;
	}
	
	public void setAcceleration(Vector2 acceleration) {
		_acceleration = acceleration;
	}
	
	public void setSpeed(float speed) {
		_velocity.setLength(speed);
	}
	
	public float getSpeed() {
		return _velocity.len();
	}
	
	public void setAngle(float degrees) {
		_angle = degrees;
	}
	
	public float getAngle() {
		return _angle;
	}
	
	public Circle getHitCircle() {
		return _hitCircle;
	}
}
