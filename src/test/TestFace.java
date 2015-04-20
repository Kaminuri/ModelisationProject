package test;

import org.junit.Test;

import model.geometric.Face;
import model.geometric.Point;
import model.geometric.Segment;



public class TestFace {
	
	@Test
	public void testGetPoints() {
		//Pas fini
		Point[]t = new Point[3];
		Segment s1 = new Segment(new Point(4,5,7),new Point(7,5,4));
		Segment s2 = new Segment(new Point(6,8,9),new Point(10,50,40));
		Segment s3 = new Segment(new Point(1,2,0),new Point(4,8,1));
		Face f = new Face(s1,s2,s3);
		t[1] = s1.getP1();
		t[2] = s1.getP2();
	}

}
