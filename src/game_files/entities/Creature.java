package game_files.entities;

import javax.swing.text.Position;

import game_files.HelperMethods;

public abstract class Creature extends Entity {
	private int health, maxHealth, getHungryThreshold, willEatThreshold;
	private float directionX, directionY, speed, hunger, smellSize;
	private String name;
	public Creature(float x, float y, int startSize, String name) {
		super(x, y, startSize);
		this.maxHealth = super.getSize()*3;
		this.speed = (float)super.getSize()/50;
		this.hunger = 100;
		this.health = maxHealth;
		this.directionX = 3000;
		this.directionY = y;
		this.getHungryThreshold = 50;
		this.willEatThreshold = 70;
		if (name == null)
			this.name = HelperMethods.getName();
		else 
			this.name = name;
	}
	
	
	public void navigateTowardsDirection() {
		float newDirX = getDirectionX() - getPositionX();
		float newDirY = getDirectionY() - getPositionY();
		//Normalize this
		float vectorLength = (float) Math.sqrt(Math.pow(newDirX, 2) + Math.pow(newDirY, 2));
		float normalizedDirX = newDirX/vectorLength;
		float normalizedDirY = newDirY/vectorLength;

		float velocityX = normalizedDirX*getSpeed();
		float velocityY = normalizedDirY*getSpeed();

		setPosition(getPositionX()+velocityX, getPositionY()+velocityY);
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
	public int getHungryThreshold() {
		return getHungryThreshold;
	}
	public void setHungryThreshold(int hungerThreshold) {
		this.getHungryThreshold = hungerThreshold;
	}
	public int getWillEatThreshold() {
		return willEatThreshold;
	}
	public void setWillEatThreshold(int willEatThreshold) {
		this.willEatThreshold = willEatThreshold;
	}
	
	
}
