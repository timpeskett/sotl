package net.thirteen.sotl.actors;

public class LongRangeCollision extends EnemyCollisionBehaviour {
	
	private static LongRangeCollision instance;

	private LongRangeCollision(){
		//singleton
		distance = 4;
	}

	public static LongRangeCollision getInstance(){
		if(instance == null){
			instance = new LongRangeCollision();
		}

		return instance;
	}

}