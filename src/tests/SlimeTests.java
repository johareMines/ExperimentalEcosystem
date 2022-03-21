package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game_files.Game;
import game_files.HelperMethods;
import game_files.Launcher;
import game_files.entities.Creature;
import game_files.entities.Entity;
import game_files.entities.Slime;
import game_files.states.GameState;
import game_files.states.State;
import junit.framework.Assert;

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
	
	@SuppressWarnings("deprecation")
	@Test
	void testGoHungry() {
		Slime hungry = new Slime(50, 50, 30, "Hungry boi");
		hungry.setHunger(20);
		hungry.update();
		Assert.assertEquals(hungry.getSlimeBehavior(), Slime.SlimeBehavior.TOWARDS_FOOD);
	}
	
	

}
