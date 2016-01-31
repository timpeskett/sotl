package net.thirteen.sotl.actors;


public enum Direction {
	LEFT,
	RIGHT,
	UP,
	DOWN;

	public Direction flip(){
		if(this == LEFT){
			return RIGHT;
		}
		else if(this == RIGHT){
			return LEFT;
		}
		else if(this == UP){
			return DOWN;
		}
		else {
			return UP;
		}
	}

	public static Direction rand(){

		int val = (int)(Math.random() * 3.9);
		
		switch (val){
			case 0:
				return UP;
			case 1:
				return DOWN;
			case 2:
				return LEFT;
			default:
				return RIGHT;
		}
	}
}