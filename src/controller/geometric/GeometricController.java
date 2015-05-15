package controller.geometric;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;

import model.geometric.Item3D;

public class GeometricController {
	private Item3D it;
	private double zoomX,zoomY,zoom;
	private int transX,transY = -450;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public GeometricController(Item3D item){
		it = item;
		zoomX =  d.width/Math.abs(it.extremites()[2]-it.extremites()[3]);
		zoomY =  d.height/Math.abs(it.extremites()[0]-it.extremites()[1]);
		zoom = zoomX > zoomY ? zoomY -50 : zoomX;
		zoomX = zoom;
		zoomY = -zoom;
	}
	
	
	public void decreaseZoomY() {
		zoomY *= 0.9;
	}
	public void decreaseZoomX() {

		zoomX *= 0.9;
	}
	public void increaseZoomX() {

		zoomX *= 1.1;
	}
	public void increaseZoomY() {
		zoomY *= 1.1;
	}
	public void decreaseTransY() {
		transY -= 15;
	}
	public void decreaseTransX() {
		transX -= 15;
	}
	public void increaseTransY() {
		transY += 15;
	}
	public void increaseTransX() {
		transX += 15;
	}

	public void listPointSort() {
		Collections.sort(it.getPoints(),Collections.reverseOrder());
	}
	
	public double getZoomX() {
		return zoomX;
	}

	public double getZoomY() {
		return zoomY;
	}
	
	public void rotationX(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			double y,z;
			y = it.getPoints().get(i).getY();
			z = it.getPoints().get(i).getZ();
			it.getPoints().get(i).setY(y*Math.cos(angle)+z*-Math.sin(angle));
			it.getPoints().get(i).setZ(y*Math.sin(angle)+z*Math.cos(angle));
		}
	}
	
	public void rotationY(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			double x,z;
			x = it.getPoints().get(i).getX();
			z = it.getPoints().get(i).getZ();
			it.getPoints().get(i).setX(x*Math.cos(angle)+z*-Math.sin(angle));
			it.getPoints().get(i).setZ(x*Math.sin(angle)+z*Math.cos(angle));}
	}
	
	public void rotationZ(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			double x,y;
			x = it.getPoints().get(i).getX();
			y = it.getPoints().get(i).getY();
			
			it.getPoints().get(i).setX(x*Math.cos(angle)+y*Math.sin(angle));
			it.getPoints().get(i).setY(x*-Math.sin(angle)+y*Math.cos(angle));
		}
	}

	public int getTransX() {
		return transX;
	}
	public int getTransY(){
		return transY;
	}
	
}
