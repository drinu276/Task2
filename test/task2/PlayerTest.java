/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package task2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrea
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of moveUp method, of class Player.
     */
    @Test
    public void testMoveUp() {
        System.out.println("moveUp");
        Player instance = new Player();
        instance.moveUp();        
    }

    /**
     * Test of moveDown method, of class Player.
     */
    @Test
    public void testMoveDown() {
        System.out.println("moveDown");
        Player instance = new Player();
        instance.moveDown();       
    }

    /**
     * Test of moveLeft method, of class Player.
     */
    @Test
    public void testMoveLeft() {
        System.out.println("moveLeft");
        Player instance = new Player();
        instance.moveLeft();        
    }

    /**
     * Test of moveRight method, of class Player.
     */
    @Test
    public void testMoveRight() {
        System.out.println("moveRight");
        Player instance = new Player();
        instance.moveRight();
    }

    /**
     * Test of setX method, of class Player.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int a = 0;
        Player instance = new Player();
        instance.setX(a);
    }

    /**
     * Test of setY method, of class Player.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int b = 0;
        Player instance = new Player();
        instance.setY(b);
    }

    /**
     * Test of getX method, of class Player.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Player instance = new Player();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Player.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Player instance = new Player();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
    }
    
}
