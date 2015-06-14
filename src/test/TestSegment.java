package test;

import static org.junit.Assert.*;
import model.geometric.Point;
import model.geometric.Segment;

import org.junit.Test;

public class TestSegment {

	@Test
	public void testSegment() {
		Point p1 = new Point(4.0, 3.0, 2.0);
		Point p2 = new Point (8.0, 7.0, 6.0);
		Segment s1 = new Segment(p1, p2);
		assertNotNull(s1);
	}
	
	@Test
	public void testGetP1(){
		Point p1 = new Point(4.0, 3.0, 2.0);
		Point p2 = new Point (8.0, 7.0, 6.0);
		Segment s1 = new Segment(p1, p2);
		assertEquals(p1, s1.getP1());
	}
	
	@Test
	public void testGetP2(){
		Point p1 = new Point(4.0, 3.0, 2.0);
		Point p2 = new Point (8.0, 7.0, 6.0);
		Segment s1 = new Segment(p1, p2);
		assertEquals(p2, s1.getP2());
	}

}
