package game_files.entities;

import javax.swing.text.Position;

import game_files.HelperMethods;

public abstract class Creature extends Entity {
	public static final int START_SIZE = 30;
	private int health, maxHealth, size;
	private float directionX, directionY, speed, hunger;
	private String name;
	public Creature(float x, float y, String name) {
		super(x, y);
		this.size = START_SIZE;
		this.maxHealth = size*3;
		this.speed = (float)size/50;
		this.hunger = 100;
		this.health = maxHealth;
		//this.directionX = x;
		this.directionX = 3000;
		this.directionY = y;
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
	
}
