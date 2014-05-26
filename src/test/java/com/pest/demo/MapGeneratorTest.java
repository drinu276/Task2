package test;
import task2.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapGeneratorTest {
	Tile[] t;
	
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
	public void test() {
		
		MapGeneratorCreator map = new MapGeneratorCreator();
		MapGenerator a = map.createMap(25,2);
		t = a.returnArray();
		
		assertArrayEquals(t, a.returnArray());
	}

}
