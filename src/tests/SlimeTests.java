package tests;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game_files.Game;
import game_files.HelperMethods;
import game_files.Launcher;
import game_files.entities.Berry;
import game_files.entities.Creature;
import game_files.entities.Entity;
import game_files.entities.Slime;
import game_files.states.GameState;
import game_files.states.State;

class SlimeTests {
//	static Game game;
//	static GameState state;
//	@BeforeAll
//	static void setup() {
//		Launcher.main(null);//Call main method in launcher
//		game = Launcher.getGameInstance();
//		game.start();
//		try {
//			Thread.sleep(500); // Manual sleep to allow the separate thread to initialize properly
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		state = (GameState) game.getState();
//	}
	
	Slime slime;
	
	@BeforeEach
	void setup() {
		slime = new Slime(50, 50, 30, "The Tester");
	}
	
	@Test
	void testGoHungry() {
		slime.setHunger(20);
		slime.update();
		Assert.assertEquals(slime.getSlimeBehavior(), Slime.SlimeBehavior.TOWARDS_FOOD);
	}
	
	@Test
	void testEat() {
		slime.setHunger(20);
		Berry berry = new Berry(50, 50); //Spawn one on the slime
		slime.update();
		Assert.assertTrue(slime.getHunger() > 20);
	}
	
	

}
