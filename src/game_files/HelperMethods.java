package game_files;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Random;

public class HelperMethods {

	public static final int SLIME_START_AMT = 30;
	public static final float INVISIBLE_BOUNDARY = 30;
	public static int screenWidth;
	public static int screenHeight;
	public static String[] names = {"BARN HARNIT", "EGG NAD"};


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


	public static String getName() {
		return names[rand().nextInt(names.length)];
	}
	
	
}