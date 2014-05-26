package test;

import task2.Player;
import task2.Game;
import task2.Tile;
import task2.HazardousMap;
import task2.HazardousMapCreator;
import task2.MapGenerator;
import task2.MapGeneratorCreator;
import task2.SafeMap;
import task2.SafeMapCreator;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameHazardousMapTest {
	Game gameH;
	MapGenerator map2 = null;
	Tile[] HazardousT;
	Player[] player;
	int winX2, winY2 =0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int numOfPlayers = 5;
		int mapSide = 10;
		gameH = new Game();
		map2 = HazardousMap.getMapInstance(10);
		HazardousT = map2.returnArray();
		player = new Player [numOfPlayers];

		player[1] = new Player();
		player[2] = new Player();
		player[3] = new Player();


		player[1].currentposX =5; //winner 
		player[1].currentposY =9; 

		player[2].currentposX = 1;
		player[2].currentposY = 4;

		player[1].setTeam(1);
		player[2].setTeam(3);

		winX2 = HazardousT[1].tileX =5;
		winY2 = HazardousT[1].tileY =9;

		//tileTypes Safe
		HazardousT[1].tileType = 1;
		HazardousT[3].tileType = 0;
		HazardousT[2].tileType = 2;
		HazardousT[4].tileType = 1;
	
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMapInstance(){
		MapGenerator.map = null;
		HazardousMap.getMapInstance(25);
		
		assertTrue(MapGenerator.map instanceof HazardousMap);
		
		MapGenerator.map = HazardousMap.getMapInstance(30);
		HazardousMap.getMapInstance(30);
		
		assertTrue(MapGenerator.map instanceof HazardousMap);

	}
	
	@Test
	public void testCheckWater(){
		
		assertEquals(1, HazardousT[1].tileType);
		assertNotEquals(1, HazardousT[3].tileType);		
	}
	
	@Test
	public void testTypeSet(){
		MapGenerator.map= HazardousMap.getMapInstance(25);
		MapGenerator.map.winTileCreated =true;
		MapGeneratorCreator m = new MapGeneratorCreator();
		MapGenerator.map.generateLoop();
		
		assertEquals(null, m.typeSet(5));
	}
	
	@Test
	public void testCheckWin(){
	
		//Hazardous Map
		assertEquals(2, HazardousT[2].tileType);
		assertEquals(winX2,player[1].currentposX );
		assertEquals(winY2, player[1].currentposY);
		
	}
	
	
	
}
