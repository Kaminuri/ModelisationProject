package model.geometric;

import java.util.Comparator;


public class Point implements Comparable<Point>,Comparator<Point>{
	private double x, y, z;
	public Point(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Renvoie la valeur en x du point
	 * @return la valeur en x du point sous forme de double
	 */
	public double getX(){
		return x;
	}
	
	
	
	/**
	 * Renvoie la valeur en y du point
	 * @return la valeur en y du point sous forme de double
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Renvoie la valeur en z du point
	 * @return la valeur en z du point sous forme de double
	 */
	public double getZ(){
		return z;
	}
	
	/**
	 * permet d'affecter une valeur a la valeur x du point
	 * @param x un double
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * permet d'affecter une valeur a la valeur y du point
	 * @param y un double
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * permet d'affecter une valeur a la valeur z du point
	 * @param z un double
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * Retourne une chaine contenant les coordonnees du point
	 */
	public String toString(){
		return "[" +x+ "/" +y+"/" +z +"]";
	}
	
	/**
	 * Retourne true si le point p possede des coordonnees egales a celles du point etudie
	 * @param p le point a tester
	 * @return true si les coordonnees sont egales, false sinon
	 */
	public boolean equals(Point p){
		return x == p.x && y == p.y && z == p.z;
	}
	public double scalarProduct(Point p){
		return x*p.x+y*p.y+z*p.z;
	}

	public void multiply(int m){
		x*=m;
		y*=m;
		z*=m;
	}
	@Override
	public int compareTo(Point p) {
		if(p.getZ()<this.getZ()){
			return 1;
		}
		else if(p.getZ()>this.getZ()){
			return -1;
		}
		else if(p.getY() < this.getY()){
			return 1;
		}
		else if(p.getY() > this.getY()){
			return -1;
		}
		return 0;
	}
	
	
	@Override
	public int compare(Point p1, Point p2) {
		if(p1.getZ()<p2.getZ()){
			return 1;
		}
		else if(p1.getZ()>p2.getZ()){
			return -1;
		}
		else if(p1.getY() < p2.getY()){
			return 1;
		}
		else if(p1.getY() > p2.getY()){
			return -1;
		}
		return 0;
	}
	
	
	/**
     * Calculate the angle between 2 vectors
     * @param vecteur
     * @return angle
     */
    public double angle(Point vecteur) {
        double vX = vecteur.getX();
        double vY = vecteur.getY();
        double vZ = vecteur.getZ();
        double normeU = Math.sqrt(x * x + y * y + z * z);
        double normeV = Math.sqrt(vX * vX + vY * vY + vZ * vZ);
        double scalaire = x * vX + y * vY + z * vZ;
        scalaire = Math.sqrt(scalaire * scalaire);
        return Math.asin(scalaire/(normeU*normeV));
    }
	
    /**Realize a vectorial product between this point and the point in parameter
     * @param point point to multiply
     * @return the new point from vectorial product
     */
    public Point vectorialProduct (Point point){
        double x1 = point.getX();
        double y1 = point.getY();
        double z1 = point.getZ();
        return new Point (y*z1 - z*y1, z*x1 - x*z1, x*y1 - y*x1);
    }
}
