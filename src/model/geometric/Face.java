package model.geometric;

import java.util.ArrayList;

public class Face {
	private Segment s1, s2;
	
	/**
	 * Cree une face avec 3 segments
	 * @param s1 le premier segment
	 * @param s2 le deuxieme segment
	 */
	public Face(Segment s1, Segment s2){
		this.s1 = s1;
		this.s2 = s2;
		s1.getP1();
		s1.getP2();
		s2.getP2();
	}

	
	/**
	 * Retourne un tableau contenant les points composant la face
	 * @return un tableau de Point de taille 3 contenant les points composant la face
	 */
	public Point[] getPoints(){
		Point[] tab = new Point[3];
		tab[0] = s1.getP1();
		tab[1] = s1.getP2();
		if(tab[0].equals(s2.getP1()) || tab[1].equals(s2.getP1())){
			tab[2] = s2.getP2();
		}
		else{
			tab[2] = s2.getP1();
		}
		return tab;
	}
	
	/**Calculate the barycenter of the face and return the point of it
     * @return new Point(x, y, z)*/
    public Point barycenter(){
        float x = 0, y = 0, z = 0;
        Point[] liste = getPoints();
        for(Point pt : liste){
            x += pt.getX();
            y += pt.getY();
            z += pt.getZ();
        }
        x /= 3;
        y /= 3;
        z /= 3;
        return new Point(x, y, z);
    }

}
