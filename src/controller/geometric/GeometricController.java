package controller.geometric;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collections;

import model.geometric.Item3D;
import model.geometric.Point;

public class GeometricController {
	private Item3D it;
	private double zoomX,zoomY,zoom, x, y;
	private int transX,transY = -450;
	private Point barycenter;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Cree un GeometricController, permettant d'effectuer les calculs sur le modele
	 * @param item Le modele sur lequel porteront les calculs
	 */
	public GeometricController(Item3D item){
		it = item;
		zoomX =  d.width/Math.abs(it.extremites()[2]-it.extremites()[3]);
		zoomY =  d.height/Math.abs(it.extremites()[0]-it.extremites()[1]);
		zoom = zoomX > zoomY ? zoomY -50 : zoomX ;
		zoomX = zoom;
		zoomY = -zoom;
		x = d.getWidth() /2;
		y = d.getHeight() /2;
	}
	
	/**
	 * Diminue la valeur du zoom vertical
	 */
	public void decreaseZoomY() {
		zoomY *= 0.9;
	}
	
	/**
	 * Diminue la valeur du zoom horizontal
	 */
	public void decreaseZoomX() {
		zoomX *= 0.9;
	}
	
	/**
	 * augmente la valeur du zoom horizontal
	 */
	public void increaseZoomX() {
		zoomX *= 1.1;
	}
	
	/**
	 * augmente la valeur du zoom vertical
	 */
	public void increaseZoomY() {
		zoomY *= 1.1;
	}
	
	/**
	 * Diminue la valeur de transition verticale
	 */
	public void decreaseTransY() {
		transY -= 15;
	}
	
	/**
	 * Diminue la valeur de transition horizontale
	 */
	public void decreaseTransX() {
		transX -= 15;
	}
	
	/**
	 * Augmente la valeur de transition verticale
	 */
	public void increaseTransY() {
		transY += 15;
	}
	
	/**
	 * Augmente la valeur de transition horizontale
	 */
	public void increaseTransX() {
		transX += 15;
	}
	
	/**
	 * Trie la liste des points 
	 */
	public void listPointSort() {
		Collections.sort(it.getPoints(),Collections.reverseOrder());
	}
	
	/**
	 * Retourne la valeur du zoom horizontal
	 * @return zoom horizontal, sous forme de double
	 */
	public double getZoomX() {
		return zoomX;
	}

	/**
	 * Retourne la valeur du zoom vertical
	 * @return zoom vertical, sous forme de double
	 */
	public double getZoomY() {
		return zoomY;
	}
	
	/**
	 * Applique une rotation du modele sur l'axe X
	 * @param angle La valeur de la rotation, en double
	 */
	public void rotationX(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			double y,z;
			y = it.getPoints().get(i).getY();
			z = it.getPoints().get(i).getZ();
			it.getPoints().get(i).setY(y*Math.cos(angle)+z*-Math.sin(angle));
			it.getPoints().get(i).setZ(y*Math.sin(angle)+z*Math.cos(angle));
		}
	}
	
	/**
	 * Applique une rotation du modele sur l'axe Y
	 * @param angle La valeur de la rotation, en double
	 */
	public void rotationY(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			double x,z;
			x = it.getPoints().get(i).getX();
			z = it.getPoints().get(i).getZ();
			it.getPoints().get(i).setX(x*Math.cos(angle)+z*-Math.sin(angle));
			it.getPoints().get(i).setZ(x*Math.sin(angle)+z*Math.cos(angle));}
	}
	
	/**
	 * Applique une rotation du modele sur l'axe Z
	 * @param angle La valeur de la rotation, en double
	 */
	public void rotationZ(double angle){
		for(int i = 0;i<it.getPoints().size();i++){
			double x,y;
			x = it.getPoints().get(i).getX();
			y = it.getPoints().get(i).getY();
			
			it.getPoints().get(i).setX(x*Math.cos(angle)+y*Math.sin(angle));
			it.getPoints().get(i).setY(x*-Math.sin(angle)+y*Math.cos(angle));
		}
	}

	/**
	 * Retourne la valeur de la transition sur X
	 * @return la transition sur X,sous forme d'entier
	 */
	public int getTransX() {
		return transX;
	}
	
	/**
	 * Retourne la valeur de la transition sur Y
	 * @return la transition sur Y,sous forme d'entier
	 */
	public int getTransY(){
		return transY;
	}

	public MouseWheelListener getZoomListener() {
		return new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() < 0){
					increaseZoomX();
		            increaseZoomY();
				}else if(e.getWheelRotation() > 0){
					decreaseZoomX();
					decreaseZoomY();
				}
			}
		
		};
	}

	public MouseMotionListener getTransRotaListener() {
		return new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
		        double x1 = e.getX();
		        double y1 = e.getY();
		        double distanceX = x1 - x;
		        double distanceY = y1 - y;
		        if (distanceX >= 1 || distanceX <= -1 || distanceY >= 1 || distanceY <= -1) {
		            for (Point p : it.getPoints()) {
		                double x2 = p.getX() + (distanceX) / zoomX;
		                double y2 = p.getY() + (distanceY) / zoomY;
		                p.setX(x2);
		                p.setY(y2);
		                x = x1;
		                y = y1;
		                barycenter = it.barycentre();
		            }
		        }

			}
		};
	}
	
}
