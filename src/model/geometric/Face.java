package model.geometric;


public class Face {
	private Segment s1, s2;
	
	/**
	 * Cree une face avec 2 segments
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
			//Si le premier point de s2 correspond a un point deja present dans tab, alors on ajoute le deuxieme point a tab
			tab[2] = s2.getP2();
		}
		else{
			//sinon, on ajoute le premier point
			tab[2] = s2.getP1();
		}
		return tab;
	}
	
	
	/**
	 * Calcule le barycentre de la face et le retourne sous forme de point
	 * @return Le point correspondant au barycentre de la face 
	 */
    public Point barycentre(){
        double x = 0, y = 0, z = 0;
        Point[] liste = getPoints();
        for(Point pt : liste){
        	//Pour chaque point de la liste, on ajoute les valeurs en x,y et z du point aux variables x y et z
            x += pt.getX();
            y += pt.getY();
            z += pt.getZ();
        }
        //On calcule le barycentre de la figure
        x /= 3;
        y /= 3;
        z /= 3;
        return new Point(x, y, z);
        //On retourne le barycentre de la figure
    }

}
