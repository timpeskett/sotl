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

	public static boolean lineOfSight(Level lev, Rectangle rect, Direction direction) {
		Tuple tileTuple = lev.worldToTile(rect.x, rect.y);

		Rectangle newRect = new Rectangle(rect);

		while(tileTuple.first() < lev.getTilesX() &&
			  tileTuple.first() >= 0 &&
		      tileTuple.last() < lev.getTilesY() &&
		      tileTuple.last() >= 0) {

			if(lev.isActorAtTile(lev.getHero(), tileTuple.first(), tileTuple.last())){
				return true;
			}

			if(!canSeeThrough(lev, newRect, direction)){
				break;
			}

			switch (direction) {
				case UP:
					tileTuple.setLast(tileTuple.last()+1);
					newRect.y += newRect.height;
					break;
				case DOWN:
					tileTuple.setLast(tileTuple.last()-1);
					newRect.y -= newRect.height;
					break;
				case LEFT:
					tileTuple.setFirst(tileTuple.first()-1);
					newRect.x -= newRect.width;
					break;
				case RIGHT:
					tileTuple.setFirst(tileTuple.first()+1);
					newRect.x += newRect.width;
					break;
			}
			
		}
		return false;
	}

	private static boolean willHitWall(Level lev, Rectangle rect, Direction direction){

		Tile proposedTile = tileInteraction(lev, rect, direction);

		return !proposedTile.isTileTraversable();	
	}

	private static boolean canSeeThrough(Level lev, Rectangle rect, Direction direction) {
		
		Tile proposedTile = tileInteraction(lev, rect, direction);

		return proposedTile.isTileTransparent();	
	}

	private static Tile tileInteraction(Level lev, Rectangle rect, Direction direction) {
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
		return lev.getTile(tileTuple.first(), tileTuple.last());
	}

}