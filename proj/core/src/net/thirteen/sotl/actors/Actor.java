package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;

public class Actor extends Sprite {

	//back reference to level actor exists within
	private Level lev;
	private Rectangle rect;

    public Actor(Texture tex, Level lev, float xpos, float ypos) {

    	super(tex);
    	rect = new Rectangle();

    	//Ensure level is not null
    	if (lev == null) {
    		throw new IllegalArgumentException("Actor must have a level");
    	}
    	this.lev =lev;

    	//level knows how wide/high a tile should be
    	rect.width = lev.getTileWidth();
    	rect.height = lev.getTileHeight();
    	rect.x = xpos;
    	rect.y = ypos;

    	//Ensure within a valid position
    	if(!rect.overlaps(lev.getRekt())){
    		throw new IllegalArgumentException("Actor cannot exist outside of level");
    	}
        
    }

    public Rectangle getRekt(){
        return rect;
    }

}

