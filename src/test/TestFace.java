package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.geometric.Face;
import model.geometric.Point;
import model.geometric.Segment;

public class TestFace {
	
	@Test
	public void testFace(){
		Segment s1 = new Segment(new Point(4,5,7),new Point(7,5,4));
		Segment s2 = new Segment(new Point(6,8,9),new Point(10,50,40));
		Face f = new Face(s1, s2);
		assertNotNull(f);	
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPoints() {
		//Pas fini
		Point[]t = new Point[3];
		Point p1 = new Point(4,5,7);
		Point p2 = new Point(7, 5, 4);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(new Point(6,8,9),new Point(10,50,40));
		Face f = new Face(s1,s2);
		t[0] = s1.getP1();
		t[1] = s1.getP2();
		t[2] = s2.getP1();
		Point [] t2 = f.getPoints();
		assertTrue(t.equals(t2));
	}

}
