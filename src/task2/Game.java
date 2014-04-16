package task2;

import java.util.Scanner;
import java.util.Random;

public class Game {

    Scanner sc = new Scanner(System.in);
    boolean won = false;
    int turn;
    int playersNum;
    int mapSide;
    char direction;
    char[] playerMoves;
    Tile[] arrayTiles;
    Player[] arrayPlayers;
    int[][] playersStartPosition;
    Random rand = new Random();

    public Game() {
        turn = 0;
        playersNum = 0;
        mapSide = 0;
    }

    void loop() {
        getMoves();
        movePlayers();
        //Move players to tiles
        //Check for win
        //Check for water
        won = true; //TODO remove
        if (won) {
            System.out.println("Game over.");
        } else {
        turn++;
        loop();
        }
    }

    void getMoves() {
        for (int i = 0; i < playersNum; i++) {
            System.out.println("Enter direction of movement, player "+i+", (up, down, left or right) :");
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
                if (arrayPlayers[i].currentposY != mapSide-1) {
                    arrayPlayers[i].moveDown();
                }
            } else if (playerMoves[i] == 'l') {
                if (arrayPlayers[i].currentposX != 0) {
                    arrayPlayers[i].moveLeft();
                }
            } else if (playerMoves[i] == 'r') {
                if (arrayPlayers[i].currentposX != mapSide-1) {
                    arrayPlayers[i].moveRight();
                }
            } else {
                System.out.println("Player "+i+" entered an invalid move.");
            }
        }
    }

    void start() {
        startQuestions();
        MapGenerator mg = new MapGenerator(mapSide);
        arrayTiles = mg.returnArray();
        startPositions();
        outputStartPos();
        copyStartPos();
        playerMoves = new char[playersNum];
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

    void startPositions() {
        boolean done;
        playersStartPosition = new int[playersNum][2];
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
                if (arrayTiles[f].tileType == 0) {
                    playersStartPosition[counter][0] = arrayTiles[f].tileX;
                    playersStartPosition[counter][1] = arrayTiles[f].tileY;
                    done = true;
                }
            }
            counter++;
        }
    }

    void outputStartPos() {
        for (int i = 0; i < playersNum; i++) {
            System.out.println(playersStartPosition[i][0] + ", " + playersStartPosition[i][1]);
        }
    }

    void copyStartPos() {
        arrayPlayers = new Player[playersNum];
        for (int i = 0; i < playersNum; i++) {
            Player temp = new Player(playersStartPosition[i][0], playersStartPosition[i][1]);
            arrayPlayers[i] = temp;
        }
    }
}
