package task2;

public class Player {

    int startposX;
    int startposY;
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

    int[] returnPos() {
        int[] arrayX = {currentposX, currentposY};
        return arrayX;
    }
    
    void setX(int a) {
        this.startposX = a;
        this.currentposX = startposX;
    }
    
    void setY(int b) {
        this.startposY = b;
        this.currentposY = startposY;
    }
}
