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
        MapGenerator instance = null;
        Tile[] expResult = null;
        Tile[] result = instance.returnArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of typeSet method, of class MapGenerator.
     */
    @Test
    public void testTypeSet() {
        System.out.println("typeSet");
        int currTile = 0;
        MapGenerator instance = null;
        int expResult = 0;
        int result = instance.typeSet(currTile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of genLoop method, of class MapGenerator.
     */
    @Test
    public void testGenLoop() {
        System.out.println("genLoop");
        MapGenerator instance = null;
        int expResult = 0;
        int result = instance.genLoop();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
