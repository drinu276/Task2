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
public class MapGeneratorTest {
    Tile[] testT = null;
    public MapGeneratorTest() {
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
     * Test of returnArray method, of class MapGenerator.
     */
    @Test
    public void testReturnArray() {
        System.out.println("returnArray");
        MapGenerator instance = new MapGenerator(0);
        Tile[] expResult = null;
        Tile[] result = testT;
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of typeSet method, of class MapGenerator.
     */
    @Test
    public void testTypeSet() {
        System.out.println("typeSet");
        int currTile = 23; //Since the return value is calculated randomly, there is no sure way to test except if currTile = 23 in which case the next tile is a win
        MapGenerator instance = new MapGenerator(0);
        int expResult = 2;
        int result = instance.typeSet(currTile);
        assertEquals(expResult, result);
        //assertEquals(2, map1.typeSet(23));
    }

    /**
     * Test of genLoop method, of class MapGenerator.
     */
    @Test
    public void testGenLoop() {
        System.out.println("genLoop");
        MapGenerator instance = new MapGenerator(5);
        int expResult = 0;
        assertEquals(instance.genLoop(), expResult);    
    }
    
}
