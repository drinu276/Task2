package task2;

import java.util.Random;

public abstract class MapGenerator {

	protected static MapGenerator map = null; 

	static int dimension;
	static int mapSize;
	boolean winTileCreated;
	protected static Tile[][] arrayTiles;
	int counter;

	public MapGenerator(int a) {
		winTileCreated = false;
//		counter = 0;
		dimension = a;
		mapSize = dimension * dimension;
//		arrayTiles = new Tile[mapSize];
		generateLoop();
		displayLoop();

	}
	
	public static void setSize(int size){
		mapSize = size;
	}
	
	public static int getSize (){
		return mapSize;
	}
	
	public static Tile getTile(int posX, int posY){
		return arrayTiles[posX][posY];
	}
	
	public static Tile[][] getArrayTiles(){
		return arrayTiles;
	}
	
	public void displayLoop() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(arrayTiles[i][j].getType()+"\t");
            }
            System.out.println();
        }
        
    }
		
	public abstract void generateLoop();
}
