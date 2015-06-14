package test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;

import model.geometric.Item3D;
import model.loader.FileParser;

import org.junit.Test;

import exceptions.ExceptionFace;
import exceptions.ExceptionPoint;
import exceptions.ExceptionSegment;

public class testItem3D extends Observable{

	@Test
	public void testItem3d() {
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		assertNotNull(item);
	}
	
	@Test
	public void testGetPosXItem() {
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		assertEquals(0.0, item.getPosXItem(), 0.001);
	}
	
	@Test
	public void testGetPosYItem(){
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		assertEquals(0.0, item.getPosYItem(), 0.001);
	}
	
	@Test
	public void testSetPosXY(){
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		item.setPosXY(2.0, 8.0);
		assertEquals(8.0, item.getPosYItem(), 0.001);
		assertEquals(2.0, item.getPosXItem(), 0.001);
	}
	
	@Test
	public void testdecreaseZoom(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		double zoomX =  d.width/Math.abs(item.extremites()[2]-item.extremites()[3]);
		double zoomY =  d.height/Math.abs(item.extremites()[0]-item.extremites()[1]);
		double zoom = zoomX > zoomY ? zoomY -50 : zoomX ;
		zoomX = zoom;
		zoomY = -zoom;
		item.decreaseZoomX();
		item.decreaseZoomY();
		zoomX *= 0.9;
		zoomY *= 0.9;
		assertEquals(zoomX, item.getZoomX(), 0.01);
		assertEquals(zoomY, item.getZoomY(), 0.01);
	}
	
	@Test
	public void testIncreaseZoom(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		double zoomX =  d.width/Math.abs(item.extremites()[2]-item.extremites()[3]);
		double zoomY =  d.height/Math.abs(item.extremites()[0]-item.extremites()[1]);
		double zoom = zoomX > zoomY ? zoomY -50 : zoomX ;
		zoomX = zoom;
		zoomY = -zoom;
		item.increaseZoomX();
		item.increaseZoomY();
		zoomX *= 1.1;
		zoomY *= 1.1;
		assertEquals(zoomX, item.getZoomX(), 0.01);
		assertEquals(zoomY, item.getZoomY(), 0.01);
	}
	
	@Test
	public void testDecreaseTrans(){
		int transX = 0 ,transY = -450;
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		item.decreaseTransX();
		item.decreaseTransY();
		transX -= 15;
		transY -= 15;
		assertEquals(transX, item.getTransX());
		assertEquals(transY, item.getTransY());		
	}
	
	@Test
	public void testIncreaseTrans(){
		int transX = 0 ,transY = -450;
		FileParser file = null;
		try {
			file = new FileParser("resources/models/x_wing.gts");
		} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
			e.printStackTrace();
		}
		Item3D item = new Item3D(file);
		item.increaseTransX();
		item.increaseTransY();
		transX += 15;
		transY += 15;
		assertEquals(transX, item.getTransX());
		assertEquals(transY, item.getTransY());		
	}
	
	
	

}
