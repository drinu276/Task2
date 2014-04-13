package task2;

import java.util.Scanner;

public class Game {

    Scanner sc = new Scanner(System.in);
    int turn;
    int playersNum = 0;
    int mapSide = 0;

    public Game() {
        turn = 0;
    }

    void loop() {
        //Get each player's intended direction (logic to enforce boundaries)
        //Move players to tiles
        //Check for win
        //Check for water
    }

    void start() {
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
}
