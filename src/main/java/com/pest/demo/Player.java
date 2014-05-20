package task2;

public class Player {

    private int startposX;
    private int startposY;
    int currentposX;
    int currentposY;

    public Player(int a, int b) {
        startposX = a;
        startposY = b;
    }
    
    public Player() {
    }

    void moveUp() {
        currentposY -= 1;
    }

    void moveDown() {
        currentposY += 1;
    }

    void moveLeft() {
        currentposX -= 1;
    }

    void moveRight() {
        currentposX += 1;
    }
    
    void setX(int a) {
      //  this.startposX = a;
        this.currentposX = startposX;
    }
    
    void setY(int b) {
      //  this.startposX = a;
        this.currentposY = startposY;
    }
    
    int getX(){
    	return currentposX;
    }
    
    int getY(){
    	return currentposY;
    }
    
    void setStartPosition(int a, int b){
    	this.startposX = a;
        this.startposX = a;
    	setX(a);
    	setY(b);
    }
}
