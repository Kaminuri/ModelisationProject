package model.geometric;

public class Segment {
	
	private Point p1, p2;
	
	/**
	 * Cree un segment a partir de deux points
	 * @param p1 le premier point
	 * @param p2 le deuxieme point
	 */
	public Segment(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Retourne le premier point du segment
	 * @return le premier point du segment
	 */
	public Point getP1(){
		return p1;
	}
	
	/**
	 * Retourne le second point du segment
	 * @return le second point du segment
	 */
	public Point getP2(){
		return p2;
	}
}
