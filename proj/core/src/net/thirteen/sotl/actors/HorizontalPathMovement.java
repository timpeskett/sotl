package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;

public class HorizontalPathMovement extends EnemyMovementBehaviour {

	private static HorizontalPathMovement instance;

	private HorizontalPathMovement(){
		//singleton
	}

	public static HorizontalPathMovement getInstance(){
		if(instance == null){
			instance = new HorizontalPathMovement();
		}

		return instance;
	}

	public Direction move(Level lev, Rectangle rect, Direction direction){
		
		Tuple tilePos = lev.worldToTile(rect.x, rect.y);
		Tile prospectiveTile;

		//was given an invalid direction
		if(direction != Direction.RIGHT && direction != Direction.LEFT)
			direction = Direction.RIGHT;

		if(direction == Direction.RIGHT){
			tilePos.setFirst(tilePos.first()+1);
		}
		else{
			tilePos.setFirst(tilePos.first()-1);
		}
		
		//ensure tile exists within level
		if (tilePos.first() < lev.getTilesX() && 
			tilePos.last()  < lev.getTilesY() &&
			tilePos.first() >= 0 &&
			tilePos.last() >= 0) {

			prospectiveTile = lev.getTile(tilePos.first(), tilePos.last());

			/*can go to tile, move in that direction*/
			if(prospectiveTile.isTileTraversable()){
				return direction;
			}
		}

		/*cannot go forward, turn around*/
		if(direction == Direction.RIGHT){
			return Direction.LEFT;
		}
		else{
			return Direction.RIGHT;
		}
	}
}