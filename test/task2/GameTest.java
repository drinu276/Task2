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

import java.io.*;

public class GameTest {
	
	MapGenerator map = null;
	Game game;
	Tile[] arrayTiles;
	///Tile[] HazardousT;
	Player[] player;
	Player [] player2;
	boolean[][] visited ;
	int winX, winY  =0;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int numOfPlayers = 5;
		int mapSide = 5;
		game = new Game();
		map = SafeMap.getMapInstance(5);
		arrayTiles = map.returnArray();
		player = new Player [numOfPlayers];
		player2 = new Player [numOfPlayers];
		
		player[1] = new Player();
		player[2] = new Player();
		player[3] = new Player();
		
		
		player[1].currentposX =3; //winner safe map
		player[1].currentposY =4; 
		
		player[2].currentposX = 5;
		player[2].currentposY = 3;
		
		//teams
		player[1].setTeam(1);
		player[2].setTeam(2);
		
		player2[0] = new Player();
		player2[1] = new Player();
		player2[2] = new Player();
		player2[3] = new Player();
		player2[4] = new Player();
		
		player2[0].currentposX =1;
		player2[0].currentposY =2; 
			
		player2[1].currentposX =4;
		player2[1].currentposY =5; 
		
		player2[2].currentposX = 6;
		player2[2].currentposY = 7;
		
		player2[4].currentposX = 9; //winner Hazardous Map
		player2[4].currentposY = 9;
		
		player2[2].team =2; //no teams
		
		//win Tiles Safe
		winX = arrayTiles[1].tileX =3;
		winY = arrayTiles[1].tileY =4;
		
		//win Tiles Haz
		/*winX2 = HazardousT[1].tileX =9;
		winY2 = HazardousT[1].tileY =9;*/
		
		
		//tileTypes Safe
		arrayTiles[1].tileType = 2;
		arrayTiles[3].tileType = 1;
		arrayTiles[2].tileType = 1;
		arrayTiles[4].tileType = 0;
		arrayTiles[5].tileType = 0;
	/*	
		//tilesTypes Hazardous
		HazardousT[1].tileType = 1;
		HazardousT[2].tileType = 0;
		HazardousT[4].tileType = 2;
		HazardousT[5].tileType = 1;*/
		
		visited = new boolean[numOfPlayers][mapSide * mapSide];
		
		visited[1][2] = true;
		visited[2][5] = true;
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckNumberOfTeams() {
			System.out.println("checkNumberOfTeams");
			assertEquals(false, game.checkNumberOfTeams(5, 4));
			assertEquals(false, game.checkNumberOfTeams(5,3));
			assertEquals(true, game.checkNumberOfTeams(3,4));
			assertEquals(true, game.checkNumberOfTeams(8,8));
	}
		
	@Test
	public void testGameType(){
		
		assertEquals(false, game.gameType('L')); //LETTER does not exist
		assertEquals(true, game.gameType('Y'));
		assertEquals(true, game.gameType('N'));
		assertEquals(true, game.gameType('n'));
		assertEquals(true, game.gameType('y'));
		
		assertEquals(2, player2[2].team);
	}
	
	@Test
	
	public void testCheckNumberOfPlayers() {
		System.out.println("checkNumberOfPlayers");
       
        assertEquals(true, game.checkNumberOfPlayers(4));
        assertEquals(false, game.checkNumberOfPlayers(0));
        assertEquals(false, game.checkNumberOfPlayers(9));
        assertEquals(true, game.checkNumberOfPlayers(8));
        assertEquals(true, game.checkNumberOfPlayers(2));
     
	}
	
	@Test
	public void testCheckMapSize() {
		System.out.println("testCheckMapSize");
		Game instance = new Game();
	
		assertEquals(true,instance.checkMapSize(4, 7));	
		assertEquals(true, instance.checkMapSize(8, 50));	
		assertEquals(false, instance.checkMapSize(3,4));
		assertEquals(false, instance.checkMapSize(8, 90));
		assertEquals(false, instance.checkMapSize(5, 6));
		assertEquals(false, instance.checkMapSize(3, 60));
		assertEquals(false, instance.checkMapSize(5,3));
			
	}
	
	@Test
	public void testMapType() {
		System.out.println("testMapType");

		assertEquals(1, game.mapType(1));
		assertEquals(2, game.mapType(2));
		assertEquals(0, game.mapType(4));
		
	}
	
	@Test
	public void testCheckWater(){
		//Safe Map
		assertEquals(1, arrayTiles[2].tileType);
		assertNotEquals(1, arrayTiles[1].tileType);
	}
	
	@Test
	public void testCheckWin(){
		//Safe Map
		assertEquals(2, arrayTiles[1].tileType);
		assertEquals(winX,player[1].currentposX );
		assertEquals(winY, player[1].currentposY);
	}
	
	@Test
	public void testAddUncovered(){
		System.out.println("testAddUncovered");
		
		assertEquals(1 ,player[1].getTeam());
		assertEquals(0, player[3].getTeam());
		
		assertEquals(true, visited[1][2]);
		assertEquals(true , visited[2][5]);
		assertEquals(false, visited[0][5]);	
	}
	
	
	@Test
	public void testGenerateHtml() {
		System.out.println("testGenerateHTML");
			
		File file = new File("mapPlayerTest");
		PrintWriter fileCreate = null;
		try{
			fileCreate = new PrintWriter(file);
		}catch(Exception e){
			
		}
		
		assertEquals(arrayTiles[4].tileType, 0);
		assertTrue(new File("mapPlayerTest").exists());	
		assertFalse(new File("testFail").exists());
	}
	
	@Test
	public void testMovePlayers(){
		
		
	}

}