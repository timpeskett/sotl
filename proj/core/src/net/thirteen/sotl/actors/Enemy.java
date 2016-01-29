package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.levels.Level;

public class Enemy extends Actor {

    public Enemy(Level lev, float xpos, float ypos) {
    	super(new Texture(Gdx.files.internal("enemy.png")),
        	lev, 
        	xpos, 
        	ypos
        );
        
    }

    public void update(){
    	checkCollisions();
    }

    public boolean checkHeroCollision(){
    	Hero hero = lev.getHero();
    	return rect.overlaps(hero.getRekt()));
    }

    private void checkCollisions(){

    	//Loop through other enemies for collisions
    	for(Enemy enemy: lev.getEnemies()){
    		//do something?
    	}
    }



}

