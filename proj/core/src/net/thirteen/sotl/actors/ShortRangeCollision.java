package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;

public class ShortRangeCollision extends EnemyCollisionBehaviour {

	private static ShortRangeCollision instance;

	private ShortRangeCollision(){
		//singleton

		distance = 0;
	}

	public static ShortRangeCollision getInstance(){
		if(instance == null){
			instance = new ShortRangeCollision();
		}

		return instance;
	}

}