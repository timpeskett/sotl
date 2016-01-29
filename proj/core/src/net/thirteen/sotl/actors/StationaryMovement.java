package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.levels.Level;

public class StationaryMovement implements IEnemyMovementBehaviour {

	private static StationaryMovement instance;

	private StationaryMovement(){
		//singleton
	}

	public static StationaryMovement getInstance(){
		if(instance == null){
			instance = new StationaryMovement();
		}

		return instance;
	}

	public Direction move(Level lev, Rectangle rect, Direction direction){
		switch (direction) {
			case UP:
				return Direction.RIGHT;
			case RIGHT:
				return Direction.DOWN;
			case DOWN:
				return Direction.LEFT;
			default:
				return Direction.UP;
		}
	}
}