package net.thirteen.sotl.actors;

public class MediumRangeCollision extends EnemyCollisionBehaviour {
	
	private static MediumRangeCollision instance;

	private MediumRangeCollision(){
		//singleton
		distance = 2;
	}

	public static MediumRangeCollision getInstance(){
		if(instance == null){
			instance = new MediumRangeCollision();
		}

		return instance;
	}

}