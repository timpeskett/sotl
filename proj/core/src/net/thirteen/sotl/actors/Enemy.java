package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Actor {

	protected IEnemyCollisionBehaviour collisionBehaviour;
	protected IEnemyMovementBehaviour movementBehaviour;

    public Enemy(Level lev, float xpos, float ypos,
    	         IEnemyCollisionBehaviour collisionBehaviour,
    	         IEnemyMovementBehaviour movementBehaviour) {
    	
    	super(new Texture(Gdx.files.internal("enemy.png")),
        	lev, 
        	xpos, 
        	ypos
        );

        this.collisionBehaviour = collisionBehaviour;
        this.movementBehaviour = movementBehaviour;
        
    }

    public void update() {
    	//checkCollisions();
    }

    public void move() {
    	movementBehaviour.move(lev, rect);
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

