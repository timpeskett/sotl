package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Hero extends Actor {

    public Hero(Level lev, float xpos, float ypos) {
        super(new Texture(Gdx.files.internal("hero.png")),
        	lev, 
        	xpos, 
        	ypos
        );
    }

}

