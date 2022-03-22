package tests;


import java.util.ArrayList;

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
	ArrayList<Entity> entities;
	
	@BeforeEach
	void setup() {
		Entity.setRelevantEntities(new ArrayList<>());
		entities = Entity.getRelevantEntities();
		slime = new Slime(50, 50, 30, "The Tester");
		entities.add(slime);
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
		Berry berry = new Berry(50, 50, 10); //Spawn one on the slime
		entities.add(berry);
		slime.update();
		Assert.assertTrue(slime.getHunger() > 20);// It should have eaten the berry
	}
	
	

}
