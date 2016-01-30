package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.tiles.Tile;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.utils.Tuple;

public abstract class EnemyCollisionBehaviour {

	protected int distance = 0;

	public boolean collide(Level lev, Rectangle rect, Direction direction){
		
		
		//convert from x/y co-ords to tile co-ords
		Tuple tileTuple = lev.worldToTile(rect.x, rect.y);
		int tileX = tileTuple.first();
		int tileY = tileTuple.last();
		Hero hero = lev.getHero();

        if(rect.overlaps(hero.getRekt())){
        	return true;
        }

		//mediumRange can see 2 ahead
		for(int i = 0; i < distance; i++){

			Tile checkTile;

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

			//Don't check further tiles if looking at wall/etc
			if(!checkTile.isTileTransparent()){
				return false;
			}

			if(lev.isActorAtTile(lev.getHero(), (int)tileX, (int)tileY)){
				return true;
			}

		}

		return false;
	}

	public abstract void attackAnimation(Level lev, Rectangle rect, Direction direction);
}