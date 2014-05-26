package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task2.*;

public class SafeMapTest {

	Game gameS;
	MapGenerator map = null;
	Tile[] safe;
	Player[] player;
	int winX, winY =0;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMapInstance(){
		MapGenerator.map = null;
		SafeMap.getMapInstance(30);
		
		assertTrue(MapGenerator.map instanceof SafeMap);
		
		MapGenerator.map = SafeMap.getMapInstance(10);
		SafeMap.getMapInstance(30);
		
		assertTrue(MapGenerator.map instanceof SafeMap);

	}
	
	@Test
	public void testTypeSet(){
		MapGenerator.map= SafeMap.getMapInstance(25);
		MapGenerator.map.winTileCreated =true;
		MapGeneratorCreator m = new MapGeneratorCreator();
		MapGenerator.map.generateLoop();
		
		assertEquals(null, m.typeSet(5));
	}
}
