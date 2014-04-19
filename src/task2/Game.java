package task2;

import java.util.Scanner;
import java.util.Random;

public class Game {

    Scanner sc = new Scanner(System.in); //Used for player input
    boolean won = false; //Controls ending of game
    boolean first = true; //Used to control what is run first time only
    int turn; //Holds turn number
    int playersNum; //Holds number of players playing
    int playerWon; //Holds ID of player that won
    int mapSide; //Holds length of side of map
    char[] playerMoves; //Array that stores player
    Tile[][] arrayTiles; //Multidimensional array that stores arrays of type Tile, one for each player
    Player[] arrayPlayers; //Array of type player that stores current and starting position of each player
    Random rand = new Random();

    public Game() {
        turn = 1;
        playersNum = 0;
        mapSide = 0;
    }

    void loop() {
        getMoves();
        movePlayers();
        checkWater();
        addUncovered();
        if (checkWin() == -1) {
            won = false;
        } else {
            won = true;
            playerWon = checkWin();
        }

        outputCurrentPos();
        if (won) {
            System.out.println("Game over. Player "+playerWon+" won, in turn "+turn);
        } else {
            turn++;
            loop();
        }
    }

    void start() {
        startQuestions();
        createTilesArray();
        arrayPlayers = new Player[playersNum];
        startPositions();
        playerMoves = new char[playersNum];
        outputCurrentPos();
        System.out.println("\nGame Start\n");
        loop();
    }

    void startQuestions() {
        while (playersNum > 8 || playersNum < 2) {
            System.out.println("How many players will be playing? (2 to 8 players)\n");
            playersNum = sc.nextInt();
            System.out.println();
        }
        if (playersNum >= 2 && playersNum <= 4) {
            while (mapSide > 50 || mapSide < 5) {
                System.out.println("What size will the edge of the map be? (5 to 50 tiles)\n");
                mapSide = sc.nextInt();
                System.out.println();
            }
        } else {
            while (mapSide > 50 || mapSide < 8) {
                System.out.println("What size will the edge of the map be? (8 to 50 tiles)\n");
                mapSide = sc.nextInt();
                System.out.println();
            }
        }
    }

    void createTilesArray() {
        MapGenerator mg = new MapGenerator(mapSide);
        arrayTiles = new Tile[playersNum][mapSide];
        for (int i = 0; i < playersNum; i++) {
            arrayTiles[i] = mg.returnArray();
        }
    }

    void startPositions() {
        boolean done;
        int[][] playersStartPosition = new int[playersNum][2];
        int counter = 0;
        while (counter < playersNum) {
            done = false;
            while (!done) {

                double a = ((mapSide * mapSide * rand.nextDouble()));
                int f = (int) Math.round(a);
                if (f < 0) {
                    f = 0;
                } else if (f > 24) {
                    f = 24;
                }
                if (arrayTiles[0][f].tileType == 0) {
                    playersStartPosition[counter][0] = arrayTiles[0][f].tileX;
                    playersStartPosition[counter][1] = arrayTiles[0][f].tileY;
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

    void getMoves() {
        for (int i = 0; i < playersNum; i++) {
            System.out.println("Enter direction of movement, player " + i + ", (up, down, left or right) :");
            playerMoves[i] = sc.next().charAt(0);
        }
    }

    void movePlayers() {
        for (int i = 0; i < playersNum; i++) {
            if (playerMoves[i] == 'u') {
                if (arrayPlayers[i].currentposY != 0) {
                    arrayPlayers[i].moveUp();
                }
            } else if (playerMoves[i] == 'd') {
                if (arrayPlayers[i].currentposY != mapSide - 1) {
                    arrayPlayers[i].moveDown();
                }
            } else if (playerMoves[i] == 'l') {
                if (arrayPlayers[i].currentposX != 0) {
                    arrayPlayers[i].moveLeft();
                }
            } else if (playerMoves[i] == 'r') {
                if (arrayPlayers[i].currentposX != mapSide - 1) {
                    arrayPlayers[i].moveRight();
                }
            } else {
                System.out.println("Player " + i + " entered an invalid move.");
            }
        }
    }

    void checkWater() {
        for (int i = 0; i < arrayTiles[0].length; i++) {
            for (int j = 0; j < playersNum; j++) {
                if (arrayTiles[0][i].tileType == 1) {
                    if ((arrayPlayers[j].currentposX == arrayTiles[0][i].tileX) && (arrayPlayers[j].currentposY == arrayTiles[0][i].tileY)) {
                        System.out.println("water");
                        arrayPlayers[j].currentposX = arrayPlayers[j].startposX;
                        arrayPlayers[j].currentposY = arrayPlayers[j].startposY;

                    }
                }
            }
        }
    }

    int checkWin() {
        int winX = 0, winY = 0;
        for (int i = 0; i < arrayTiles[0].length; i++) {
            if (arrayTiles[0][i].tileType == 2) {
                winX = arrayTiles[0][i].tileX;
                winY = arrayTiles[0][i].tileY;
            }
        }

        for (int i = 0; i < playersNum; i++) {
            if (arrayPlayers[i].currentposX == winX && arrayPlayers[i].currentposY == winY) {
                return i;
            }
        }
        return -1;
    }

    void addUncovered() {
        if (first) {
            first = false;
            for (int i = 0; i < playersNum; i++) {
                for (int j = 0; j < arrayTiles[i].length; j++) {
                    if ((arrayTiles[i][j].tileX == arrayPlayers[i].startposX) && (arrayTiles[i][j].tileY == arrayPlayers[i].startposY)) {
                        arrayTiles[i][j].tileUncovered = true;
                    }
                }
            }

            for (int i = 0; i < playersNum; i++) {
                for (int j = 0; j < arrayTiles[i].length; j++) {
                    if ((arrayTiles[i][j].tileX == arrayPlayers[i].currentposX) && (arrayTiles[i][j].tileY == arrayPlayers[i].currentposY)) {
                        arrayTiles[i][j].tileUncovered = true;
                    }
                }
            }
        } else {
            for (int i = 0; i < playersNum; i++) {
                for (int j = 0; j < arrayTiles[i].length; j++) {
                    if ((arrayTiles[i][j].tileX == arrayPlayers[i].currentposX) && (arrayTiles[i][j].tileY == arrayPlayers[i].currentposY)) {
                        arrayTiles[i][j].tileUncovered = true;
                    }
                }
            }
        }
    }

    void outputCurrentPos() {
        System.out.println();
        for (int i = 0; i < playersNum; i++) {
            System.out.println("Player " + i + " is at coords: " + arrayPlayers[i].currentposX + ", " + arrayPlayers[i].currentposY);
        }
        System.out.println();
    }
}
