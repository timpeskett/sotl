package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;

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

			if(willHitWall(lev, rect, direction)){
				if(angle < 90){
					direction = Direction.RIGHT;
				}
				else{
					direction = Direction.LEFT;
				}
			}
		}
		else if(angle >= 135 && angle < 225){
			direction = Direction.LEFT;
		
			if(willHitWall(lev, rect, direction)){
				if(angle < 180){
					direction = Direction.UP;
				}
				else{
					direction = Direction.DOWN;
				}
			}
		}
		else if(angle >= 225 && angle < 315){
			direction = Direction.DOWN;
			
			if(willHitWall(lev, rect, direction)){
				if(angle < 270){
					direction = Direction.LEFT;
				}
				else{
					direction = Direction.RIGHT;
				}
			}
		}
		else{
			direction = Direction.RIGHT;
			
			if(willHitWall(lev, rect, direction)){
				if(angle < 360){
					direction = Direction.DOWN;
				}
				else{
					direction = Direction.UP;
				}
			}
		}



		return direction;
	}

	private boolean willHitWall(Level lev, Rectangle rect, Direction direction){

		float posx = rect.x;
		float posy = rect.y;

		switch (direction) {
			case UP:
				posy += rect.height;
				break;
			case DOWN:
				posy -= rect.height;
				break;
			case LEFT:
				posx -= rect.width;
				break;
			case RIGHT:
				posx += rect.width;
				break;
		}	

		Tuple tileTuple = lev.worldToTile(posx, posy);
		Tile proposedTile = lev.getTile(tileTuple.first(), tileTuple.last());

		return proposedTile.isTileTraversable();	
	}
}