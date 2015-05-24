package model.geometric;

import java.util.ArrayList;
import java.util.Observable;

import model.loader.FileParser;


public class Item3D extends Observable{
	private ArrayList<Point> points;
	private ArrayList<Segment> segments;
	private ArrayList<Face> faces;
	public FileParser fp;
	
	public Item3D(FileParser fp){
		points = fp.getListPoints();
		segments = fp.getListSegments();
		faces = fp.getListFaces();
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
		//tab[0] -> minX ; tab[1] -> maxX  ; tab[2] -> minY ; tab[3] -> maxY
		double[] tab=new double[4];
		tab[0] = points.get(0).getX();
		tab[1] = points.get(0).getX();
		tab[2] = points.get(0).getY();
		tab[3] = points.get(0).getY();
		for (Face f2 : faces) {
			Point[] t = f2.getPoints();
			for(Point p : t){
				if(tab[0] > p.getX()){
					tab[0] = p.getX();
				}
				if(tab[1] < p.getX()){
					tab[1] = p.getX();
				}
				if(tab[2] > p.getY()){
					tab[2] = p.getY();
				}
				if(tab[3] < p.getY()){
					tab[3] = p.getY();
				}
			}
		}
		return tab;
	}
	
	public ArrayList<Point> getPoints() {
		setChanged();
		notifyObservers();
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
		setChanged();
		notifyObservers();
	}

	public ArrayList<Segment> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}

	public ArrayList<Face> getFaces() {
		setChanged();
		notifyObservers();
		return faces;
	}

}
