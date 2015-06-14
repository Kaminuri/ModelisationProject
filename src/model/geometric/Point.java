package model.geometric;

import java.util.Comparator;


public class Point implements Comparable<Point>,Comparator<Point>{
	private double x, y, z;
	
	/**
	 * Cree un point,possedant trois doubles 
	 * @param x La valeur du point en x
	 * @param y La valeur du point en y
	 * @param z La valeur du point en z
	 */
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
	
	/**
	 * Retour le produit scalaire entre le point actuel et le point passe en parametre
	 * @param p Le deuxieme point, avec lequel on fera le produit scalaire
	 * @return valeur du produit scalaire, sous forme de double
	 */
	public double scalarProduct(Point p){
		return x*p.x+y*p.y+z*p.z;
	}

	/**
	 * Multiplie les valeurs du point actuel par un entier 
	 * @param m l'entier qui multipliera les valeurs
	 */
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
     * Calcule l'angle entre deux vecteurs
     * @param vecteur le deuxieme point a partir duquel calculer l'angle
     * @return double correspondant a l'angle
     */
    public double angle(Point vecteur) {
        return Math.acos(cosine(vecteur));
    }
    
    /**
     * Calcule la valeur du cosinus de l'angle entre deux vecteurs
     * @param vecteur le deuxieme point a partir duquel calculer le cosinus
     * @return un double correspondant a l'angle
     */
    public double cosine(Point vecteur) {
        double vX = vecteur.getX();
        double vY = vecteur.getY();
        double vZ = vecteur.getZ();
        double normeU = Math.sqrt(x * x + y * y + z * z);
        double normeV = Math.sqrt(vX * vX + vY * vY + vZ * vZ);
        double scalaire = x * vX + y * vY + z * vZ;
        return scalaire/(normeU*normeV);
    }
    /**
     *  Effectue le calcul du produit vectoriel entre le point actuel et le point passé en parametre
     * @param point avec lequel effectuer le produit vectoriel, en plus du point actuel
     * @return le nouveau point issu du produit vectoriel entre le point actuel et le point en parametre
     */
    public Point produitVectoriel (Point point){
        double x1 = point.getX();
        double y1 = point.getY();
        double z1 = point.getZ();
        return new Point (y*z1 - z*y1, z*x1 - x*z1, x*y1 - y*x1);
    }
}
