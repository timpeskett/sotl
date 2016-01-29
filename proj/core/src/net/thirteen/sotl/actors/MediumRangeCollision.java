package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;

public class MediumRangeCollision implements IEnemyCollisionBehaviour {
	
	private static MediumRangeCollision instance;

	private MediumRangeCollision(){
		//singleton
	}

	public static MediumRangeCollision getInstance(){
		if(instance == null){
			instance = new MediumRangeCollision();
		}

		return instance;
	}

	public boolean collide(Level lev, Rectangle rect, Direction direction){
		
		//have some code that checks whether tiles in 
		//direction x are transparent/the hero

		return false;
	}

}