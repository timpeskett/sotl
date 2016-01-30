package net.thirteen.sotl.actors;

import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.levels.Level;

public class MediumRangeCollision extends EnemyCollisionBehaviour {
	
	private static MediumRangeCollision instance;

	private MediumRangeCollision(){
		//singleton
		distance = 2;
	}

	public static MediumRangeCollision getInstance(){
		if(instance == null){
			instance = new MediumRangeCollision();
		}

		return instance;
	}

	@Override
	public void attackAnimation(Level lev, Rectangle rect, Direction direction){
    	//enemy dead animation
    	System.out.println("YOU DEAD");
	}

}