package task2;

public class SafeMap extends MapGenerator {

    private SafeMap(int size) {
        super(size);
    }

    public static MapGenerator getMapInstance(int size) {
        
        if (MapGenerator.map == null) {
            map = new SafeMap(size);
        }

        return MapGenerator.map;
    }

    public int typeSet(int currTile) {
        waterTilesMax = Math.round((mapSize)/10);
        int a = (int)(Math.random()*10);
        if (currTile > mapSize-3 && !winTileCreated) {
            winTileCreated = true;
            return 2;
        } else {
            if (a > 8 && !winTileCreated) {
                winTileCreated = true;
                return 2;
            } else if (a < 5 && (waterTilesCurr < waterTilesMax)) {
                waterTilesCurr++;
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    @Override
    public void generateLoop() {
        arrayTiles = new Tile[mapSize];
        int counter = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                arrayTiles[counter] = new Tile(j, i, typeSet(counter));
                counter++;
            }
        }
    }
}
