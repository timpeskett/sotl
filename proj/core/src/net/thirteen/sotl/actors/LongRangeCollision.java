package net.thirteen.sotl.actors;

import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.levels.Level;

public class LongRangeCollision extends EnemyCollisionBehaviour {
	
	private static LongRangeCollision instance;

	private LongRangeCollision(){
		//singleton
		distance = 4;
	}

	public static LongRangeCollision getInstance(){
		if(instance == null){
			instance = new LongRangeCollision();
		}

		return instance;
	}

	@Override
	public void attackAnimation(Level lev, Rectangle rect, Direction direction){
		//enemy dead animation
    	System.out.println("YOU DEAD");
	}

}