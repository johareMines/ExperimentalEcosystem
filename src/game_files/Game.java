package game_files;

import java.awt.Color;
import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import game_files.gfx.Assets;
import game_files.gfx.ImageLoader;
import game_files.gfx.SpriteSheet;
import game_files.states.GameState;
import game_files.states.MenuState;
import game_files.states.State;

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
	
	//States
	private State gameState;
	private State menuState;
	
	
	///////////
//	private BufferedImage testImage;
//	private SpriteSheet sheet;
	//////////

	public Game(String title, int screen) {//	For full screen
		wantedDisplay = HelperMethods.getSizeOfMonitor(screen);
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
		Assets.init();
		
		
		gameState = new GameState();
		menuState = new MenuState();
		State.setState(gameState);
	}
	
	//int x=0;
	private void update() {
		//x+=1;
		if (State.getState() != null)
			State.getState().update();
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
		if (State.getState() != null)
			State.getState().render(g);
		
//		g.setColor(Color.pink);
//		g.fillRect(100, 500, 50, 50);
		
		//g.drawImage(Assets.player, x, 10, null);
		
//		g.drawImage(testImage, 100, 100, null);//	Null is image observer, don't know what it do
//		g.drawImage(sheet.crop(100, 0, 32, 32), 200, 800, null);
		
		//	Tell java we are done drawing
		bs.show();
		g.dispose();
	}
	public void run() {
		init();
		
		
		//FPS settings
		int fps = 144;
		
		double timePerTick = 1000000000 / fps; // 1 billion, = time in nanoseconds
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(gameRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000) {
				//System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
			///////End FPS//////////
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
