package game_files.entities;

import java.awt.Graphics;

import javax.swing.text.Position;

import game_files.gfx.Assets;

public class Slime extends Creature{
	
	public Slime(float x, float y, int maxHealth) {
		super(x, y, maxHealth);
	}

	@Override
	public void update() {
		super.setPosition(getPositionX()+1, getPositionY()+1);
	}

	@Override
	public void render(Graphics g) {
		//g.drawImage(Assets.player, 0, 0, null);
		g.drawRect((int)super.getPositionX(), (int)super.getPositionY(), 30, 30);
		
	}
}
