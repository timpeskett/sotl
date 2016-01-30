package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;

public class SeekingMovement extends EnemyMovementBehaviour {


	private static SeekingMovement instance;

	private SeekingMovement(){
		//singleton
	}

	public static SeekingMovement getInstance(){
		if(instance == null){
			instance = new SeekingMovement();
		}

		return instance;
	}

	public Direction move(Level lev, Rectangle rect, Direction direction){
		return seek(lev, rect, direction);
	}
		
		
}