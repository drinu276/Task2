package test;
import task2.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TileTest {
	Tile t,t2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		t = new Tile ( 2, 3 ,1);
			
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		t2 = new Tile(4,3,2);
	
		assertSame(t2.tileX, t.tileX);
		assertNotSame(t2.tileY, t.tileY);
		assertNotSame(t2.tileType, t.tileType);
	}

}
