package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.levels.PathFinder;

import java.util.ArrayList;

public abstract class EnemyMovementBehaviour {



	public abstract Direction move(Level lev, Rectangle rect, Direction direction);


	//Move towards player's location
	protected static Direction seek(Level lev, Rectangle rect, Direction direction) {
		Hero hero = lev.getHero();
		Rectangle heroRect = hero.getBoundBox();
		Tuple heroTuple = lev.worldToTile(heroRect.x, heroRect.y);
		Tuple enemyTuple = lev.worldToTile(rect.x, rect.y);

        ArrayList<Tuple> path = PathFinder.getPath(lev, enemyTuple, heroTuple);

        /* Ensure not on the same tile as the hero */
        if(path.size() > 1) {
            Tuple dest = path.get(1);

            if(dest.first() > enemyTuple.first()){
                return Direction.RIGHT;
            }
            if(dest.last() < enemyTuple.last()){
                return Direction.DOWN;
            }
            if(dest.first() < enemyTuple.first()){
                return Direction.LEFT;
            }
        }

		return Direction.UP;
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

		if(posx > (lev.getRekt().width - lev.getTileWidth())){
			posx = (lev.getRekt().width - lev.getTileWidth());
		}
		if(posy > (lev.getRekt().height - lev.getTileHeight())){
			posy = (lev.getRekt().height - lev.getTileHeight());
		}

		Tuple tileTuple = lev.worldToTile(posx, posy);
		return lev.getTile(tileTuple.first(), tileTuple.last());
	}

}
