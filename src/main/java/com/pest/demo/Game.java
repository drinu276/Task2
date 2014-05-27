package task2;
/*
 import java.util.Scanner;
 import java.util.Random;*/

import java.io.*; // imported to use PrintWriter 
import java.util.*;

public class Game {

	Scanner sc = new Scanner(System.in); //Used for player input
	boolean won = false; //Controls ending of game
	boolean first = true; //Used to control what is run first time only
	boolean teams = false;
	int turn; //Holds turn number
	int playersNum; //Holds number of players playing
	public int mapSide; //Holds length of side of map
	public int mapsType; //chooses the type of map that the player wants
	int[] winners;
	char[] playerMoves; //Array that stores player
	public boolean[][] visited;
	Tile[][] arrayTiles; //Multidimensional array that stores arrays of type Tile, one for each player
	Tile[] arrayTiles2;
	public Player[] arrayPlayers; //Array of type player that stores current and starting position of each player
	Random rand = new Random();
	char gamesType;
	int teamsNumber;

	public Game() {
		turn = 1;
		playersNum = 0;
		mapSide = 0;
		mapsType = 0;
		teamsNumber = 0;

	}

	public int loop() {
	//	getMoves();
		for (int i = 0; i < playersNum; i++) {
			System.out.println("Enter direction of movement, player " + i + ", ((U)p, (D)own, (L)eft or (R)ight) :");
			playerMoves[i] = sc.next().charAt(0);
			movePlayers(playerMoves[i], arrayPlayers, i);
			
		}
		
		//movePlayers();
		checkWater(arrayTiles2, arrayPlayers, playersNum);
		addUncovered(arrayTiles2, arrayPlayers, playersNum);
		winners = checkWin(arrayTiles2, arrayPlayers, playersNum);
		for (int i = 0; i < playersNum; i++) {
			if (winners[i] == 1) {
				won = true;
				System.out.println("Player " + i + " won");
			}
		}

		for (int counter = 0; counter < playersNum; counter++) {
			generateHtml(/*arrayPlayers[counter], */counter);
		}	
		outputCurrentPos(arrayPlayers);
		
		if (won) {
			for (int i = 0; i < playersNum; i++) {
				if (winners[i] == 1) {
					System.out.println("Game over. Player " + i + " won, in turn " + turn);
				}
			}
		} else {
			turn++;
			loop();
		}
		return 0;
	}

	public int start() {
		startQuestions();
		createTilesArray();
		startPositions();
		visited = new boolean[playersNum][mapSide * mapSide];
		playerMoves = new char[playersNum];
		addUncovered(arrayTiles2, arrayPlayers, playersNum);
		outputCurrentPos(arrayPlayers);
		
		for (int counter = 0; counter < playersNum; counter++) {
			generateHtml(/*arrayPlayers[counter],*/ counter);
		}
		System.out.println("\nGame Start\n");
		loop();
		return 0;
	}

	public int startQuestions() {
		while (checkNumberOfPlayers(playersNum) == false) {
			System.out.println("How many players will be playing? (2 to 8 players)\n");
			playersNum = sc.nextInt();
			arrayPlayers = new Player[playersNum];
			System.out.println();
		}

		while (checkMapSize(playersNum, mapSide) == false) {
			mapSide = sc.nextInt();
			System.out.println();
		}

		while (mapType(mapsType) == 0) {
			System.out.println("Would you like to have a Safe map (1) or a hazardous map (2)? ");
			mapsType = sc.nextInt();
			System.out.println();
		}

		while (gameType(gamesType) == false) {
			System.out.println("Would you like to play in collaborative mode (Team Mode)? (Y/y or N/n)");
			gamesType = sc.next().charAt(0);
			System.out.println();
		}
		
			System.out.println("How many teams will be playing? (There can not be more players than teams)");
			teamsNumber = sc.nextInt();
			System.out.println();
			if (checkNumberOfTeams(teamsNumber, playersNum)) {
				setUpTeams(teamsNumber, playersNum);
			}
		return 0;
	}

