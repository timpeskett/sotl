package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.levels.Level;

public class StationaryMovement extends EnemyMovementBehaviour {

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
		return direction;
	}
}