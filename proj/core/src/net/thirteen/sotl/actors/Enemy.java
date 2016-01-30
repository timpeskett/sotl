package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;

public class Enemy extends Actor {

    /* speed in pixels per second */
	private float speed;
    private Level lev;
    private boolean seenHero;

	protected EnemyCollisionBehaviour collisionBehaviour;
	protected EnemyMovementBehaviour movementBehaviour;


    public Enemy(Level lev, float xpos, float ypos, float speed,
    	         EnemyCollisionBehaviour collisionBehaviour,
    	         EnemyMovementBehaviour movementBehaviour) {
    	
    	super(Main.manager.get("enemy.png", Texture.class),
        	xpos, 
        	ypos,
            lev.getTileWidth(),
            lev.getTileHeight()
        );

        this.lev = lev;
        this.speed = speed;
        this.collisionBehaviour = collisionBehaviour;
        this.movementBehaviour = movementBehaviour;
        this.seenHero = false;
        
    }


    public void update() {
    	move();
    }

    public void move() {
    	Direction newDirection;

    	if(!seenHero){
	    	seenHero = movementBehaviour.lineOfSight(lev, rect, direction);
	    }

    	if(seenHero){
    		newDirection = movementBehaviour.seek(lev, rect, direction);
    	}
    	else{
    		newDirection = movementBehaviour.move(lev, rect, direction);
    	}

    	

    	//do not move when turning around
    	if(newDirection == direction){
    		moveInDirection(direction);
    	}
    	else{
    		setDirection(newDirection);
    	}
    }

    private void moveInDirection(Direction direction) {

 		Tuple tilePos = lev.worldToTile(rect.x, rect.y);
 		Tile tile = lev.getTile(tilePos.first(), tilePos.last());
 		float distance = Gdx.graphics.getDeltaTime() * speed * tile.getSpeedMult();

    	switch (direction){
    		case UP:
    			rect.y += distance;
    			break;
    		case RIGHT:
    			rect.x += distance;
    			break;
    		case DOWN:
    			rect.y -= distance;
    			break;
    		case LEFT:
    			rect.x -= distance;
    			break;
    	}

    	setPosition(rect.x, rect.y);
    }

    public boolean checkHeroCollision() {
    	return collisionBehaviour.collide(lev, rect, direction);
    }

    // private void checkCollisions() {

    // 	//Loop through other enemies for collisions
    // 	for(Enemy enemy: lev.getEnemies()){
    // 		//do something?
    // 	}
    // }



}

