package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.tiles.DoorTile;
import net.thirteen.sotl.World;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;
import net.thirteen.sotl.utils.Tuple;

public class Hero extends Actor {

    public enum State { STANDING, RUNNING};

    /* speed in pixels per second */
	private float speed;

    private World world;

    private State currState;
    private State prevState;
    private float stateTimer;

    public Hero(World world, float xpos, float ypos, float width, float height, float speed) {
        super(Main.manager.get("hero.png", Texture.class),
        	xpos, 
        	ypos,
            width,
            height
        );

        Rectangle boundBox = getBoundBox();

        setCenter(boundBox.getCenter(new Vector2()).x, 
            boundBox.getCenter(new Vector2()).y);

        this.speed = speed;
        this.world = world;

        currState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;

    }

    public float getSpeed() {
    	return speed;
    }

    public void move(float dx, float dy) {
        Rectangle boundBox = new Rectangle(getBoundBox());
        boolean validMove = true;

        Tuple tilePos = world.getCurrentLevel().worldToTile(boundBox.x, boundBox.y);
        Tile tile = world.getCurrentLevel().getTile(tilePos.first(), tilePos.last());

        boundBox.x += speed * dx * tile.getSpeedMult();
        boundBox.y += speed * dy * tile.getSpeedMult();

        for(Tile t : world.getCurrentLevel().getTilesInRect(boundBox).values()) {
            if(!t.isTileTraversable()) {
                validMove = false;
            }
        }

        if(validMove) {

            /* checking for door and spawning new level bro */

            Tuple tpl = world.getCurrentLevel().worldToTile(
                boundBox.getCenter(new Vector2()).x,
                boundBox.getCenter(new Vector2()).y);

            Tile t = world.getCurrentLevel().getTile(tpl);

            if(t instanceof DoorTile) {
                Tuple curLevel, newLevel, newCharPos;
                float newX, newY;

                Level currLev = world.getCurrentLevel();

                curLevel = world.getCurrentLevelTup();
                newLevel = world.changeLevel(tpl);
                newCharPos = world.getDoorPos(curLevel, newLevel);
                if(newCharPos.first() == 0) {
                    newCharPos = newCharPos.firstInc();
                }
                if(newCharPos.last() == 0) {
                    newCharPos = newCharPos.lastInc();
                }
                if(newCharPos.first() == currLev.getTilesX() - 1) {
                    newCharPos = newCharPos.firstDec();
                }
                if(newCharPos.last() == currLev.getTilesY() - 1) {
                    newCharPos = newCharPos.lastDec();
                }

                setPosition(newCharPos.first() * Main.TILE_SIZE, newCharPos.last() * Main.TILE_SIZE);
                boundBox.x = newCharPos.first() * Main.TILE_SIZE;
                boundBox.y = newCharPos.last() * Main.TILE_SIZE;

            } else {

                setPosition(getX() + speed * dx * tile.getSpeedMult(), getY() + speed * dy * tile.getSpeedMult());

            }

            setBoundBox(boundBox);
        }
    }
}

