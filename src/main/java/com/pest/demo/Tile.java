package task2;

public class Tile {

    int tileX;
    int tileY;
    int tileType;
    boolean tileUncovered = false;

    //TODO empty constructor??? !! 
    public Tile(){
    	
    }
    
    public Tile(int y, int x, int type) {
        tileX = x;
        tileY = y;
        tileType = type;
    }
    
    public int getType(){
    	return tileType;
    }
    
    void uncoverTile() {
        this.tileUncovered = true;
    }
}
