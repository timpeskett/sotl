package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Actor {

    private Level lev;

	protected IEnemyCollisionBehaviour collisionBehaviour;
	protected IEnemyMovementBehaviour movementBehaviour;

    public Enemy(Level lev, float xpos, float ypos,
    	         IEnemyCollisionBehaviour collisionBehaviour,
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

