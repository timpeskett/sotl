package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.World;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;

public class Hero extends Actor {

    /* speed in pixels per second */
	private float speed;

    private World world;

    public Hero(World world, float xpos, float ypos, float width, float height, float speed) {
        super(Main.manager.get("hero.png", Texture.class),
        	xpos, 
        	ypos,
            width,
            height
        );

        this.speed = speed;
        this.world = world;
    }

    public float getSpeed() {
    	return speed;
    }

    public void move(float dx, float dy) {
        Rectangle boundBox = new Rectangle(getBoundBox());
        boolean validMove = true;

        boundBox.x += speed * dx;
        boundBox.y += speed * dy;

        for(Tile t : world.getCurrentLevel().getTilesInRect(boundBox).values()) {
            if(!t.isTileTraversable()) {
                validMove = false;
            }
        }

        if(validMove) {
            setPosition(getX() + speed * dx, getY() + speed * dy);

            setBoundBox(boundBox);
        }
    }
}

