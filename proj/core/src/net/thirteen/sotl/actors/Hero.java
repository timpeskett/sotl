package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.Main;

public class Hero extends Actor {

	private int speed;

    public Hero(Level lev, float xpos, float ypos) {
        super(Main.manager.get("hero.png", Texture.class),
        	lev, 
        	xpos, 
        	ypos
        );

        speed = 200;
    }

    public int getSpeed() {
    	return speed;
    }

}

