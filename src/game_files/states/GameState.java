package game_files.states;

import java.awt.Graphics;
import java.util.ArrayList;

import game_files.HelperMethods;
import game_files.entities.Entity;
import game_files.entities.Slime;
import game_files.gfx.Assets;

public class GameState extends State {
	private ArrayList<Entity> entities;
	
	private Slime slime;

	
	public GameState() {
		entities = new ArrayList<Entity>();
		
		//Declare Slime Amount
		for (int i=0; i<HelperMethods.SLIME_START_AMT; i++) {
			entities.add(new Slime(HelperMethods.rand().nextInt(HelperMethods.screenWidth), HelperMethods.rand().nextInt(HelperMethods.screenHeight)));
			
		}
	}
	
	
	@Override
	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}

	@Override
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}

}
