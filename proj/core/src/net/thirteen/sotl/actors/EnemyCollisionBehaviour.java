package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.tiles.Tile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.Main;

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

		//mediumRange can see 1 ahead
		for(int i = 0; i < distance; i++){

			Tile checkTile;

			switch (direction){
				case UP:
					if(tileY < lev.getTilesY() - 1){
						tileY++;
					}
					break;
				case DOWN:
					if(tileY > 0){
						tileY--;
					}
					break;
				case RIGHT:
					if(tileX < lev.getTilesX() - 1){
						tileX++;
					}
					break;
				case LEFT:
					if(tileX > 0){
						tileX--;
					}
					break;
			}


			checkTile = lev.getTile((int)tileX, (int)tileY);

			//Don't check further tiles if looking at wall/etc
			if(!checkTile.isTileTransparent()){
				return false;
			}

			if(lev.isActorAtTile(lev.getHero(), (int)tileX, (int)tileY)){
				Main.manager.get("bleet.mp3", Sound.class).play();
				return true;
			}

		}

		return false;
	}

	public abstract void attackAnimation(Level lev, Rectangle rect, Direction direction);
}