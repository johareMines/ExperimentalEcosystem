package game_files.entities;

import java.awt.Graphics;

import javax.swing.text.Position;

public abstract class Entity {
	private float x, y;
	
	public Entity (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
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
}
