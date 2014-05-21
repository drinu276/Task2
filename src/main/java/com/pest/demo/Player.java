package task2;

public class Player {

    private int startposX;
    private int startposY;
    int currentposX;
    int currentposY;

    public Player(){
    	
    }
    
    public Player(int a, int b) {
        startposX = a;
        startposY = b;
    }
    
    private Positions p;
    private Positions starts;
    Game g;
    
    void movePlayers(char c, int playerNum) {
			if (c == 'u' || c == 'U') {
				if (currentposY != 0) {
					p.moveUp();
				}
			} else if (c == 'd' ||c == 'D') {
				if (currentposY != g.mapSide - 1) {
					p.moveDown();
				}
			} else if (c == 'l' || c == 'L') {
				if (currentposX != 0) {
					p.moveLeft();
				}
			} else if (c == 'r' || c == 'R') {
				if (currentposX != g.mapSide - 1) {
					p.moveRight();
				}
			} else {
				System.out.println("Player " + playerNum + " entered an invalid move.");
			}
	}
   
    public Positions getPosition(){
    	return p;
    }
    void startPositions(int x , int y){
    	p = new Positions(x, y);
    	System.out.println("Starting Positions set");
    	System.out.println("x: " + p.getX()+ ", y : " + p.getY());
    
    }
    
    void positions(Positions position){
    	p = new Positions(p.getX(), p.getY());
    	System.out.println("Starting Positions set");

    }
}
