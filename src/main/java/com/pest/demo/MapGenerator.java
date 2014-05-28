package task2;

import java.util.Random;

public abstract class MapGenerator {

    public static MapGenerator map = null;
    static int dimension;
    static int mapSize;
    public boolean winTileCreated;
    Tile[] arrayTiles;
    Random rand = new Random();
   // int testinteger;
    int waterTilesMax, waterTilesCurr;

    public MapGenerator(int size) { //inputs: type, size
        dimension = size;
        mapSize = dimension * dimension;
        generateLoop();
    }
    
    public Tile[] returnArray() {
        return arrayTiles;
    }

    public abstract void generateLoop();

}
