package game_files.states;

import java.awt.Graphics;

import game_files.entities.Slime;
import game_files.gfx.Assets;

public class GameState extends State {
	private Slime slime;

	
	public GameState() {
		slime = new Slime(50, 50, 100);
	}
	
	
	@Override
	public void update() {
		slime.update();
	}

	@Override
	public void render(Graphics g) {
		slime.render(g);
	}

}
