package game_files.entities;

import javax.swing.text.Position;

import game_files.HelperMethods;

public abstract class Creature extends Entity {
	private int health, maxHealth, size, hungerThreshold;
	private float directionX, directionY, speed, hunger;
	private String name;
	public Creature(float x, float y, int startSize, String name) {
		super(x, y);
		this.size = startSize;
		this.maxHealth = size*3;
		this.speed = (float)size/50;
		this.hunger = 100;
		this.health = maxHealth;
		this.directionX = 3000;
		this.directionY = y;
		this.hungerThreshold = 50;
		if (name == null)
			this.name = HelperMethods.getName();
		else 
			this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getDirectionX() {
		return directionX;
	}
	public void setDirectionX(float dirX) {
		this.directionX = dirX;
	}
	public float getDirectionY() {
		return directionY;
	}
	public void setDirectionY(float dirY) {
		this.directionY = dirY;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getHunger() {
		return hunger;
	}
	public void setHunger(float hunger) {
		this.hunger = hunger;
	}
	public int getHungerThreshold() {
		return hungerThreshold;
	}
	public void setHungerThreshold(int hungerThreshold) {
		this.hungerThreshold = hungerThreshold;
	}
	
	
}
