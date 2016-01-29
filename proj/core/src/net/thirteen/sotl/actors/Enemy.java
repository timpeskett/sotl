package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Actor {

    private Level lev;

	protected EnemyCollisionBehaviour collisionBehaviour;
	protected IEnemyMovementBehaviour movementBehaviour;

    public Enemy(Level lev, float xpos, float ypos,
    	         EnemyCollisionBehaviour collisionBehaviour,
    	         IEnemyMovementBehaviour movementBehaviour) {
    	
    	super(Main.manager.get("enemy.png", Texture.class),
        	xpos, 
        	ypos,
            lev.getTileWidth(),
            lev.getTileHeight()
        );

        this.lev = lev;

        this.collisionBehaviour = collisionBehaviour;
        this.movementBehaviour = movementBehaviour;
        
    }

    public void update() {
    	//checkCollisions();
    }

    public void move() {
    	Direction newDirection;

    	newDirection = movementBehaviour.move(lev, rect, direction);

    	//do not move when turning around
    	if(newDirection == direction){
    		moveInDirection(direction);
    	}
    	else{
    		direction = newDirection;
    	}
    }

    private void moveInDirection(Direction direction) {
    	switch (direction){
    		case UP:
    			rect.y += rect.height;
    			break;
    		case RIGHT:
    			rect.x += rect.width;
    			break;
    		case DOWN:
    			rect.y -= rect.height;
    			break;
    		case LEFT:
    			rect.x -= rect.width;
    			break;
    	}
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

