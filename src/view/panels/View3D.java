package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import model.geometric.Face;
import model.geometric.Item3D;
import model.geometric.Point;
import controller.geometric.GeometricController;

public class View3D extends JDesktopPane{

	Item3D i;
	GeometricController c;
	int[] listeX,listeY;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Cree un View3D a partir d'un item3D et d'un GeometricController
	 * @param i l'item3D contenant le modele
	 * @param c le GeometricController permettant de controler le modele
	 */
	public View3D(Item3D i,GeometricController c){
		this.i = i;
		this.c = c;

		c.listPointSort();
	}

	/**
	 * Permet d'afficher la figure
	 */
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, d.width+50,d.height);
		g.setColor(Color.BLACK);
		listeX = new int[3];
		listeY = new int[3];

		double x0 = d.width / 2; //Permet d'aligner la figure sur l'axe des x
		double y0 = d.height - 60; //permet d'aligner la figure sur l'axe des y
		for (int j = i.getFaces().size()-1; j>= 0 ; j--) {
			Face f = i.getFaces().get(j);
			Point[] tab = f.getPoints();
			for(int h=0;h<3;h++){
				listeX[h] = (int)(tab[h].getX()*c.getZoomX() +x0 + c.getTransX());
				listeY[h] = (int)(tab[h].getY()*c.getZoomY() +y0 + c.getTransY());
			}
			Point vector1 = new Point(tab[1].getX() - tab[0].getX(), tab[1].getY() - tab[0].getY(), tab[1].getZ() - tab[0].getZ());
            Point vector2 = new Point(tab[2].getX() - tab[0].getX(), tab[2].getY() - tab[0].getY(), tab[2].getZ() - tab[0].getZ());
            final Point Light = new Point(0, 0, 1);
            int color = (int) (Light.angle(vector1.produitVectoriel(vector2)) * (150 / (Math.PI / 2))) + 25;
            g.setColor(new Color(color, color, color));
			g.fillPolygon(listeX, listeY, 3);
		}
	}


	
}
