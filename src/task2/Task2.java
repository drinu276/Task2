package task2;

public class Task2 {

    public static void main(String[] args) {
    Player bob = new Player(5,6);
    bob.moveUp();
    bob.moveDown();
    System.out.println("bob.returnPos() = "+bob.returnPos()[0]+","+bob.returnPos()[1]);
    MapGenerator mapgen = new MapGenerator(5);
    mapgen.start();
    }
    
}
