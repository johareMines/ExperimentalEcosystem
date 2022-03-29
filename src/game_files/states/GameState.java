package game_files.states;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import game_files.HelperMethods;
import game_files.entities.Berry;
import game_files.entities.Creature;
import game_files.entities.Entity;
import game_files.entities.Slime;
import game_files.gfx.Assets;

public class GameState extends State {
	//private static ArrayList<Entity> entities;
	
	private Slime slime;
	private ArrayList<Entity> entity;
	
	public GameState() {
		Entity.setRelevantEntities(new ArrayList<Entity>());
		Entity.setEntitiesToRemove(new ArrayList<Entity>());
		entity = Entity.getRelevantEntities();
		
		//Declare Slime Amount
		for (int i=0; i<HelperMethods.SLIME_START_AMT; i++) {
			int startX = HelperMethods.rand().nextInt(HelperMethods.screenWidth);
			int startY = HelperMethods.rand().nextInt(HelperMethods.screenHeight);
			int startSize = HelperMethods.rand().nextInt(20, 50);
			entity.add(new Slime(startX, startY, startSize));
		}
		
		//Insert some food
		for (int i=0; i<10; i++) {
			int startX = HelperMethods.rand().nextInt(HelperMethods.screenWidth);
			int startY = HelperMethods.rand().nextInt(HelperMethods.screenHeight);
			int startSize = HelperMethods.rand().nextInt(7, 15);
			entity.add(new Berry(startX, startY, startSize));
		}
	}
	
	
	@Override
	public void update() {
		for (Entity e : entity) {
			e.update();
		}
		//if (Entity.getEntitiesToRemove().s)
		entity.removeAll(Entity.getEntitiesToRemove());
	}

	@Override
	public void render(Graphics g) {
		for (Entity e : entity) {
			e.render(g);
		}
	}

}
