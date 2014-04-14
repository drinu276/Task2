package task2;

import java.util.Scanner;
import java.util.Random;

public class Game {

    Scanner sc = new Scanner(System.in);
    int turn;
    int playersNum;
    int mapSide;
    Tile[] arrayTiles;
    int[][] playersStartPosition;
    Random rand = new Random();

    public Game() {
        turn = 0;
        playersNum = 0;
        mapSide = 0;
    }

    void loop() {
        //Get each player's intended direction (logic to enforce boundaries)
        //Move players to tiles
        //Check for win
        //Check for water
    }

    void start() {
        startQuestions();
        MapGenerator mg = new MapGenerator(mapSide);
        arrayTiles = mg.returnArray();
        startPositions();
        //outputStartPos();
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
        for(int i = 0; i<playersNum; i++){
            System.out.println(playersStartPosition[i][0]+", "+playersStartPosition[i][1]);
        }
    }
}
