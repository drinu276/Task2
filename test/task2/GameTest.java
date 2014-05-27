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

import org.junit.*;



public class GameTest {
	
	MapGenerator map = null;
	Game game;
	Tile[] arrayTiles;
	Player[] player;
	Player [] player2;
	boolean[][] visited ;
	int winX, winY  =0;
	int waterX, waterY =0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int numOfPlayers = 7;
		int mapSide = 5;
		game = new Game();
		map = SafeMap.getMapInstance(5);
		arrayTiles = map.returnArray();
		player = new Player [numOfPlayers];
		player2 = new Player [7];
		
		player[0] = new Player();
		player[1] = new Player();
		player[2] = new Player();
		player[3] = new Player();
		
		player[0].currentposX = 1;
		player[0].currentposY = 2;
		
		player[1].currentposX =3; //winner 
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
		player2[5] = new Player();
		player2[6] = new Player();
		
		player2[0].currentposX =1;
		player2[0].currentposY =2; 
			
		player2[1].currentposX =4;
		player2[1].currentposY =5; 
		
		player2[2].currentposX = 6;
		player2[2].currentposY = 7;
		
		player2[3].currentposX = 6;
		player2[3].currentposY = 9;
		
		player2[4].currentposX = 9; 
		player2[4].currentposY = 9;
		
		player2[5].currentposX = 3;//winner
		player2[5].currentposY = 4;
		
		player2[6].currentposX = 2;//water
		player2[6].currentposY = 2;
		
		player2[2].team =2; //no teams
		
		winX = arrayTiles[1].tileX =3;
		winY = arrayTiles[1].tileY =4;
		
		waterX = arrayTiles[3].tileX =2;
		waterY = arrayTiles[3].tileY =2;
				
		arrayTiles[1].tileType = 2;
		arrayTiles[3].tileType = 1;
		arrayTiles[2].tileType = 1;
		arrayTiles[4].tileType = 0;
		arrayTiles[5].tileType = 0;
		
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
		
		int playerNum=2;
		Player[] player3;
		player3 = new Player [playerNum];
		game.gameType('N');
		
		if(game.gameType('N')|| game.gameType('n')){
			for(int i=0; i<playerNum; i++){
				player3[i]= new Player();
				player3[i].setTeam(i);
			}
			assertEquals(0, player3[0].getTeam());
			assertEquals(1, player3[1].getTeam());
			assertNotEquals(1, player3[0].getTeam());
		}
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
	public void testOutputCurrentPos(){
		assertEquals(true,game.outputCurrentPos(player2));
		assertEquals(true, game.outputCurrentPos(player));
		
	}
	
	@SuppressWarnings("null")
	@Test
	public void testSetUpTeams(){
		int playerNum =3;	
		int playerCount =0;
		int numberOfTeams=3;
		int playersRem =playerNum - numberOfTeams;
		Player[] arrayPlayers = new Player[playerNum];

		game.setUpTeams(3,playerNum);		
		assertEquals(0, game.arrayPlayers[0].getTeam());
		assertEquals(1, game.arrayPlayers[1].getTeam());	
	}

	@Test
	public void testSetUpTeams2(){
		Player[] arrayPlayers;
		int playerNum =4;		
		game.setUpTeams(3,playerNum);		
		
		assertEquals(0, game.arrayPlayers[0].getTeam());
		assertEquals(1, game.arrayPlayers[1].getTeam());
		assertEquals(2, game.arrayPlayers[2].getTeam());
		assertNotSame(game.arrayPlayers[2], game.arrayPlayers[1]);
		
		assertNotNull(game.arrayPlayers[3].getTeam()); // cant check which team since this is allocated randomely
	}
		
	@Test
	public void testMovePlayers(){
		//passes
		assertEquals(true, game.movePlayers('u', player2, 1));
		assertEquals(true, game.movePlayers('d', player2, 2));
		assertEquals(true, game.movePlayers('r', player2, 4));
		assertEquals(true, game.movePlayers('l', player2, 3)); 
		assertEquals(true, game.movePlayers('R', player2, 1));
		//fail
		assertEquals(false, game.movePlayers('g', player2, 3));
		assertEquals(false, game.movePlayers('f', player2, 4));
	}
	
	public void testcreateTilesArray(){
		MapGeneratorCreator map2 = new MapGeneratorCreator();
		MapGenerator a = map2.createMap(game.mapSide, game.mapsType);
		a.returnArray();
		game.createTilesArray();
		assertNotNull(game.createTilesArray());
		
	}
	
	@Test
	public void testStartPositions(){
		game.mapSide = 5; 
		game.mapsType = 1;
		Tile[] arrayTiles2;
		MapGeneratorCreator map2 = new MapGeneratorCreator();
		MapGenerator a = map2.createMap(game.mapSide, game.mapsType);
		arrayTiles2 = a.returnArray();
		
		game.startPositions();
		
	}
		
