package net.thirteen.sotl.actors;

import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Direction;

public interface IEnemyMovementBehaviour {
	public Direction move(Level lev, Rectangle rect, Direction direction);
}