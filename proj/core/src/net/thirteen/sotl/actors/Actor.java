package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;

public class Actor extends Sprite {

	protected Rectangle rect;
    protected Direction direction;

    public Actor(Texture tex, float xpos, float ypos, float width, float height) {

    	super(tex);
    	rect = new Rectangle();

    	//level knows how wide/high a tile should be
    	rect.width = width;
    	rect.height = height;
    	rect.x = xpos;
    	rect.y = ypos;

        setOriginCenter();
        setX(xpos);
        setY(ypos);

        direction = Direction.UP;

    }

    public Rectangle getRekt(){
        return rect;
    }

    public void setDirection( Direction d) {
        //probs should be case statement. not even mad.
        if( d == Direction.DOWN) {
            setRotation(180);
        }
        if( d == Direction.UP) {
            setRotation(0);
        }
        if( d == Direction.LEFT) {
            setRotation(90);
        }
        if( d == Direction.RIGHT) {
            setRotation(270);
        }
        direction = d;
    }
}

