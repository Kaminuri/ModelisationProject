package test;

import static org.junit.Assert.*;
import model.geometric.Point;

import org.junit.Test;

public class TestPoint {

	@Test
	public void testGetX(){
		Point p = new Point(12,4,28);
		assertEquals(12, p.getX(), 0.01);
	}
	
	@Test
	public void testGetY(){
		Point p = new Point(12,4,28);
		assertEquals(4, p.getY(), 0.01);
	}
	
	@Test
	public void testGetZ(){
		Point p = new Point(12,4,28);
		assertEquals(28, p.getZ(), 0.01);
	}
	
	@Test
	public void testSetX(){
		Point p = new Point(12,4,28);
		p.setX(3);
		assertEquals(3, p.getX(), 0.01);
	}
	
	@Test
	public void testSetY(){
		Point p = new Point(12,4,28);
		p.setY(13);
		assertEquals(13, p.getY(), 0.01);
	}
	
	@Test
	public void testSetZ(){
		Point p = new Point(12,4,28);
		p.setZ(42);
		assertEquals(42, p.getZ(), 0.01);
	}
	
	@Test
	public void testToString(){
		Point p = new Point(12,4,28);
		assertEquals("[12.0/4.0/28.0]", p.toString());
	}
	
	
	@Test
	public void testScalarProduct() {
		Point p = new Point(12,4,28);
		Point x = new Point(6,7,9);
		double b = p.scalarProduct(x);
		assertEquals(352, b, 0.01);
	}
	
	@Test
	public void testMultiply() {
		Point p = new Point(12,4,28);
		int m = 4;
		p.multiply(m);
		assertEquals(48,p.getX(),0.01);
		assertEquals(16,p.getY(),0.01);
		assertEquals(112,p.getZ(),0.01);
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
	
	@Test
	public void testCompareToZ1() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,27);
		assertEquals(1, p.compareTo(m));
	}
	
	@Test
	public void testCompareToZ2() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,29);
		assertEquals(-1, p.compareTo(m));
	}
	
	@Test
	public void testCompareToY1() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,3,28);
		assertEquals(1, p.compareTo(m));
	}
	
	@Test
	public void testCompareToY2() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,5,28);
		assertEquals(-1, p.compareTo(m));
	}
	
	@Test
	public void testCompareToEquals() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,28);
		assertEquals(0, p.compareTo(m));
	}
	
	@Test
	public void testCompareZ1() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,27);
		Point l = new Point(12,4,28);
		assertEquals(1, p.compare(m,l));
	}
	
	@Test
	public void testCompareZ2() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,29);
		Point l = new Point(12,4,28);
		assertEquals(-1, p.compare(m,l));
	}
	
	@Test
	public void testCompareY1() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,3,28);
		Point l = new Point(12,4,28);
		assertEquals(1, p.compare(m,l));
	}
	
	@Test
	public void testCompareY2() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,5,28);
		Point l = new Point(12,4,28);
		assertEquals(-1, p.compare(m,l));
	}
	
	@Test
	public void testCompareEquals() {
		Point p = new Point(12,4,28);
		Point m = new Point(12,4,28);
		Point l = new Point(12,4,28);
		assertEquals(0, p.compare(m,l));
	}
	
	@Test
	public void testCosine() {
		Point p = new Point(1,2,3);
		Point m = new Point(2,3,4);
		assertEquals(0.992, p.cosine(m),0.001);
	}
	
	@Test
	public void testAngle() {
		Point p = new Point(1,2,3);
		Point m = new Point(2,3,4);
		assertEquals(7.252, p.angle(m),0.001);
	}
	
	@Test
	public void testProduitVectoriel() {
		Point p = new Point(1,2,3);
		Point m = new Point(4,5,6);
		Point f = new Point(-3,6,-3);
		assertEquals(f, p.produitVectoriel(m));
	}
	
	
}


