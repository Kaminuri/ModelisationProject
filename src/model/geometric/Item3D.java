package model.geometric;

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
	
	/**
	 * Cree un Item3D,contenant les points,segments et faces du modele
	 * @param fp Le fileparser contenant les donnees du modele
	 */
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
		pointZ /= points	.size();
		return new Point(pointX, pointY, pointZ);
	}
	
	public void recreateItem(FileParser fp){
		points = fp.getListPoints();
		segments = fp.getListSegments();
		faces = fp.getListFaces();
	}


}
