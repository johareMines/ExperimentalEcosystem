package game_files;

public class Launcher {
	public static final boolean WANT_FULLSCREEN = true;
	public static final int WHICH_MONITOR = 2;//	Which monitor shall you use?
	public static final int [] CUSTOM_RESOLUTION = {500, 500};//	Not needed if full screen
	
	
	public static void main (String args[]) {
		Game game;
		if (WANT_FULLSCREEN)
			game = new Game("Gayme", WHICH_MONITOR);
		else
			game = new Game("gamee", CUSTOM_RESOLUTION[0], CUSTOM_RESOLUTION[1]);
		
		game.start();
		
	}
}
