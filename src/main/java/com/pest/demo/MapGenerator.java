package task2;

import java.util.Random;

public abstract class MapGenerator {

    protected static MapGenerator map = null;
    static int dimension;
    static int mapSize;
    boolean winTileCreated;
    Tile[] arrayTiles;
    Random rand = new Random();
    int testinteger;
    int waterTilesMax, waterTilesCurr;

    public MapGenerator(int size) { //inputs: type, size
        dimension = size;
        mapSize = dimension * dimension;
        generateLoop();
    }

 /*   void displayLoop() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(arrayTiles[dimension*i+j].tileType+"\t");
            }
            System.out.println();
        }
        
    }*/
    
    public Tile[] returnArray() {
        return arrayTiles;
    }

    public abstract void generateLoop();
}
