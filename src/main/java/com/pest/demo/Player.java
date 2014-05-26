package task2;

public class Player {

    public int startposX;
    public int startposY;
    public int currentposX;
    public int currentposY;
    public int team;

    public Player(int a, int b) {
        startposX = b;
        startposY = a;
    }
    
    public Player() {
    }

    public void moveUp() {
    	currentposX -=1;	
    }

    public void moveDown() {
    	currentposX +=1;	
    }

    public void moveLeft() {
    	currentposY -=1;	
    }

    public void moveRight() {
    	currentposY +=1;
    }
    
    public void setX(int a) {
        this.startposX = a;
        this.currentposX = startposX;
    }
    
    public void setY(int b) {
        this.startposY = b;
        this.currentposY = startposY;
    }
    
    public int getX(){
    	return currentposX;
    }
    
    public int getY(){
    	return currentposY;
    }
    
    public void setTeam(int c) {
        this.team = c;
    }
    public int getTeam() {
        return team;
    }    
}
