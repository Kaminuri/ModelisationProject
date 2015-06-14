package test;

import static org.junit.Assert.*;
import model.geometric.Face;
import model.geometric.Point;
import model.geometric.Segment;

import org.junit.Test;

public class TestFace {
		
		@Test
		public void testFace(){
			Segment s1 = new Segment(new Point(4,5,7),new Point(7,5,4));
			Segment s2 = new Segment(new Point(6,8,9),new Point(10,50,40));
			Face f = new Face(s1, s2);
			assertNotNull(f);	
		}

		@Test
		public void testGetPoints() {
			Point[]t = new Point[3];
			Point p1 = new Point(4,5,7);
			Point p2 = new Point(7,5,4);
			Segment s1 = new Segment(p1, p2);
			Segment s2 = new Segment(new Point(6,8,9),new Point(10,50,40));
			Face f = new Face(s1,s2);
			t[0] = s1.getP1();
			t[1] = s1.getP2();
			t[2] = s2.getP1();
			Point [] t2 = f.getPoints();
			assertEquals(t[0], t2[0]);
			assertEquals(t[1], t2[1]);
			assertEquals(t[2], t2[2]);
		}
		
		@Test
		public void testBarycentre(){
			Point p1 = new Point(4,5,7);
			Point p2 = new Point(7,5,4);
			Segment s1 = new Segment(p1, p2);
			Segment s2 = new Segment(new Point(6,8,9),new Point(10,50,40));
			Face f = new Face(s1,s2);
			Point res = f.barycentre();
			assertNotNull(res);
			double t = (4.0+7.0+6.0)/3.0;
			assertEquals(t, res.getX(), 0.0001);
			t = (5.0 + 5.0 + 8.0)/3.0;
			assertEquals(t, res.getY(), 0.0001);
			t = (7.0 + 4.0 + 9.0)/3.0;
			assertEquals(t, res.getZ(), 0.0001);
		}

	}

