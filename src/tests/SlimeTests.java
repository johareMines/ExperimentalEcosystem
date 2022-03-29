package tests;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game_files.entities.Berry;
import game_files.entities.Entity;
import game_files.entities.Slime;

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
		Entity.setEntitiesToRemove(new ArrayList<>());
		entities = Entity.getRelevantEntities();
		slime = new Slime(50, 50, 30, "The Tester");
		entities.add(slime);
	}
	
	@Test
	//Test slimes can get hungry
	void testGoHungry() {
		slime.setHunger(20);
		Berry berry = new Berry(50, 150, 10); //Spawn one a bit away
		entities.add(berry);
		slime.update();
		Assert.assertEquals(slime.getSlimeBehavior(), Slime.SlimeBehavior.TOWARDS_FOOD);
	}
	
	@Test
	//Test that slimes can eat
	void testEat() {
		slime.setHunger(20);
		Berry berry = new Berry(50, 50, 10); //Spawn one on the slime
		entities.add(berry);
		slime.update();
		Assert.assertTrue(slime.getHunger() > 20);// It should have eaten the berry
	}
	
	
	@Test
	//Test that slimes know how to select berries as targets
	void testBerryTargetSelection() {
		slime.setHunger(20);
		Berry berry = new Berry(50, 150, 10); //Spawn one a bit away
		entities.add(berry);
		slime.update();
		Assert.assertTrue(berry.getPositionX() == slime.getDirectionX());
		Assert.assertTrue(berry.getPositionY() == slime.getDirectionY());
	}
	
	@Test
	//Test that slimes can smell food
	void testSlimeSmell() {
		Berry berry = new Berry(50, 150, 10); //Spawn one a bit away
		Berry berry2 = new Berry(50, 100, 10); //Spawn one a bit away
		Berry berry3 = new Berry(50, 200, 10); // Too far
		entities.add(berry);
		entities.add(berry2);
		entities.add(berry3);
		slime.setSmellSize(100);
		slime.update();
		Assert.assertEquals(2, slime.getSmellableBerries().size());
	}
}
