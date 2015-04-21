package controller.geometric;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;

import model.geometric.Item3D;

public class GeometricController {
	private Item3D it;
	private double zoomX,zoomY,zoom;
	private int transX,transY;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public GeometricController(Item3D item){
		it = item;
		zoomX =  d.width/Math.abs(it.extremites()[2]-it.extremites()[3]);
		zoomY =  d.height/Math.abs(it.extremites()[0]-it.extremites()[1]);
		zoom = zoomX > zoomY ? zoomY -70 : zoomX;
		zoomX = zoom;
		zoomY = -zoom;
	}
	
	
	public void decreaseZoomY() {
		// TODO Auto-generated method stub
		zoomY *= 0.9;
	}
	public void decreaseZoomX() {
		// TODO Auto-generated method stub
		zoomX *= 0.9;
	}
	public void increaseZoomX() {
		// TODO Auto-generated method stub
		zoomX *= 1.1;
	}
	public void increaseZoomY() {
		// TODO Auto-generated method stub
		zoomY *= 1.1;
	}
	public void decreaseTransY() {
		// TODO Auto-generated method stub
		transY -= 15;
	}
	public void decreaseTransX() {
		// TODO Auto-generated method stub
		transX -= 15;
	}
	public void increaseTransY() {
		// TODO Auto-generated method stub
		transY += 15;
	}
	public void increaseTransX() {
		transX += 15;
	}

	public void listPointSort() {
		// TODO Auto-generated method stub
		Collections.sort(it.getPoints());
	}
	
	public double getZoomX() {
		return zoomX;
	}

	public double getZoomY() {
		return zoomY;
	}
	
	public void rotationX(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			it.getPoints().get(i).setY((it.getPoints().get(i).getY()*Math.cos(angle))+(it.getPoints().get(i).getZ()*-Math.sin(angle)));
			it.getPoints().get(i).setZ(it.getPoints().get(i).getY()*Math.sin(angle)+it.getPoints().get(i).getZ()*Math.cos(angle));
		}
	}
	
	public void rotationY(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			it.getPoints().get(i).setX(it.getPoints().get(i).getX()*Math.cos(angle)+it.getPoints().get(i).getZ()*Math.sin(angle));
			it.getPoints().get(i).setZ(it.getPoints().get(i).getX()*-Math.cos(angle)+it.getPoints().get(i).getZ()*Math.cos(angle));
		}
	}
	
	public void rotationZ(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			it.getPoints().get(i).setX(it.getPoints().get(i).getX()*Math.cos(angle)+it.getPoints().get(i).getZ()*Math.sin(angle));
			it.getPoints().get(i).setY(it.getPoints().get(i).getX()*-Math.cos(angle)+it.getPoints().get(i).getZ()*Math.cos(angle));
		}
	}

	public int getTransX() {
		return transX;
	}
	public int getTransY(){
		return transY;
	}
	
}
