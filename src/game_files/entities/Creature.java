package game_files.entities;

import javax.swing.text.Position;

public abstract class Creature extends Entity {
	private int health, maxHealth;
	public Creature(float x, float y, int maxHealth) {
		super(x, y);
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
}
