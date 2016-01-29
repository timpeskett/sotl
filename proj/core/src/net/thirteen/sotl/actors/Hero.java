package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import net.thirteen.sotl.World;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;

public class Hero extends Actor {

	private int speed;
    private World world;

    public Hero(World world, float xpos, float ypos, float width, float height) {
        super(Main.manager.get("hero.png", Texture.class),
        	xpos, 
        	ypos,
            width,
            height
        );

        speed = 200;
        this.world = world;
    }

    public int getSpeed() {
    	return speed;
    }

}

