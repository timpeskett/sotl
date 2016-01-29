package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Direction;
import com.badlogic.gdx.math.Rectangle;

public interface IEnemyCollisionBehaviour {
	public boolean collide(Level lev, Rectangle rect, Direction direction);
}