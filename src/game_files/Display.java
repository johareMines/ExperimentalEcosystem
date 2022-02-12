package game_files;

import java.awt.Dimension;//	Screen resolution
import java.awt.GraphicsDevice;//	Monitors
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {
	private static JFrame frame;
	
	private String title;
	private int width, height;
	private boolean isCustomSize;
	
	public Display (String title, int screen) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.title = title;
		width = (int) size.getWidth();
		height = (int) size.getHeight();
		isCustomSize = false;
		
		createDisplay(screen);
	}
	public Display (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		isCustomSize = true;
		
		createDisplay();
	}

	private void createDisplay() {//	If no screen is specified, default to screen 0
		createDisplay(0);
	}
	private void createDisplay(int screen) {
		frame = new JFrame(title);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//	May not stop the program when closing window without this line
		frame.setVisible(true);//	these things aren't visible by default lmao java
		
		if (isCustomSize) {
			frame.setSize(width, height);
			frame.setLocationRelativeTo(null);
		} else {//	Make full screen if not custom size
			chooseScreen(screen);
		}
		
	}
	
	private static void chooseScreen(int screen) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] monitors = ge.getScreenDevices();
		
		if (screen >=0 && screen < monitors.length) {
			monitors[screen].setFullScreenWindow(frame);
		} else if (monitors.length > 0) {//If they entered a number for a monitor that doesn't exist
			monitors[0].setFullScreenWindow(frame);
		} else {
			throw new RuntimeException ("No screens found, the fuck is u doin");
		}
	}
}
