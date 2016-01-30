package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;

public class SeekingMovement implements IEnemyMovementBehaviour {


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
		
		Hero hero = lev.getHero();
		Rectangle heroRect = hero.getBoundBox();
		double angle = Math.atan2(rect.y - heroRect.y, rect.x - heroRect.x) + Math.PI;

		/*degrees are better, suck it tim*/
		angle *= (180.0/Math.PI);


		if(angle >= 45 && angle < 135){
			direction = Direction.UP;
		}
		else if(angle >= 135 && angle < 225){
			direction = Direction.LEFT;
		}
		else if(angle >= 225 && angle < 315){
			direction = Direction.DOWN;
		}
		else{
			direction = Direction.RIGHT;
		}

		System.out.println("\nangle: " + angle + " : " + direction);


		return direction;
	}
}