package net.thirteen.sotl.actors;

import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.levels.Level;

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

	@Override
	public void attackAnimation(Level lev, Rectangle rect, Direction direction){
    	//enemy dead animation
    	System.out.println("YOU DEAD");
	}

}