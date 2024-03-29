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
	private ArrayList<Berry> smellableBerries;

	private float directionVariance = 100;//	How much do they change their mind about where to go?

	public Slime(float x, float y, int startSize) {
		this (x, y, startSize, (String) null);
	}
	public Slime(float x, float y, int startSize, String name) {
		super (x, y, startSize, name);
		setBehavior(SlimeBehavior.RANDOM);
		smellableBerries = new ArrayList<>();
	}

	//Call all applicable functions
	@Override
	public void update() {
		cleanSelfArrays();
		costOfLiving();
		smellBerries();
		selectAppropriateBehavior();
		actOnBehavior();
		if (super.getHunger() < super.getWillEatThreshold()) {
			eatFoodWhenCloseEnough();
		}
	}

	//Draw the slime
	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.player, 0, 0, null);
		g.drawRect((int)getPositionX()-getSize()/2, (int)getPositionY()-getSize()/2, getSize(), getSize());

	}
	
	@Override
	public void cleanSelfArrays() {
		ArrayList<Berry> berriesToRemove = new ArrayList<>();
		for (Berry b : smellableBerries) {
			if (!Entity.getRelevantEntities().contains(b)) {
				berriesToRemove.add(b);
			}
		}
		smellableBerries.removeAll(berriesToRemove);
	}

	private void selectAppropriateBehavior() {
		if (super.getHunger() < super.getHungryThreshold()) {
			setBehavior(SlimeBehavior.TOWARDS_FOOD);
		} else {
			setBehavior(SlimeBehavior.RANDOM);
		}
	}
	private void actOnBehavior() {
		switch (behavior) {//	Choose what movement method to run
		case RANDOM:
			randomBehavior();
			break;
		case TOWARDS_FOOD:
			findFoodBehavior();
			break;
		}
	}
	//Decrement values according to the cost of living
	private void costOfLiving() {
		float costOfLiving = 0.1f;
		setHunger((float)(getHunger() - costOfLiving));
	}

	//Find close enough berries
	private void smellBerries() {
		ArrayList<Berry> allBerries = new ArrayList<>();
		for (Entity e : Entity.getRelevantEntities()) {
			if (e instanceof Berry) {
				float dist = HelperMethods.getDistance(getPositionX(), getPositionY(), e.getPositionX(), e.getPositionY());
				if (dist <= getSmellSize()) {
					smellableBerries.add((Berry) e);
				}
			}
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

		navigateTowardsDirection();

	}
	private void findFoodBehavior() {
		Berry closestBerry = null;
		//Find the closest berry
		for (Berry b : smellableBerries) {
			if (closestBerry == null) {
				closestBerry = (Berry) b;
				continue;
			}
			float distToClosest = HelperMethods.getDistance(getPositionX(), getPositionY(), closestBerry.getPositionX(), closestBerry.getPositionY());
			float distToE = HelperMethods.getDistance(getPositionX(), getPositionY(), b.getPositionX(), b.getPositionY());
			if (distToE < distToClosest) {
				closestBerry = b;
			}

		}

		//No berries found
		if (closestBerry == null) {
			setBehavior(SlimeBehavior.RANDOM);
			actOnBehavior();
		} else {
			//Navigate towards closest berry
			setDirectionX(closestBerry.getPositionX());
			setDirectionY(closestBerry.getPositionY());
			navigateTowardsDirection();
		}
	}

	private void eatFoodWhenCloseEnough() {
		for (Entity e : Entity.getRelevantEntities()) {
			if (e instanceof Berry) {
				float dist = HelperMethods.getDistance(getPositionX(), getPositionY(), e.getPositionX(), e.getPositionY());
				if (dist < getSize() + e.getSize()) {
					//Eat the berry
					setSize(getSize()+e.getSize());//Increase in size
					setHunger(getHunger()+e.getSize());//Reduce hunger
					if (getHunger() > 100) {
						setHunger(100);//Limit max
					}
					e.killSelf();
					break;//Break so the array doesn't loop too many times, as we just changed it
				}
			}
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
	public ArrayList<Berry> getSmellableBerries() {
		return smellableBerries;
	}
	public void setSmellableBerries(ArrayList<Berry> smellableBerries) {
		this.smellableBerries = smellableBerries;
	}
	

}

