/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pest.demo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

/**
 *
 * @author Andrea
 */
public class GameTest {

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MapGenerator instance = new MapGenerator(5);
        Tile[] arrayTiles = instance.returnArray();
    }

    @After
    public void tearDown() {
    }
    /*
     /**
     * Test of loop method, of class Game.
     
     @Test
     public void testLoop() {
     System.out.println("loop");
     Game instance = new Game();
     int expResult = 0;
     int result = instance.loop();
     assertEquals(expResult, result);
     }

     /**
     * Test of start method, of class Game.
     
     @Test
     public void testStart() {
     System.out.println("start");
     Game instance = new Game();        
     int expResult = 0;
     int result = instance.start();
     assertEquals(expResult, result);
     }

     /**
     * Test of startQuestions method, of class Game.
     
     @Test
     public void testStartQuestions() {
     Scanner sc = new Scanner(System.in);
     System.out.println("startQuestions");
     Game instance = new Game();
     assertEquals(instance.startQuestions(),0);
     }
     */

    /**
     * Test of checkNumberOfPlayers method, of class Game.
     */
    @Test
    public void testCheckNumberOfPlayers() {
        System.out.println("checkNumberOfPlayers");
        int playNum = 0;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.checkNumberOfPlayers(playNum);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMapSize method, of class Game.
     */
    @Test
    public void testCheckMapSize() {
        System.out.println("checkMapSize");
        int playersNum = 0;
        int mapSide = 0;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.checkMapSize(playersNum, mapSide);
        assertEquals(expResult, result);
    }

    /**
     * Test of createTilesArray method, of class Game.
     */
    @Test
    public void testCreateTilesArray() {
        System.out.println("createTilesArray");
        Game instance = new Game();
        instance.createTilesArray();
    }

    /**
     * Test of startPositions method, of class Game.
     */
    @Test
    public void testStartPositions() {
        System.out.println("startPositions");
        Game instance = new Game();
        instance.startPositions();
    }

    /**
     * Test of getMoves method, of class Game.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Game instance = new Game();
        instance.getMoves();
    }

    /**
     * Test of movePlayers method, of class Game.
     */
    @Test
    public void testMovePlayers() {
        System.out.println("movePlayers");
        Game instance = new Game();
        instance.movePlayers();
    }

    /**
     * Test of checkWater method, of class Game.
     */
    /*
     @Test
     public void testCheckWater() {
     System.out.println("checkWater");
     Game instance = new Game();
     int expResult = 0;
     int result = instance.checkWater();
     assertEquals(expResult, result);  
     }
     */
    /**
     * Test of checkWin method, of class Game.
     */
    /*
     @Test
     public void testCheckWin() {
     System.out.println("checkWin");
     Game instance = new Game();
     int[] expResult = null;
     int[] result = instance.checkWin();
     assertArrayEquals(expResult, result);
     }
     */
    /**
     * Test of addUncovered method, of class Game.
     */
    @Test
    public void testAddUncovered() {
        System.out.println("addUncovered");
        Game instance = new Game();
        instance.addUncovered();
    }

    /**
     * Test of outputCurrentPos method, of class Game.
     */
    @Test
    public void testOutputCurrentPos() {
        System.out.println("outputCurrentPos");
        Game instance = new Game();
        instance.outputCurrentPos();
    }

    /**
     * Test of generateHtml method, of class Game.
     */
    @Test
    public void testGenerateHtml() {
        System.out.println("generateHtml");
        Player player = null;
        Tile[][] arrayTile = null;
        int playerNumber = 0;
        Game instance = new Game();
        instance.generateHtml(player, arrayTile, playerNumber);
    }

}
