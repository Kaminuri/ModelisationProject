package test;

import static org.junit.Assert.*;
import model.geometric.Point;

import org.junit.Test;

public class TestPoint {
	
	@Test
	public void testScalarProduct() {
		Point p = new Point(12,4,28);
		Point x = new Point(6,7,9);
		double b = p.scalarProduct(x);
		assertEquals(b,352,1);
	}
	
	@Test
	public void testMultiplyX() {
		Point p = new Point(12,4,28);
		int m = 4;
		p.multiply(m);
		assertEquals(p.getX(),48,1);
	}
	
	@Test
	public void testMultiplyY() {
		Point p = new Point(12,4,28);
		int m = 4;
		p.multiply(m);
		assertEquals(p.getY(),16,1);
	}
	
	@Test
	public void testMultiplyZ() {
		Point p = new Point(12,4,28);
		int m = 4;
		p.multiply(m);
		assertEquals(p.getZ(),112,1);
	}
	
	@Test
	public void testEqualsP() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,28);
		boolean b = p.equals(m);
		assertTrue(b);
	}
	
	@Test
	public void testEqualsN() {
		Point p = new Point(12,4,28);
		Point m = new Point(11,4,28);
		boolean b = p.equals(m);
		assertFalse(b);
	}
}


