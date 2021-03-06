package model.geometric;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

import model.loader.FileParser;


public class Item3D extends Observable{
	private ArrayList<Point> points;
	private ArrayList<Segment> segments;
	private ArrayList<Face> faces;
	public FileParser fp;
	private double zoomX,zoomY,zoom, posX, posY;
	private int transX,transY = 0;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private double xCentring, yCentring;
	private Point barycenter;
	private boolean filsdefer;
	/**
	 * Cree un Item3D,contenant les points,segments et faces du modele
	 * @param fp Le fileparser contenant les donnees du modele
	 */
	public Item3D(FileParser fp){
		filsdefer = false;
		points = fp.getListPoints();
		segments = fp.getListSegments();
		faces = fp.getListFaces();
		zoomX =  d.width/Math.abs(this.extremites()[2]-this.extremites()[3]);
		zoomY =  d.height/Math.abs(this.extremites()[0]-this.extremites()[1]);
		zoom = zoomX > zoomY ? zoomY - 15 : zoomX ;
		zoomX = zoom;
		zoomY = -zoom;
		xCentring = (extremites()[1] + extremites()[0])/2;
        yCentring = (extremites()[3] + extremites()[2])/2;
        
        barycenter = barycentre();
	}
	
	/**
	 * Modifie la valeur du boolean filsdefer
	 * @param b la nouvelle valeur du boolean
	 */
	public void setFilsDefer(boolean b){
		filsdefer = b;
	}
	
	/**
	 * Renvoie la valeur du boolean filsdefer
	 * @return boolean filsdefer
	 */
	public boolean getFilsDeFer(){
		return filsdefer;
	}
	/**
	 * Renvoie la position en X du barycentre de l'item
	 * @return double posX, la position en X
	 */
	public double getPosXItem(){
		return posX;
	}
	
	/**
	 * Renvoie la position en Y du barycentre de l'item
	 * @return double posY, la position en Y
	 */
	public double getPosYItem(){
		return posY;
	}
	
	/**
	 * Definit la position en x et y du barycentre de l'item3D
	 * @param x La position en x
	 * @param y la position en y
	 */
	public void setPosXY(double x, double y){
		posX = x;
		posY = y;
	}
	
	/**
	 * Diminue la valeur du zoom vertical
	 */
	public void decreaseZoomY() {
		zoomY *= 0.9;
	}
	
	/**
	 * Retourne la taille de l'ecran sous forme d'une dimension
	 * @return Dimension d, la taille de l'ecran
	 */
	public Dimension getScreenSize(){
		return d;
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
		Collections.sort(points,Collections.reverseOrder());
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
		
		for(int i = 0;i<points.size();i++){
			double y,z;
			y = points.get(i).getY();
			z = points.get(i).getZ();
			points.get(i).setY((y)*Math.cos(angle)+(z)*-Math.sin(angle));
			points.get(i).setZ((y)*Math.sin(angle)+(z)*Math.cos(angle));
		}
	}
	
	/**
	 * Applique une rotation du modele sur l'axe Y
	 * @param angle La valeur de la rotation, en double
	 */
	public void rotationY(double angle){
		for(int i = 0;i<points.size();i++){
			double x,z;
			x = points.get(i).getX();
			z = points.get(i).getZ();
			points.get(i).setX((x)*Math.cos(angle)+(z)*-Math.sin(angle));
			points.get(i).setZ((x)*Math.sin(angle)+(z)*Math.cos(angle));
		}
	}
	
