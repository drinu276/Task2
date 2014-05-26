package test;

import task2.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import java.io.*;

public class MapCreatorTest {
	Game game;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int numberOfPlayers = 3;
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestcreateMap() {
		MapGeneratorCreator map = new MapGeneratorCreator();
		MapGenerator map1 = map.createMap(25);

		MapGeneratorCreator map2 = new MapGeneratorCreator();
		MapGenerator mapS = map2.createMap(25, 1);
		
		MapGeneratorCreator map3 = new MapGeneratorCreator();
		MapGenerator mapa = map3.createMap(25, 2);
		
		assertThat(mapS, is(not(map1)));	
	}
	
	@Test
	public void TestTypeSet(){
		MapGeneratorCreator map = new MapGeneratorCreator();
		MapGeneratorCreator map1 = map.typeSet(1);
		MapGeneratorCreator map2 = map.typeSet(2);
		MapGeneratorCreator map3 = map.typeSet(3);
		
		assertTrue(map1 instanceof SafeMapCreator);
		assertTrue(map2 instanceof HazardousMapCreator);
		assertNull(map3);
		
	}

}
