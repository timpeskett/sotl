package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;

public abstract class EnemyMovementBehaviour {



	public abstract Direction move(Level lev, Rectangle rect, Direction direction);


	//Move towards player's location
	protected static Direction seek(Level lev, Rectangle rect, Direction direction) {
		Hero hero = lev.getHero();
		Rectangle heroRect = hero.getBoundBox();

		double x = (rect.x + (rect.width/2)) - (heroRect.x + (heroRect.width/2));
		double y = (rect.y + (rect.height/2)) - (heroRect.y + (heroRect.height/2));;

		double angle = Math.atan2(y, x) + Math.PI;

		/*degrees are better, suck it tim*/
		angle *= (180.0/Math.PI);


		if(angle >= 45.0f && angle < 135.0f){
			direction = Direction.UP;

			if(willHitWall(lev, rect, direction)){
				if(angle < 90.0f){
					direction = Direction.RIGHT;
				}
				else{
					direction = Direction.LEFT;
				}
			}
		}
		else if(angle >= 135.0f && angle < 225.0f){
			direction = Direction.LEFT;
		
			if(willHitWall(lev, rect, direction)){
				if(angle < 180.0f){
					direction = Direction.UP;
				}
				else{
					direction = Direction.DOWN;
				}
			}
		}
		else if(angle >= 225.0f && angle < 315.0f){
			direction = Direction.DOWN;
			
			if(willHitWall(lev, rect, direction)){
				if(angle < 270.0f){
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
				if(angle < 360.0f){
					direction = Direction.DOWN;
				}
				else{
					direction = Direction.UP;
				}
			}
		}



		return direction;
	}

	private static boolean willHitWall(Level lev, Rectangle rect, Direction direction){

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

		return !proposedTile.isTileTraversable();	
	}

}