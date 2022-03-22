package game_files.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.text.Position;

import game_files.HelperMethods;
import game_files.gfx.Assets;
import game_files.states.GameState;

public class Slime extends Creature{
	public enum SlimeBehavior {RANDOM, TOWARDS_FOOD};
	private SlimeBehavior behavior;

	private float directionVariance = 100;//	How much do they change their mind about where to go?

	//Constructors for testing classes
//	public Slime(float x, float y, int startSize, ArrayList<Entity> relevantEntities) {
//		this (x, y, startSize, (String) null);
//		this.relevantEntities = relevantEntities;
//	}
//	public Slime(float x, float y, int startSize, String name, ArrayList<Entity> relevantEntities) {
//		this (x, y, startSize, name);
//		this.relevantEntities = relevantEntities;
//	}
	//Constructors for tests ^^
	
	public Slime(float x, float y, int startSize) {
		this (x, y, startSize, (String) null);
	}
	public Slime(float x, float y, int startSize, String name) {
		super (x, y, startSize, name);
		setBehavior(SlimeBehavior.RANDOM);
	}

	@Override
	public void update() {
		selectAppropriateBehavior();
		actOnBehavior();
		eatFoodWhenCloseEnough();
	}

	
	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.player, 0, 0, null);
		g.drawRect((int)super.getPositionX(), (int)super.getPositionY(), 30, 30);

	}

	private void selectAppropriateBehavior() {
		if (super.getHunger() < 50) {
			setBehavior(SlimeBehavior.TOWARDS_FOOD);
		}
	}
	private void randomBehavior() {
		//System.out.println("Doing rand behav");
		float stepSize = HelperMethods.monteCarlo() * directionVariance;
		float xStepSize = HelperMethods.rand().nextFloat(stepSize);
		float yStepSize = HelperMethods.rand().nextFloat(stepSize);
		//Positive or negative change?
		for (int i=0; i<2; i++) {
			float isNegative = HelperMethods.rand().nextFloat(1);
			if (isNegative > 0.5) {
				if (i == 0)
					xStepSize *= -1;
				else
					yStepSize *= -1;
			}
		}
		//make target var here if want dev settings later
		super.setDirectionX(super.getDirectionX() + xStepSize);
		super.setDirectionY(super.getDirectionY() + yStepSize);
		//Bounds checks
		if (super.getDirectionX() < HelperMethods.INVISIBLE_BOUNDARY) {super.setDirectionX(super.getDirectionX()+10);}
		if (super.getDirectionX() > HelperMethods.screenWidth - HelperMethods.INVISIBLE_BOUNDARY) {super.setDirectionX(super.getDirectionX()-10);}
		if (super.getDirectionY() < HelperMethods.INVISIBLE_BOUNDARY) {super.setDirectionY(super.getDirectionY()+10);}
		if (super.getDirectionY() > HelperMethods.screenHeight - HelperMethods.INVISIBLE_BOUNDARY) {super.setDirectionY(super.getDirectionY()-10);}
		
		
		float newDirX = super.getDirectionX() - super.getPositionX();
		float newDirY = super.getDirectionY() - super.getPositionY();
		//Normalize this
		float vectorLength = (float) Math.sqrt(Math.pow(newDirX, 2) + Math.pow(newDirY, 2));
		float normalizedDirX = newDirX/vectorLength;
		float normalizedDirY = newDirY/vectorLength;
		
		float velocityX = normalizedDirX*super.getSpeed();
		float velocityY = normalizedDirY*super.getSpeed();
		
		//System.out.println("Step Size: " + stepSize + " | XSS " + xStepSize + " | YSS "+yStepSize + " | NewDirX "+ newDirX + " | newDirY " + newDirY + " | Vector Length " + vectorLength
		//		+ " | n.DirX " + normalizedDirX + " | n.dirY " + normalizedDirY + " | velX " +velocityX + " | velY " +velocityY + " | Speed " + super.getSpeed());
		
		super.setPosition(super.getPositionX()+velocityX, super.getPositionY()+velocityY);
	}
	private void eatFoodWhenCloseEnough() {
		for (Entity e : Entity.getRelevantEntities()) {
			if (e instanceof Berry) {
				float dist = HelperMethods.getDistance(super.getPositionX(), super.getPositionY(), e.getPositionX(), e.getPositionY());
				if (dist < super.getSize() + e.getSize()) {
					//Eat the berry
					super.setSize(super.getSize()+e.getSize());//Increase in size
					super.setHunger(super.getHunger()+e.getSize());//Reduce hunger
					if (super.getHunger() > 100) {
						super.setHunger(100);//Limit max
					}
					e.killSelf();
					break;//Break so the array doesn't loop too many times, as we just changed it
				}
			}
		}
	}
	
	private void actOnBehavior() {
		switch (behavior) {//	Choose what movement method to run
		case RANDOM:
			randomBehavior();
			break;
		case TOWARDS_FOOD:
			super.setPosition(getPositionX()+1, getPositionY()+1);
			break;
		}
	}
	
	public SlimeBehavior getSlimeBehavior() {
		return behavior;
	}
	public void setBehavior(SlimeBehavior behavior) {
		if (this.behavior == behavior)
			return;
		System.out.println("Slime " + super.getName()+ " selected behavior " + behavior);
		this.behavior = behavior;
	}
}

