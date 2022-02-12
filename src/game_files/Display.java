package game_files;

import java.awt.Canvas;
import java.awt.Dimension;//	Screen resolution
import java.awt.GraphicsDevice;//	Monitors
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {
	private static JFrame frame;
	private Canvas canvas;//	Draw your images to canvas, then add it to JFrame to see on screen
	
	private String title;
	private int width, height;
	private boolean isCustomSize;
	
	public Display (String title, GraphicsDevice wantedDisplay, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		isCustomSize = false;
		
		createDisplay(wantedDisplay);
		
		this.title = title;
		
	}
	public Display (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		isCustomSize = true;
		
		createDisplay();
	}

	private void createDisplay() {//	If no screen is specified, default to screen 0
		createDisplay(null);
	}
	private void createDisplay(GraphicsDevice wantedDisplay) {
		frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//	May not stop the program when closing window without this line
		frame.setVisible(true);//	these things aren't visible by default lmao java
		
		if (isCustomSize) {
			frame.setSize(width, height);
			frame.setLocationRelativeTo(null);
		} else {//	Make full screen if not custom size
			//chooseScreen(screen);
			wantedDisplay.setFullScreenWindow(frame);
		}
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));//	Ensure it must always be width and height
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();//	Ensure all of canvas is visible
	}
	
	
	public Canvas getCanvas() {
		return canvas;
	}
}
