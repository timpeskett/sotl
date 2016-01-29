package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;

public interface IEnemyMovementBehaviour {
	public void move(Level lev, Rectangle rect);
}