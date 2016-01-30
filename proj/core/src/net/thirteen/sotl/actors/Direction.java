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
}