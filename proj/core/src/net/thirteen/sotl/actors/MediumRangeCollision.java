package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.tiles.Tile;
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
		
		float tileX = rect.x / lev.getTileWidth();
		float tileY = rect.y / lev.getTileHeight();
		Tile checkTile;

		//mediumRange can see 2 ahead
		for(int i = 0; i < 2; i++){
			switch (direction){
				case UP:
					tileY++;
					break;
				case DOWN:
					tileY--;
					break;
				case RIGHT:
					tileX++;
					break;
				case LEFT:
					tileX--;
					break;
			}

			checkTile = lev.getTile((int)tileX, (int)tileY);

			if(!checkTile.isTileTransparent()){
				return false;
			}

			// if(lev.isActorAtTile(lev.getHero(), (int)tileX, (int)tileY)){
			// 	return true;
			// }

		}

		//have some code that checks whether tiles in 
		//direction x are transparent/the hero

		return false;
	}

}