	@Test
	public void testCheckWin(){
		int[] winners = new int[2];
		//winners[0] = 1;
		
		for (int i = 0; i < arrayTiles.length; i++) {
			if (arrayTiles[i].tileType == 2) {
				winX = arrayTiles[i].tileX;
				winY = arrayTiles[i].tileY;
				assertEquals(2, arrayTiles[i].tileType);				
				System.out.println(winX);
			}
		}
		
		for (int i = 0; i<winners.length; i++){
			if(player[i].currentposX == winX && player[i].currentposY == winY) {
				assertEquals(winX, player[i].currentposX);
				assertEquals(winY, player[i].currentposY);
				winners[i] = 1;
			}
		}
		
		game.checkWin(arrayTiles, player,2);
		assertEquals(winners.length, game.checkWin(arrayTiles, player,2).length);
		assertEquals(winners[0], game.checkWin(arrayTiles, player, 2)[0]);		
	}
	
	@Test
	public void testCheckWater(){
		game.mapSide =5;
		int playersNum = 4;
		game.visited = new boolean[4][game.mapSide*game.mapSide];
		
		for (int i = 0; i < arrayTiles.length; i++) {
			for (int j = 0; j < playersNum; j++) {
				if (arrayTiles[i].tileType == 1) {
					assertEquals(1, arrayTiles[i].tileType);				
					
					if ((player2[j].currentposX == arrayTiles[i].tileX) && (player2[j].currentposY == arrayTiles[i].tileY)) {
						assertEquals(arrayTiles[i].tileX, player2[i].currentposX);
						assertEquals(arrayTiles[i].tileY, player2[i].currentposY);
					
						
						int currTeam = player2[j].getTeam();
					
						for (int k = 0; k < playersNum; k++) {
							if (player2[k].getTeam() == currTeam) {
								assertEquals(currTeam, player2[k].getTeam());
								visited[k][i] = true;
								assertEquals(true,visited[k][i]);
							}
						}
						System.out.println("Player " + j + " you have fallen in the water");
						player2[j].currentposX = player2[j].startposX;
						player2[j].currentposY = player2[j].startposY;
					}
				}
			}
		}
		
		game.checkWater(arrayTiles, player2, 4);
		assertEquals(0, game.checkWater(arrayTiles, player2, 4));	
		assertEquals(visited[1][1], game.visited[1][1]);		
	}
	
	
	@Test
	public void testAddUncovered(){
		System.out.println("testAddUncovered");
		int playersNum = 4;
		game.mapSide = 5;

		MapGenerator map2 = SafeMap.getMapInstance(25);
		map2.generateLoop();
		game.startPositions();
		
		game.visited = new boolean[4][game.mapSide*game.mapSide];

		/*for (int i = 0; i < playersNum; i++) {
			assertNotEquals(i,playersNum);
			
			for (int j = 0; j < arrayTiles.length; j++) {
				if ((arrayTiles[j].tileX == player2[i].startposX) && (arrayTiles[j].tileY == player2[i].startposY)) {
					
					int currTeam = player2[i].getTeam();
					for (int k = 0; k < playersNum; k++) {
						assertEquals(currTeam, player2[k].getTeam());

						if (player2[k].getTeam() == currTeam) {
							visited[k][j] = true;

						}
					}
				}
			}
		}
		*/
		
		game.addUncovered(arrayTiles, player, 4);
	//	assertEquals(visited[1][1], game.visited[1][1]);
		
		
		assertEquals(1 ,player[1].getTeam());
		assertEquals(0, player[3].getTeam());
		
		assertEquals(true, visited[1][2]);
		assertEquals(true , visited[2][5]);
		assertEquals(false, visited[0][5]);	
	}
	
		
	@Test
	public void testGenerateHtml() {
		System.out.println("testGenerateHTML");
		MapGenerator map2 = SafeMap.getMapInstance(25);
		game.mapSide= 5;
		map2.generateLoop();
		game.visited = new boolean[4][game.mapSide*game.mapSide];
		
		File file1 = new File ("map_player_0.html");
		File file2 = new File ("map_player_1.html");
		File file3 = new File("maps");
		
		game.startPositions();;
		game.generateHtml(2);
		
		
		for(int i=0; i<game.mapSide;i++){
			for(int j=0; j<game.mapSide;j++){
				if(arrayTiles[game.mapSide*i+j].tileType==1){
					player[2].setX(i);
					player[2].setY(j);
					visited[2][game.mapSide*i+j] =true;
				}
			}
		}
				
		game.generateHtml(0);
		
		for(int i=0; i<game.mapSide;i++){
			for(int j=0; j<game.mapSide;j++){
				if(arrayTiles[game.mapSide*i+j].tileType==2){
					player[0].setX(i);
					player[0].setY(j);
					visited[0][game.mapSide*i+j] =true;
				}
			}
		}

		
		game.generateHtml(1);
		
		assertTrue(file1.exists());
		assertTrue(file2.exists());
		
		assertFalse(file3.exists());
		assertNotSame(file1,file2);
		
	}
	
	
}
