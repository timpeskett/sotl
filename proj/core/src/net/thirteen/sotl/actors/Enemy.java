package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels;

public class Enemy extends Actor {

    public Enemy(Level lev, float xpos, float ypos) {
    	super(new Texture(Gdx.files.internal("enemy.png")),
        	lev, 
        	xpos, 
        	ypos
        );
        
    }

}

