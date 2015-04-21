package model.geometric;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.loader.FileParser;


public class Item3D extends Observable{
	private ArrayList<Point> points;
	private ArrayList<Segment> segments;
	private ArrayList<Face> faces;
	private List<Face> f;
	public FileParser fp;
	
	public Item3D(FileParser fp){
		points = fp.getListPoints();
		segments = fp.getListSegments();
		faces = fp.getListFaces();
	}
	
	public double[] extremites(){
		//tab[0] -> minX ; tab[1] -> maxX  ; tab[2] -> minY ; tab[3] -> maxY
		double[] tab=new double[4];
		tab[0] = points.get(0).getX();
		tab[1] = points.get(0).getX();
		tab[2] = points.get(0).getY();
		tab[3] = points.get(0).getY();
		int i = 0;
		for (Face f2 : f) {
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
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

}
