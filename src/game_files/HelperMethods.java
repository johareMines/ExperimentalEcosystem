package game_files;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Random;

public class HelperMethods {

	public static final int SLIME_START_AMT = 3;
	public static final float INVISIBLE_BOUNDARY = 30;
	public static int screenWidth;
	public static int screenHeight;
	public static String[] names = {"BARN HARNIT", "EGG NAD", "Fallen Tree", "Gust of three winds", "sanko", "Teebo", "Waltz?", "Prouu", "Fredrick", 
			"slow bump", "q-tip", "Eneumerated Melon", "Obtuse Goose", "Molten Fingertrap", "Illiterate Frenchman", "Squeemish Platelet", "Too small of a feather", 
			"High hanging fruit", "who0ps", "Asymmetric Moose Antler", "bonkman", "Cruddy Squeeze", "Sneevile", "Pwiertie", "Grunbo", "Clyide", "Pansh",
			"Estranged Homeowner", "Ugly lil truther", "shid", "boogermonger", ""};// Yo fucks at the party, add a name
	


	public static GraphicsDevice getSizeOfMonitor(int screen) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] monitors = ge.getScreenDevices();

		if (screen >=0 && screen < monitors.length) {
			return monitors[screen];
		} else if (monitors.length > 0) {//If they entered a number for a monitor that doesn't exist
			return monitors[0];
		} else {
			throw new RuntimeException ("No screens found, the fuck is u doin");
		}
		

	}

	
	public static Random rand() {
		return new Random();
	}


	public static float monteCarlo() {
		while (true) {
			float r1 = rand().nextFloat();
		      float r2 = rand().nextFloat();
		      if (r2>r1) {return r1;}
		}
	}
	//Distance between two points
	public static float getDistance(float x1, float y1, float x2, float y2) {
		float xDiff = x1 - x2;
		float yDiff = y1 - y2;
		if (Float.isNaN(xDiff) || Float.isNaN(yDiff)) {
			return (float) 0;
		}
		return (float) Math.sqrt((Math.pow(xDiff, 2)+Math.pow(yDiff, 2)));
	}

	public static String getName() {
		return names[rand().nextInt(names.length)];
	}
	
	
}