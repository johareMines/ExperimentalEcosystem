package game_files;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import game_files.HelperClasses.GetScreenSize;

//Runnable makes game run on its own thread
public class Game implements Runnable {//	Main game class
	private Display display;
	private GraphicsDevice wantedDisplay = null;
	public static final int NUM_BUFFERS = 3;

	private int width, height;
	private String title;

	private boolean gameRunning = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;

	public Game(String title, int screen) {//	For full screen
		wantedDisplay = HelperClasses.GetScreenSize.getSizeOfMonitor(screen);
		DisplayMode dm = wantedDisplay.getDisplayMode();//	Allows to get resolution
		this.width = dm.getWidth();
		this.height = dm.getHeight();
		this.title = title;
	}
	public Game(String title, int width, int height) {// For custom resolution
		this.width = width;
		this.height = height;
		this.title = title;
	}
	//////////////////Constructors are done////////////////////////////////////////////////

	private void init() {
		//	Initializes graphics and gets things ready
		if (wantedDisplay == null)//	Custom resolution constructor
			display = new Display(title, width, height);
		else
			display = new Display(title, wantedDisplay, width, height);
		
		
	}
	private void update() {
		
	}
	private void render() {
		bs = display.getCanvas().getBufferStrategy();//	Tells computer how to draw to screen using buffer
		if (bs == null) {//	First time running
			display.getCanvas().createBufferStrategy(NUM_BUFFERS);//How many buffers? you can choose
			return;//	Can't render yet, return to avoid errors
		}
		
		//Allows us to draw to canvas, like a paint brush
		g = bs.getDrawGraphics();
		//Clear screen
		g.clearRect(0, 0, width, height);//	clear whole screen
		//Draw
		g.setColor(Color.pink);
		g.fillRect(100, 50, 500, height-100);
		
		//	Tell java we are done drawing
		bs.show();
		g.dispose();
	}
	public void run() {
		init();
		
		while(gameRunning) {
			update();
			render();
		}
		stop();
	}

	//Only use synchronized when using threads
	public synchronized void start() {
		if (gameRunning)
			return;//	Don't run it twice
		gameRunning = true;
		
		thread = new Thread(this);//	Pass in this game class to run
		thread.start();//	Calls run method
	}
	public synchronized void stop() {
		if (!gameRunning)
			return;
		gameRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
