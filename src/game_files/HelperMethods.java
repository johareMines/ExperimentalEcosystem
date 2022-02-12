package game_files;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class HelperMethods {

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
}
