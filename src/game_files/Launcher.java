package game_files;

public class Launcher {
	public static final boolean WANT_FULLSCREEN = true;
	public static final int WHICH_MONITOR = 1;//	Which monitor shall you use?
	public static final int [] CUSTOM_RESOLUTION = {500, 500};//	Not needed if full screen
	public static Game GAME_INSTANCE;
	
	public static void main (String args[]) {
		if (WANT_FULLSCREEN)
			GAME_INSTANCE = new Game("Gayme", WHICH_MONITOR);
		else
			GAME_INSTANCE = new Game("gamee", CUSTOM_RESOLUTION[0], CUSTOM_RESOLUTION[1]);
		
		GAME_INSTANCE.start();
	}
	
	public static Game getGameInstance() {
		return GAME_INSTANCE;
	}
}

