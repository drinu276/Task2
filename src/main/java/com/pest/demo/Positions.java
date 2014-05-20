package task2;

public class Positions {

	private int currentposX;
	private int currentposY;
	
	public Positions ( int x , int y){
		currentposX = x; 
		currentposY = y;
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
        this.currentposX = a;
    }
    
    void setY(int b) {
      //  this.startposX = a;
        this.currentposY = b;
    }
    
    int getX(){
    	return currentposX;
    }
    
    int getY(){
    	return currentposY;
    }
	
	
}
