package net.thirteen.sotl.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Input;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.Main;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.tiles.Tile;

public class Enemy extends Actor {

    public enum State { STANDING, RUNNING };

    /* speed in pixels per second */
	private float speed;
    private int sightRange;
    private Level lev;
    private boolean seenHero;

	protected EnemyCollisionBehaviour collisionBehaviour;
	protected EnemyMovementBehaviour movementBehaviour;

    private State currState;
    private State prevState;
    private float stateTimer;

    private TextureRegion enemyStand;

    private Animation run;


    public Enemy(Level lev, float xpos, float ypos, 
                 float width, float height,
                 float speed, int sightRange,
    	         EnemyCollisionBehaviour collisionBehaviour,
    	         EnemyMovementBehaviour movementBehaviour,
                 Texture tex) {
    	
    	super(tex,
            xpos, 
            ypos,
            width,
            height
        );

        this.lev = lev;
        this.speed = speed;
        this.collisionBehaviour = collisionBehaviour;
        this.movementBehaviour = movementBehaviour;
        this.seenHero = false;
        this.sightRange = sightRange;

        currState = State.STANDING;
        prevState = State.STANDING;
        stateTimer = 0;

        if(movementBehaviour instanceof StationaryMovement){
            float x = xpos - (lev.getTilesX() * lev.getTileWidth())/2;
            float y = ypos - (lev.getTilesY() * lev.getTileHeight())/2;
            double angle = Math.atan2(y,x) + Math.PI;

            angle *= (180/Math.PI);

            if(angle > 45 && angle <= 135){
                direction = Direction.UP;
            }
            else if(angle > 135 && angle <= 225){
                direction = Direction.LEFT;
            }
            else if(angle > 225 && angle <= 315){
                direction = Direction.DOWN;
            }
            else{
                direction = Direction.RIGHT;
            }

        }

        
        Array<TextureRegion> frames = new Array<TextureRegion>();

        /*if(collisionBehaviour instanceof LongRangeCollision) {
             //stuff
         }
        else*/ if(collisionBehaviour instanceof MediumRangeCollision){
        
            frames.add(new TextureRegion(getTexture(), 1, 1, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 76, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 151, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 226, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 301, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 376, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 451, 23, 75));
            frames.add(new TextureRegion(getTexture(), 1, 526, 23, 75));

            enemyStand = new TextureRegion(getTexture(), 1, 376, 23, 75);

            setBounds(1, 427, 23, 75);

        }
        else {

            frames.add(new TextureRegion(getTexture(), 1, 1, 23, 23));
            frames.add(new TextureRegion(getTexture(), 1, 26, 23, 23));
            frames.add(new TextureRegion(getTexture(), 1, 51, 23, 23));
            frames.add(new TextureRegion(getTexture(), 1, 76, 23, 23));
            frames.add(new TextureRegion(getTexture(), 1, 101, 23, 23));
            frames.add(new TextureRegion(getTexture(), 1, 126, 23, 22));
            frames.add(new TextureRegion(getTexture(), 1, 150, 23, 22));
            frames.add(new TextureRegion(getTexture(), 1, 174, 23, 22));

            enemyStand = new TextureRegion(getTexture(), 1, 126, 23, 22);

            setBounds(1, 126, 23, 22);

        }

        run = new Animation(0.1f, frames);

        setRegion(enemyStand);
        
        Rectangle boundBox = getBoundBox();
        setCenter(boundBox.getCenter(new Vector2()).x, 
            boundBox.getCenter(new Vector2()).y);
        setOriginCenter();
    }


    public void update() {
    	move();
        setRegion(getFrame(Gdx.graphics.getDeltaTime()));
    	checkCollisions();
    }


    public TextureRegion getFrame(float delta) {

        TextureRegion region;

        switch(currState) {
            case RUNNING:
                region = run.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = enemyStand;
                break;
        }

        stateTimer = currState == prevState ? stateTimer + delta : 0;
        prevState = currState;

        return region;
    }


    public void move() {
    	Direction newDirection;

    	if(!seenHero){
	    	seenHero = movementBehaviour.lineOfSight(lev, rect, direction, sightRange);
	    }

    	if(seenHero){

    		if(movementBehaviour instanceof StationaryMovement){
    			movementBehaviour = SeekingMovement.getInstance();
    		}

    		newDirection = movementBehaviour.seek(lev, rect, direction);
    	}
    	else{
    		newDirection = movementBehaviour.move(lev, rect, direction);
    	}

    	

    	//do not move when turning around or if stationary
    	if(newDirection == direction && !(movementBehaviour instanceof StationaryMovement)){
    		moveInDirection(direction);
            currState = State.RUNNING;
    	}
    	else{
            /*move to tile boundary first*/
            snapToTile(direction);
    		setDirection(newDirection);
            currState = State.STANDING;
    	}
    }

    private void moveInDirection(Direction direction) {

        Tuple tilePos = lev.worldToTile(rect.x, rect.y);
        Tile tile = lev.getTile(tilePos.first(), tilePos.last());

 		float distance = Gdx.graphics.getDeltaTime() * speed * tile.getSpeedMult();
        moveInDirection(direction, distance);
    }

    private void snapToTile(Direction direction) {

        int tileWidth = lev.getTileWidth();
        int tileHeight = lev.getTileHeight();

        if((rect.x % tileWidth) < (tileWidth / 2)){
            rect.x = (int)(rect.x/tileWidth) * tileWidth;
        }
        else{
            rect.x = ((int)(rect.x/tileWidth) * tileWidth) + 1;
        }

        if((rect.x % tileHeight) < (tileHeight / 2)){
            rect.y = (int)(rect.y/tileHeight) * tileHeight;
        }
        else{
            rect.y = ((int)(rect.y/tileHeight) * tileHeight) + 1;
        }

        setPosition(rect.x, rect.y);
    }

    private void moveInDirection(Direction direction, float distance) {
     
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

    public void attackAnimation(){
    	collisionBehaviour.attackAnimation(lev, rect, direction);
    }

    private void checkCollisions() {

    	//Loop through other enemies for collisions
    	// for(Enemy e: lev.getEnemies()){

    	// 	if(e != this && e.overlaps(this)){
    	// 		direction = direction.flip();
    	// 	}
    	// }
    }



}

