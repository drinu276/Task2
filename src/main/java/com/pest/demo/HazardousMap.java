package task2;

import java.util.*;

public class HazardousMap extends MapGenerator {

	private HazardousMap (int a){
		super(a);
	}

	public static MapGenerator getMapInstance(int a){
		System.out.println("ok?");
		if(MapGenerator.map == null)
			map = new HazardousMap(a);
		System.out.println("GET MAP INSTANCE OK");
		return MapGenerator.map;
	}

	public int typeSet(int currTile){
		Random rand = new Random();
		int waterTilesMax = (int) Math.round((35*mapSize)/100);
		int waterTilesMin = (int) Math.round((25*mapSize)/100);
		int amount = rand.nextInt(waterTilesMax - waterTilesMin)+ waterTilesMin; // amount of tiles which will be water
		int a = (int) (Math.round(10 * rand.nextDouble()));
		int waterTilesCurr =0;

		//TODO - TAKE OFF THESE PRINTS
		System.out.println("water MIN: " + waterTilesMin); //!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("water MAX: " + waterTilesMax);  //!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("Number of Water Tiles : " + amount); // ??????????

		if(currTile ==23 && !winTileCreated){
			return 2;
		}
		else {
			if(a>8 && !winTileCreated){

				winTileCreated = true;
				return 2; 
			}
			else if ( a<5 && (waterTilesCurr < amount)){
				waterTilesCurr ++;
				return 1;
			}
			else {
				return 0;
			}
		}
	}

	@Override
	public void generateLoop() {
		System.out.println("generate LOOP");
		arrayTiles = new Tile[dimension][dimension];
		int counter=0;

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				arrayTiles[i][j] = new Tile(j , i , typeSet(counter));// ???TODO
				counter++;
				System.out.println("TYPE" + arrayTiles[i][j].getType());

			}
		}
		System.out.println("array of tiles created... ");

	}

}
