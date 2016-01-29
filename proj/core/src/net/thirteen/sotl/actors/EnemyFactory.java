package net.thirteen.sotl.actors;

import net.thirteen.sotl.actors.Enemy;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.EnemyCollisionBehaviour;
import net.thirteen.sotl.actors.IEnemyMovementBehaviour;

public class EnemyFactory {

	public enum Difficulty {
		STATIONARY(0x00000001),
		PATH(0x00000010),
		SEEKING(0x00000100),
		SHORT_RANGE(0x00001000),
		MEDIUM_RANGE(0x00010000),
		LONG_RANGE(0x00010000);

		private final int value;

		private Difficulty(final int v){
			value = v;
		}

		public int val(){ return value; }

	}


	public static Enemy createEnemy(int difficulty, Level lev, float xpos, float ypos){

		EnemyCollisionBehaviour collisionBehaviour;
		IEnemyMovementBehaviour movementBehaviour;


		if((difficulty & Difficulty.LONG_RANGE.val()) != 0){
			collisionBehaviour = LongRangeCollision.getInstance();
		}
		else if((difficulty & Difficulty.MEDIUM_RANGE.val()) != 0){
			collisionBehaviour = MediumRangeCollision.getInstance();
		}
		else{
			collisionBehaviour = ShortRangeCollision.getInstance();
		}


		if((difficulty & Difficulty.SEEKING.val()) != 0){
			movementBehaviour = null;
		}
		else if((difficulty & Difficulty.PATH.val()) != 0){
			movementBehaviour = null;
		}
		else{
			movementBehaviour = null;
		}

		return new Enemy(lev, xpos, ypos,
    	                 collisionBehaviour, 
    	                 movementBehaviour);
	}
}