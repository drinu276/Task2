package com.pest.demo;

import java.util.Scanner;
import java.util.Random;
import java.io.*; // imported to use PrintWriter 

public class Game {

    Scanner sc = new Scanner(System.in); //Used for player input
    boolean won = false; //Controls ending of game
    boolean first = true; //Used to control what is run first time only
    int turn; //Holds turn number
    int playersNum; //Holds number of players playing
    int mapSide; //Holds length of side of map
    int[] winners;
    char[] playerMoves; //Array that stores player
    Tile[][] arrayTiles; //Multidimensional array that stores arrays of type Tile, one for each player
    Player[] arrayPlayers; //Array of type player that stores current and starting position of each player
    Random rand = new Random();

    public Game() {
        turn = 1;
        playersNum = 0;
        mapSide = 0;
    }

    int loop() {
        getMoves();
        movePlayers();
        checkWater();
        addUncovered();
        winners = checkWin();
        for (int i = 0; i < playersNum; i++) {
            if (winners[i] == 1) {
                won = true;
                System.out.println("Player " + i + " won");
            }
        }

        for (int counter = 0; counter < playersNum; counter++) {
            generateHtml(arrayPlayers[counter], arrayTiles, counter);
        }

        outputCurrentPos();
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

    int start() {
        startQuestions();
        createTilesArray();
        arrayPlayers = new Player[playersNum];
        startPositions();
        addUncovered();
        playerMoves = new char[playersNum];
        outputCurrentPos();
        for (int counter = 0; counter < playersNum; counter++) {
            generateHtml(arrayPlayers[counter], arrayTiles, counter);
        }
        System.out.println("\nGame Start\n");
        loop();
        return 0;
    }

    int startQuestions() {
        while (checkNumberOfPlayers(playersNum) == false) {
            System.out.println("How many players will be playing? (2 to 8 players)\n");
            playersNum = sc.nextInt();
            System.out.println();
        }

        while (checkMapSize(playersNum, mapSide) == false) {
            mapSide = sc.nextInt();
            System.out.println();
        }
        return 0;
    }

    boolean checkNumberOfPlayers(int playNum) {
        if (playNum > 8 || playNum < 2) {
            return false;
        } else {
            playersNum = playNum;
            return true;
        }
    }

    boolean checkMapSize(int playersNum, int mapSide) {
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

    void createTilesArray() {
        MapGenerator mg = new MapGenerator(mapSide);
        mg.genLoop();
        Tile[] testTile = mg.returnArray();
        arrayTiles = new Tile[playersNum][mapSide * mapSide];

        for (int i = 0; i < playersNum; i++) {
            for (int j = 0; j < testTile.length; j++) {
                arrayTiles[i][j] = new Tile(0, 0, 0);
                arrayTiles[i][j].tileX = testTile[j].tileX;
                arrayTiles[i][j].tileY = testTile[j].tileY;
                arrayTiles[i][j].tileType = testTile[j].tileType;
            }
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
            System.out.println("Enter direction of movement, player " + i + ", ((U)p, (D)own, (L)eft or (R)ight) :");
            playerMoves[i] = sc.next().charAt(0);
        }
    }

    void movePlayers() {
        for (int i = 0; i < playersNum; i++) {
            if (playerMoves[i] == 'u' || playerMoves[i] == 'U') {
                if (arrayPlayers[i].currentposY != 0) {
                    arrayPlayers[i].moveUp();
                }
            } else if (playerMoves[i] == 'd' || playerMoves[i] == 'D') {
                if (arrayPlayers[i].currentposY != mapSide - 1) {
                    arrayPlayers[i].moveDown();
                }
            } else if (playerMoves[i] == 'l' || playerMoves[i] == 'L') {
                if (arrayPlayers[i].currentposX != 0) {
                    arrayPlayers[i].moveLeft();
                }
            } else if (playerMoves[i] == 'r' || playerMoves[i] == 'R') {
                if (arrayPlayers[i].currentposX != mapSide - 1) {
                    arrayPlayers[i].moveRight();
                }
            } else {
                System.out.println("Player " + i + " entered an invalid move.");
            }
        }
    }

    int checkWater() {
        for (int i = 0; i < arrayTiles[0].length; i++) {
            for (int j = 0; j < playersNum; j++) {
                if (arrayTiles[0][i].tileType == 1) {
                    if ((arrayPlayers[j].currentposX == arrayTiles[0][i].tileX) && (arrayPlayers[j].currentposY == arrayTiles[0][i].tileY)) {
                        System.out.println("Player " + j + " you have fallen in the water");
                        arrayTiles[j][i].tileUncovered = true; // uncover the water tile
                        arrayPlayers[j].currentposX = arrayPlayers[j].startposX;
                        arrayPlayers[j].currentposY = arrayPlayers[j].startposY;

                    }
                }
            }
        }
        return 0;
    }

    int[] checkWin() {
        int winX = 0, winY = 0;
        int[] winPlayers = new int[playersNum];

        for (int i = 0; i < arrayTiles[0].length; i++) {
            if (arrayTiles[0][i].tileType == 2) {
                winX = arrayTiles[0][i].tileX;
                winY = arrayTiles[0][i].tileY;
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

    void addUncovered() {
        if (first) {
            first = false;
            for (int i = 0; i < playersNum; i++) {
                for (int j = 0; j < arrayTiles[i].length; j++) {
                    if ((arrayTiles[i][j].tileX == arrayPlayers[i].startposX) && (arrayTiles[i][j].tileY == arrayPlayers[i].startposY)) {
                        arrayTiles[i][j].uncoverTile();
                    }
                }
            }

            for (int i = 0; i < playersNum; i++) {
                for (int j = 0; j < arrayTiles[i].length; j++) {
                    if ((arrayTiles[i][j].tileX == arrayPlayers[i].currentposX) && (arrayTiles[i][j].tileY == arrayPlayers[i].currentposY)) {
                        arrayTiles[i][j].uncoverTile();
                    }
                }
            }
        } else {
            for (int i = 0; i < playersNum; i++) {
                for (int j = 0; j < arrayTiles[i].length; j++) {
                    if ((arrayTiles[i][j].tileX == arrayPlayers[i].currentposX) && (arrayTiles[i][j].tileY == arrayPlayers[i].currentposY)) {
                        arrayTiles[i][j].uncoverTile();
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

    void generateHtml(Player player, Tile[][] arrayTile, int playerNumber) {

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

        for (int j = 0; j < mapSide; j++) {
            fileCreate.println("<tr>");
            for (int i = 0; i < mapSide; i++) {
                if ((arrayTile[playerNumber][mapSide * j + i].tileUncovered == false)) {
                    fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/hiddenTile.jpg \">"); //if player position agrees with current axis then player position image
                } else if (arrayTile[playerNumber][mapSide * j + i].tileUncovered == true) {
                    if (((player.getX() == (i)) && (player.getY() == (j))) && (arrayTile[playerNumber][mapSide * j + i].tileType == 2) == true) {
                        fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/winTile.jpg \">"); //win
                    } else if ((player.getX() == (i)) && (player.getY() == (j))) {
                        fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/pirateTile.jpg \">"); //if player position agrees with current axis then player position image
                    } else if (arrayTile[playerNumber][mapSide * j + i].tileType == 0) {
                        fileCreate.println("<td width=\"12\" align = \"center\" background =  \"images/grassTile.jpg \">"); //grass

                    } else if (arrayTile[playerNumber][mapSide * j + i].tileType == 1) {
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
