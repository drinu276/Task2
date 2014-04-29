package task2;

import java.util.Random;

public class MapGenerator {

    private final int dimension;
    private final int mapSize;
    private final int waterTilesMax;
    private int waterTilesCurr;
    private boolean winTileCreated;
    Tile[] arrayTiles;
    Random rand = new Random();
    private int counter;

    public MapGenerator(int a) {
        winTileCreated = false;
        counter = 0;
        dimension = a;
        waterTilesMax = a - 1;
        mapSize = dimension * dimension;
        arrayTiles = new Tile[mapSize];
    }

    Tile[] returnArray() {
        return arrayTiles;
    }

    int typeSet(int currTile) { //Type 0 = grass, Type 1 = water, Type 2 = win
        int type;
        int a = (int) (Math.round(10 * rand.nextDouble()));

        if (currTile == 23 && !winTileCreated) {
            type = 2;
        } else {

            if (a > 8 && !winTileCreated) {
                type = 2;
                winTileCreated = true;
            } else if (a <= 2 && (waterTilesCurr < waterTilesMax)) {
                type = 1;
                waterTilesCurr++;
            } else {
                type = 0;
            }
        }
        return type;
    }

    int genLoop() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Tile tile1 = new Tile(i, j, typeSet(counter));
                arrayTiles[counter] = tile1;
                counter++;
            }
        }
        return 0;
    }
}