	public boolean checkNumberOfTeams(int teamNum, int playNum) {
		if (teamNum > playNum) {
			System.out.println("Too many Teams");
			return false;
		} else {
			teamsNumber = teamNum;
			System.out.println("Number of Teams: " + teamsNumber);
			System.out.println("Number of Players:  " + playersNum);
			System.out.println();
			return true;
		}
	}

	public boolean gameType(char gameType) {
		//boolean done = false;
		if (gameType == 'N' || gameType == 'n') {
			for (int i = 0; i < playersNum; i++) {
				arrayPlayers[i] = new Player();
				arrayPlayers[i].setTeam(i);
			}
			return true;
		} else if (gameType == 'Y' || gameType == 'y') {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNumberOfPlayers(int playNum) {
		if (playNum > 8 || playNum < 2) {
			return false;
		} else {
			playersNum = playNum;
			return true;
		}
	}

	public boolean checkMapSize(int playersNum, int mapSide) {
		if (playersNum >= 2 && playersNum <= 4) {
			if (mapSide > 50 || mapSide < 5) {
				System.out.println("What size will the edge of the map be? (5 to 50 tiles)\n");
				return false;
			} else {
				return true;
			}
		} else {
			if (mapSide > 50 || mapSide < 8) {
				System.out.println("What size will the edge of the map be? (8 to 50 tiles)\n");
				return false;
			} else {
				return true;
			}
		}
	}

	public int mapType(int mapsType) {
		if (mapsType == 1) {
			return 1;
		} else if (mapsType == 2) {
			return 2;
		} else {
			return 0;
		}
	}

	public void setUpTeams(int numberOfTeams, int numberOfPlayers) {
		arrayPlayers = new Player [numberOfPlayers];
		int playerNumber = 0;
		int playersRem = numberOfPlayers - numberOfTeams;

		for (int i = 0; i < numberOfTeams; i++) { //place the first n players in the first n teams
			arrayPlayers[playerNumber] = new Player();
			arrayPlayers[i].setTeam(i);
			playerNumber++;
		}

		while (playersRem > 0) {

			if (playersRem > numberOfTeams) {
				for (int i = 0; i < numberOfTeams; i++) {
					arrayPlayers[playerNumber] = new Player();
					arrayPlayers[playerNumber].setTeam(i);
					playerNumber++;
					playersRem--;
				}
			} else {
				for (int i = 0; i < playersRem; i++) {
					arrayPlayers[playerNumber] = new Player();
					arrayPlayers[playerNumber].setTeam(i);
					playerNumber++;
					playersRem--;
				}
			}
		}        for(int i =0 ; i < arrayPlayers.length; i++){
			System.out.println("Player " + i + "is in Team " + arrayPlayers[i].getTeam());
		}
	}

	public int createTilesArray() {
		
		MapGeneratorCreator mg = new MapGeneratorCreator();
		MapGenerator a = mg.createMap(mapSide, mapsType);
		arrayTiles2 = a.returnArray();
		return 0;
	}

	public void startPositions() {
		int length = (mapSide * mapSide);
		boolean done;
		int[][] playersStartPosition = new int[playersNum][2];
		int counter = 0;
		while (counter < playersNum) {
			done = false;
			while (!done) {
				double a = (length * rand.nextDouble());
				int f = (int) Math.round(a);
				if (f < 0) {
					f = 0;
				} else if (f > (length)) {
					f = (length);
				}
				if (arrayTiles2[f].tileType == 0) {
					playersStartPosition[counter][0] = arrayTiles2[f].tileX;
					playersStartPosition[counter][1] = arrayTiles2[f].tileY;
					done = true;
				}
			}
			counter++;
		}

		for (int i = 0; i < playersNum; i++) {
			if (arrayPlayers[i] == null) {
				arrayPlayers[i] = new Player();
			}
			arrayPlayers[i].setX(playersStartPosition[i][0]);
			arrayPlayers[i].setY(playersStartPosition[i][1]);
		}
	}

	/*public void getMoves() {
		for (int i = 0; i < playersNum; i++) {
			System.out.println("Enter direction of movement, player " + i + ", ((U)p, (D)own, (L)eft or (R)ight) :");
			playerMoves[i] = sc.next().charAt(0);
			movePlayers(playerMoves[i], arrayPlayers, i);
			
		}
	}*/

	public boolean movePlayers(char playerMoves, Player arrayPlayers[] ,int i) {
		//for (int i = 0; i < playersNum; i++) {
			if (playerMoves == 'u' || playerMoves == 'U') {
				if (arrayPlayers[i].currentposX != 0) {
					arrayPlayers[i].moveUp();
				}
			} else if (playerMoves == 'd' || playerMoves == 'D') {
				if (arrayPlayers[i].currentposX != mapSide - 1) {
					arrayPlayers[i].moveDown();
				}
			} else if (playerMoves == 'l' || playerMoves == 'L') {
				if (arrayPlayers[i].currentposY != 0) {
					arrayPlayers[i].moveLeft();
				}
			} else if (playerMoves == 'r' || playerMoves == 'R') {
				if (arrayPlayers[i].currentposY != mapSide - 1) {
					arrayPlayers[i].moveRight();
				}
			} else {
				System.out.println("Player " + i + " entered an invalid move.");
				return false;
			}
		//}
			return true;
	}

	public int checkWater(Tile[] arrayTiles2, Player[] arrayPlayers, int playersNum) {
		for (int i = 0; i < arrayTiles2.length; i++) {
			for (int j = 0; j < playersNum; j++) {
				if (arrayTiles2[i].tileType == 1) {
					if ((arrayPlayers[j].currentposX == arrayTiles2[i].tileX) && (arrayPlayers[j].currentposY == arrayTiles2[i].tileY)) {
						int currTeam = arrayPlayers[j].getTeam();
						for (int k = 0; k < playersNum; k++) {
							if (arrayPlayers[k].getTeam() == currTeam) {
								visited[k][i] = true;
							}
						}
						System.out.println("Player " + j + " you have fallen in the water");
						arrayPlayers[j].currentposX = arrayPlayers[j].startposX;
						arrayPlayers[j].currentposY = arrayPlayers[j].startposY;

					}
				}
			}
		}
		return 0;
	}

	public int[] checkWin(Tile[] arrayTiles2, Player[] arrayPlayers, int playersNum) {
		int winX = 0, winY = 0;
		int[] winPlayers = new int[playersNum];

		for (int i = 0; i < arrayTiles2.length; i++) {
			if (arrayTiles2[i].tileType == 2) {
				winX = arrayTiles2[i].tileX;
				winY = arrayTiles2[i].tileY;
			}
		}

		for (int i = 0; i < winPlayers.length; i++) {
			winPlayers[i] = 0;
		}

		for (int i = 0; i < winPlayers.length; i++) {
			if (arrayPlayers[i].currentposX == winX && arrayPlayers[i].currentposY == winY) {
				winPlayers[i] = 1;
			}
		}
		return winPlayers;
	}

	public void addUncovered(Tile[] arrayTiles2, Player[] arrayPlayers, int playersNum) {
		if (first) {
			first = false;
			for (int i = 0; i < playersNum; i++) {
				for (int j = 0; j < arrayTiles2.length; j++) {
					if ((arrayTiles2[j].tileX == arrayPlayers[i].startposX) && (arrayTiles2[j].tileY == arrayPlayers[i].startposY)) {
						int currTeam = arrayPlayers[i].getTeam();
						for (int k = 0; k < playersNum; k++) {
							if (arrayPlayers[k].getTeam() == currTeam) {
								visited[k][j] = true;
							}
						}
					}
				}
			}

			for (int i = 0; i < playersNum; i++) {
				for (int j = 0; j < arrayTiles2.length; j++) {
					if ((arrayTiles2[j].tileX == arrayPlayers[i].currentposX) && (arrayTiles2[j].tileY == arrayPlayers[i].currentposY)) {
						int currTeam = arrayPlayers[i].getTeam();
						for (int k = 0; k < playersNum; k++) {
							if (arrayPlayers[k].getTeam() == currTeam) {
								visited[k][j] = true;
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < playersNum; i++) {
				for (int j = 0; j < arrayTiles2.length; j++) {
					if ((arrayTiles2[j].tileX == arrayPlayers[i].currentposX) && (arrayTiles2[j].tileY == arrayPlayers[i].currentposY)) {
						int currTeam = arrayPlayers[i].getTeam();
						for (int k = 0; k < playersNum; k++) {
							if (arrayPlayers[k].getTeam() == currTeam) {
								visited[k][j] = true;
							}
						}
					}
				}
			}
		}
	}

	public boolean outputCurrentPos(Player arrayPlayers[]) {
		System.out.println();
		for (int i = 0; i < playersNum; i++) {
			System.out.println("Player " + i + " is at coords: y: " + arrayPlayers[i].currentposY + ", x: " + arrayPlayers[i].currentposX);
		}
		System.out.println();
		return true;
	}

	public void generateHtml(/*Player player,*/int playerNumber) { 

		PrintWriter fileCreate = null;
		try {
			fileCreate = new PrintWriter("map_player_" + playerNumber + ".html"); // creating an html file for each player starting from 1 to 8
		} catch (Exception e) {
			System.out.println("File does not exist!!");
		}

		fileCreate.println("<html>");
		fileCreate.println("<title> player " + playerNumber + "</title>");
		fileCreate.println("<meta http-equiv=\"refresh\" content=\"3\" >");
		fileCreate.println("<h1><b><center> Player " + playerNumber + "<center></b></h1>");
		fileCreate.println("<body>");
		fileCreate.println("<table style = \"margin:0px auto; border \"75\" cellspacing \"21\" cellpadding = \"20\" bgcolor =006633 \"\"; >");

		for (int i = 0; i < mapSide; i++) {
			fileCreate.println("<tr>");
			for (int j = 0; j < mapSide; j++) {
				if (visited[playerNumber][mapSide * i + j] == false) {
					fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/hiddenTile.jpg \">"); //if player position agrees with current axis then player position image
				} else if (visited[playerNumber][mapSide * i + j] == true) {
					if (((arrayPlayers[playerNumber].getX() == (i)) && (arrayPlayers[playerNumber].getY() == (j))) && (arrayTiles2[mapSide * i + j].tileType == 2) == true) {
						fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/winTile.jpg \">"); //win
					} else if ((arrayPlayers[playerNumber].getX() == (i)) && (arrayPlayers[playerNumber].getY() == (j))) {
						fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/pirateTile.jpg \">"); //if player position agrees with current axis then player position image
					} else if (arrayTiles2[mapSide * i + j].tileType == 0) {
						fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/grassTile.jpg \">"); //grass
					} else if (arrayTiles2[mapSide * i + j].tileType == 1) {
						fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/waterTile.jpg \">"); //water
					}
				}
			}
			fileCreate.println("</tr>");
		}

		fileCreate.println("</table>");

		if (won) {
			if (winners[playerNumber] == 1) {
				fileCreate.println("<center><b><font size= \"20\"; face =\"Gotham\"; color = \"blue\">");
				fileCreate.println("Congratulations! YOU WIN!!!");
				fileCreate.println("</center></b></font>");
			} else {
				fileCreate.println("<center><b><font size= \"20\"; face =\"Gotham\"; color = \"blue\">");
				fileCreate.println("GAME OVER");
				fileCreate.println("</center></b></font>");
			}
		}
		fileCreate.println("</body>");
		fileCreate.println("</html>");
		fileCreate.close();

	}

}
