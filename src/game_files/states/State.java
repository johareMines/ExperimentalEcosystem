package game_files.states;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	
	
}
