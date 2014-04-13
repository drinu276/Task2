package task2;

public class Player {

    private final int startposX;
    private final int startposY;
    private int currentposX;
    private int currentposY;

    public Player(int a, int b) {
        startposX = a;
        startposY = b;
        currentposX = startposX;
        currentposY = startposY;
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
}
