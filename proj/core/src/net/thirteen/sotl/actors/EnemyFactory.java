package net.thirteen.sotl.actors;

import net.thirteen.sotl.actors.Enemy;
import net.thirteen.sotl.levels.Level;

public class EnemyFactory {

	public enum Difficulty {
		STATIONARY(0x00000001),
		HOR_PATH(0x00000010),
		VERT_PATH(0x00000100),
		SEEKING(0x00001000),
		SHORT_RANGE(0x00010000),
		MEDIUM_RANGE(0x00100000),
		LONG_RANGE(0x01000000);

		private final int value;

		private Difficulty(final int v){
			value = v;
		}

		public int val(){ return value; }

	}

	public static Enemy createEnemy(Level lev, float difficulty, float xpos, float ypos){

		difficulty *= 12;
		int difficultyMask = 0;

		if(difficulty <= 1f){
			difficultyMask |= Difficulty.STATIONARY.val();
			difficultyMask |= Difficulty.SHORT_RANGE.val();
		}
		else if(difficulty <= 2f){
			difficultyMask |= Difficulty.STATIONARY.val();
			difficultyMask |= Difficulty.MEDIUM_RANGE.val();
		}
		else if(difficulty <= 3f){
			difficultyMask |= Difficulty.STATIONARY.val();
			difficultyMask |= Difficulty.LONG_RANGE.val();
		}
		else if(difficulty <= 4f){
			difficultyMask |= Difficulty.HOR_PATH.val();
			difficultyMask |= Difficulty.SHORT_RANGE.val();
		}
		else if(difficulty <= 5f){
			difficultyMask |= Difficulty.VERT_PATH.val();
			difficultyMask |= Difficulty.SHORT_RANGE.val();
		}
		else if(difficulty <= 6f){
			difficultyMask |= Difficulty.HOR_PATH.val();
			difficultyMask |= Difficulty.MEDIUM_RANGE.val();
		}
		else if(difficulty <= 7f){
			difficultyMask |= Difficulty.VERT_PATH.val();
			difficultyMask |= Difficulty.MEDIUM_RANGE.val();
		}
		else if(difficulty <= 8f){
			difficultyMask |= Difficulty.HOR_PATH.val();
			difficultyMask |= Difficulty.LONG_RANGE.val();
		}
		else if(difficulty <= 9f){
			difficultyMask |= Difficulty.VERT_PATH.val();
			difficultyMask |= Difficulty.LONG_RANGE.val();
		}
		else if(difficulty <= 10f){
			difficultyMask |= Difficulty.SEEKING.val();
			difficultyMask |= Difficulty.SHORT_RANGE.val();
		}
		else if(difficulty <= 11f){
			difficultyMask |= Difficulty.SEEKING.val();
			difficultyMask |= Difficulty.MEDIUM_RANGE.val();
		}
		else{
			difficultyMask |= Difficulty.SEEKING.val();
			difficultyMask |= Difficulty.LONG_RANGE.val();
		}

		return createEnemy(lev, difficultyMask, xpos, ypos);
	}


	protected static Enemy createEnemy(Level lev, int difficulty, float xpos, float ypos){

		EnemyCollisionBehaviour collisionBehaviour;
		EnemyMovementBehaviour movementBehaviour;


		//Attack range
		if((difficulty & Difficulty.LONG_RANGE.val()) != 0){
			collisionBehaviour = LongRangeCollision.getInstance();
		}
		else if((difficulty & Difficulty.MEDIUM_RANGE.val()) != 0){
			collisionBehaviour = MediumRangeCollision.getInstance();
		}
		else{
			collisionBehaviour = ShortRangeCollision.getInstance();
		}


		//Movement pattern
		if((difficulty & Difficulty.SEEKING.val()) != 0){
			movementBehaviour = SeekingMovement.getInstance();
		}
		else if((difficulty & Difficulty.HOR_PATH.val()) != 0){
			movementBehaviour = HorizontalPathMovement.getInstance();
		}
		else if((difficulty & Difficulty.VERT_PATH.val()) != 0){
			movementBehaviour = VerticalPathMovement.getInstance();
		}
		else{
			movementBehaviour = StationaryMovement.getInstance();
		}

		return new Enemy(lev, xpos, ypos, 
						 150f /*speed*/, 
			             5 /*sightRange*/,
    	                 collisionBehaviour, 
    	                 movementBehaviour);
	}
}