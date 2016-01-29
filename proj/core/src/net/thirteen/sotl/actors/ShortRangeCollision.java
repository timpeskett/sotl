package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;

public class ShortRangeCollision implements IEnemyCollisionBehaviour {

	private static ShortRangeCollision instance;

	private ShortRangeCollision(){
		//singleton
	}

	public static ShortRangeCollision getInstance(){
		if(instance == null){
			instance = new ShortRangeCollision();
		}

		return instance;
	}
	
	public boolean collide(Level lev, Rectangle rect, Direction direction){
		Hero hero = lev.getHero();
        return rect.overlaps(hero.getRekt());
	}

}