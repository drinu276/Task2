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

/**
 *
 * @author Andrea
 */
public class TileTest {

    Tile t1, t2, t3;

    public TileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        t1 = new Tile(2, 2, 0);
        t2 = new Tile(4, 5, 0);
        t3 = new Tile(6, 7, 2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of uncoverTile method, of class Tile.
     */
    @Test
    public void testUncoverTile() {
        /*System.out.println("uncoverTile");
         Tile instance = null;
         instance.uncoverTile(); */
        t1.uncoverTile();
        assertEquals(true, t1.tileUncovered);

        t2.uncoverTile();
        assertEquals(true, t2.tileUncovered);

        assertEquals(false, t3.tileUncovered);
    }

}
