package game_files.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.text.Position;

public abstract class Entity {
	private float x, y;
	private int size;
	
	private static ArrayList<Entity> relevantEntities;
	private static ArrayList<Entity> entitiesToRemove; // Will be removed after loops finish to avoid concurrent modification exception
	
	public Entity (float x, float y, int startSize) {
		this.x = x;
		this.y = y;
		this.size = startSize;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	
	public void killSelf() {
		//remove references to self, Garbage collector should remove it
//		relevantEntities.remove(this);
		entitiesToRemove.add(this);
	}
	
	
	
	public float getPositionX() {
		return x;
	}
	public float getPositionY() {
		return y;
	}
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public static ArrayList<Entity> getRelevantEntities() {
		return relevantEntities;
	}
	public static void setRelevantEntities(ArrayList<Entity> rE) {
		relevantEntities = rE;
	}

	public static ArrayList<Entity> getEntitiesToRemove() {
		return entitiesToRemove;
	}

	public static void setEntitiesToRemove(ArrayList<Entity> entitiesToRemove) {
		Entity.entitiesToRemove = entitiesToRemove;
	}
	
	
	
}
