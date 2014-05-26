package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import task2.Player;

public class PlayerTest {

	
	Player p, p1, p2, p3,p4,p5, p6;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		p = new Player (2,3);
		p1 = new Player ();
		p2 = new Player ();
		p3 = new Player ();
		p4 = new Player();
		p5 = new Player();
		p6 = new Player();
		
		p1.setX(4);
		p1.setY(4);
		
		p2.setX(2);
		p2.setY(3);
		
		p3.setX(4);
		p3.setY(4);
		
		p4.setX(1);
		p4.setY(3);
		
		p5.setX(1);
		p5.setY(4);
		
		p6.setX(2);
		p6.setY(4);
		
		p1.setTeam(3);
		
		 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetX(){
		assertEquals(1, p4.getX());
		assertEquals(2, p2.getX());
	}
	
	@Test
	public void testgetY(){
		assertEquals(3, p4.getY());
		assertNotEquals(2, p3.getY());
	}
	
	@Test
	public void testSetY(){
		p5.setY(3);
		assertEquals(3, p5.getY());
	}
	
	@Test
	public void testSetX(){
		p5.setX(1);
		assertEquals(1, p5.getX());
	}
	
	@Test
	public void testgetTeam(){
		assertEquals(3, p1.getTeam());
	}
	
	@Test
	public void testSetTeam(){
		p.setTeam(2);
		assertEquals(2, p.getTeam());
	}
	
	@Test
	public void testMoveUp(){
		p1.moveUp();
		assertEquals(3, p1.getX());
		assertNotEquals(5, p1.getX());
	}
	
	@Test
	public void testMoveDown(){
		p3.moveDown();
		assertEquals(5, p3.getX());
	}
	
	@Test
	public void testMoveRight(){
		p5.moveRight();
		assertEquals(5, p5.getY());
	}
	
	@Test
	public void testMoveLeft(){
		p6.moveLeft();
		assertEquals(3, p6.getY());
	}

}
