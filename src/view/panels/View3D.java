package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.geometric.Face;
import model.geometric.Item3D;
import model.geometric.Point;
import controller.geometric.GeometricController;

public class View3D extends JPanel{

	Item3D i;
	GeometricController c;
	int[] listeX,listeY;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public View3D(Item3D i,GeometricController c){
		this.i = i;
		this.c = c;

		c.listPointSort();
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, d.width+50,d.height);
		g.setColor(Color.BLACK);
		listeX = new int[3];
		listeY = new int[3];
		
		double x0 = d.width / 2;
		double y0 = d.height - 60;
		
		for (Face f : i.getFaces()) {
			Point[] tab = f.getPoints();
			for(int j=0;j<3;j++){
				listeX[j] = (int)(tab[j].getX()*c.getZoomX() +x0 + c.getTransX());
				listeY[j] = (int)(tab[j].getY()*c.getZoomY() +x0 + c.getTransY());
			}
			g.setColor(setColor(Color.gray, tab[0], tab[1], tab[2]));
			g.drawPolygon(listeX, listeY, 3);
		}
	}
	public Color setColor(Color col, Point a, Point b, Point c){
        Point ab = new Point(b.getX()-a.getX(),b.getY()-a.getY(),b.getZ()-a.getZ()); //on calcule le vecteur ab directeur du plan
        Point ac = new Point(c.getX()-a.getX(),c.getY()-a.getY(),c.getZ()-a.getZ()); //on calcule le vecteur ac directeur du plan
        Point normal = new Point((ab.getY()*ac.getZ()-ab.getZ()*ac.getY()),-(ab.getX()*ac.getZ()-ab.getZ()*ac.getX()),(ab.getX()*ac.getY()-ab.getY()*ac.getX())); //vecteur normal au plan
        /* un point comporte trois coordonnée x y z comme un vecteur donc je ne redéfini pas une classe vecteur comme elle est
		identique à la classe point.*/
        Point lumiere = new Point(0,0,-1); //vecteur directeur des rayons de lumière lumière
        double percent = normal.getX() * lumiere.getX() + normal.getY() * lumiere.getY() + normal.getZ() * lumiere.getZ();
        double normen = Math.sqrt(normal.getX() * normal.getX() + normal.getY() * normal.getY() + normal.getZ() * normal.getZ());
        double normel = Math.sqrt(lumiere.getX() * lumiere.getX() + lumiere.getY() * lumiere.getY() + lumiere.getZ() * lumiere.getZ());
        percent = percent / (normen * normel); //on divise par la multiplication des deux normes pour un résultat compris entre 0 et 1
        System.out.println(normen);
        float[] hsbCol = new float[]{col.getRed(), col.getGreen(), col.getBlue()}; //on utilise un autre format pour définir une couleur
        hsbCol[2] = (float) percent; //la colonne 2 permet de jouer sur la brillance de la couleur on peux jour sur la saturation avec la 1 colonne
       /* System.out.println(hsbCol[0]);
        System.out.println(hsbCol[1]);
        System.out.println(hsbCol[2]);*/
        return Color.getHSBColor(hsbCol[0], hsbCol[1], hsbCol[2]); //et on reconvertit en color
    }
}