	/**
	 * Applique une rotation du modele sur l'axe Z
	 * @param angle La valeur de la rotation, en double
	 */
	public void rotationZ(double angle){
		for(int i = 0;i<points.size();i++){
			double x,y;
			x = points.get(i).getX();
			y = points.get(i).getY();
			
			points.get(i).setX(x*Math.cos(angle)+y*-Math.sin(angle));
			points.get(i).setY(x*Math.sin(angle)+y*Math.cos(angle));
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

	/**
	 * Retourne un tableau contenant les extremites du modele
	 * Indice 0 : Extremite minimale sur X
	 * Indice 1 : Extremite maximale sur X
	 * Indice 2 : Extremite minimale sur Y
	 * Indice 3 : Extremite maximale sur Y
	 * @return un tableau de double contenant les extremites du modele
	 */
	public double[] extremites(){
		double[] tab=new double[4];
		tab[0] = points.get(0).getX();
		tab[1] = points.get(0).getX();
		tab[2] = points.get(0).getY();
		tab[3] = points.get(0).getY();
		for (Face f2 : faces) {
			Point[] t = f2.getPoints();
			for(Point p : t){
				if(tab[0] > p.getX()){//Si le point actuel a une valeur en x inferieure a celle dans tab[0]
					tab[0] = p.getX();
				}
				if(tab[1] < p.getX()){//Si le point actuel a une valeur en x superieure a celle dans tab[1]
					tab[1] = p.getX();
				}
				if(tab[2] > p.getY()){//Si le point actuel a une valeur en y inferieure a celle dans tab[2]
					tab[2] = p.getY();
				}
				if(tab[3] < p.getY()){//Si le point actuel a une valeur en y superieure a celle dans tab[3]
					tab[3] = p.getY();
				}
			}
		}
		return tab;
	}
	
	/**
	 * Affecte un setchanged() et notifyObservers(),  et retourne une arraylist contenant les points du modele
	 * @return ArrayList des points du modele
	 */
	public ArrayList<Point> getPoints() {
		setChanged();
		notifyObservers();
		return points;
	}
	
	/**
	 * Definit les points du modele a partir d'une arraylist de points
	 * @param points L'arraylist contenant les points du modele
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
		setChanged();
		notifyObservers();
	}


	/**
	 * Retourne la liste des Segments du modele
	 * @return ArrayList des Segments du modele
	 */
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	
	/**
	 * 
	 * @param segments
	 */
	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}

	/**
	 * Retourne la liste des Faces du modele
	 * @return ArrayList des Faces du modele
	 */
	public ArrayList<Face> getFaces() {
		setChanged();
		notifyObservers();
		return faces;
	}
	
	/**
	 * Trie les faces en fonction de l'ordre dans lequel elles doivent etre affichees
	 */
	public void algoPainter(){
		Collections.sort(faces, new Comparateur ());
	}

	
	class Comparateur implements Comparator <Face> {
		@Override
		public int compare(Face f1, Face f2) {
			return f1.barycentre().compareTo(f2.barycentre());
		}
	}


	/**
	 * Calcule le barycentre du modele et le retourne sous forme de point
	 * @return Le point correspondant au barycentre du modele 
	 */
	public Point barycentre() {
		double pointX = 0;
		double pointY = 0;
		double pointZ = 0;
		for (int i = 0; i < points.size(); i++) {
			pointX += points.get(i).getX();
			pointY += points.get(i).getY();
			pointZ += points.get(i).getZ();
		}
		pointX /= points.size();
		pointY /= points.size();
		pointZ /= points.size();
		return new Point(pointX+transX, pointY+transY, pointZ);
	}
	
	/**
	 * Recree l'item
	 * @param fp le fileparser contenant les informations de l'item
	 */
	public void recreateItem(FileParser fp){
		filsdefer = false;
		points = fp.getListPoints();
		segments = fp.getListSegments();
		faces = fp.getListFaces();
		zoomX =  d.width/Math.abs(this.extremites()[2]-this.extremites()[3]);
		zoomY =  d.height/Math.abs(this.extremites()[0]-this.extremites()[1]);
		zoom = zoomX > zoomY ? zoomY - 15 : zoomX ;
		zoomX = zoom;
		zoomY = -zoom;
		xCentring = (extremites()[1] + extremites()[0])/2;
        yCentring = (extremites()[3] + extremites()[2])/2;
        barycenter = barycentre();
	}

	/**
	 * renvoie le centre en X
	 * @return xCentring , le centre en X de l'item3D
	 */
	public double getXCentring() {
		return xCentring;
	}

	/**
	 * renvoie le centre en Y
	 * @return yCentring , le centre en Y de l'item3D
	 */
	public double getYCentring() {
		return yCentring;
	}

	/**
	 * Renvoie le zoom
	 * @return double zoom
	 */
	public double getZoom() {
		return zoom;
	}

	public void rotateByMouse(MouseEvent e) {
		double x2 = e.getX();
        double y2 = e.getY();
        double angleY = x2 - getPosXItem();
        double angleX = y2 - getPosYItem();
        double px, py;
        Point barycenter = barycentre();
        if (angleX >= 1 || angleX <= -1 || angleY >= 1 || angleY <= -1) {
            angleX *= -(2.0 * Math.PI) / 4000;
            angleY *= (2.0 * Math.PI) / 4000;
            for (Point p : getPoints()) {
                //rotation selon y
                px = p.getX();
                p.setX((p.getX() - barycenter.getX()) * (Math.cos(angleY)) + (p.getZ() - barycenter.getZ()) * (-Math.sin(angleY)));
                p.setZ((px - barycenter.getX()) * (Math.sin(angleY)) + (p.getZ() - barycenter.getZ()) * (Math.cos(angleY)));
                p.setX(p.getX() + barycenter.getX());
                p.setZ(p.getZ() + barycenter.getZ());

                //rotation selon x
                py = p.getY();
                p.setY(((p.getY() - barycenter.getY())* Math.cos(angleX)) + ((p.getZ() - barycenter.getZ())* -Math.sin(angleX)));
                p.setZ(((py - barycenter.getY())* (Math.sin(angleX))) + ((p.getZ() - barycenter.getZ())* Math.cos(angleX)));
                p.setY(p.getY() + barycenter.getY());
                p.setZ(p.getZ() + barycenter.getZ());
                setPosXY(x2, y2);
            }

        }
    }

	public void translateByMouse(MouseEvent e) {
		double x1 = e.getX();
        double y1 = e.getY();
        double distanceX = x1 - posX;
        double distanceY = y1 - posY;
        if (distanceX >= 1 || distanceX <= -1 || distanceY >= 1 || distanceY <= -1) {
            for (Point p : getPoints()) {
                double x2 = p.getX() + (distanceX) / zoom;
                double y2 = p.getY() - (distanceY) / zoom;
                p.setX(x2);
                p.setY(y2);
                setPosXY(x1, y1); 
                barycenter = barycentre();
            }
        }

        
	}


}
