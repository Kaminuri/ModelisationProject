package view.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDesktopPane;

import model.geometric.Face;
import model.geometric.Item3D;
import model.geometric.Point;
import controller.geometric.GeometricController;

@SuppressWarnings("serial")
public class View3D extends JDesktopPane{

	private Item3D i;
	private int[] listeX,listeY;

	/**
	 * Cree un View3D a partir d'un item3D et d'un GeometricController
	 * @param i l'item3D contenant le modele
	 * @param c le GeometricController permettant de controler le modele
	 */
	public View3D(Item3D i,GeometricController c){
		this.i = i;
		addMouseWheelListener(c.getZoomListener());
		addMouseMotionListener(c.getTransRotaListener());
		addMouseListener(c.getTranslationListener());
	}

	/**
	 * Permet d'afficher la figure
	 */
	public void paintComponent(Graphics g){
		i.algoPainter();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, i.getScreenSize().width+50,i.getScreenSize().height);
		g.setColor(Color.BLACK);
		listeX = new int[3];
		listeY = new int[3];

		double x0 = i.getScreenSize().width / 2; //Permet d'aligner la figure sur l'axe des x
		double y0 = i.getScreenSize().height /2; //permet d'aligner la figure sur l'axe des y
		for (int j = i.getFaces().size()-1; j>= 0 ; j--) {
			//Pour chaque face du modele
			Face f = i.getFaces().get(j);
			Point[] tab = f.getPoints();
			for(int h=0;h<3;h++){
				//Pour chaque point de la face, on recupere le x et le y
				listeX[h] = (int)(tab[h].getX()*i.getZoomX() +x0 + i.getTransX());
				listeY[h] = (int)(tab[h].getY()*i.getZoomY() +y0 + i.getTransY());
			}
			Point vector1 = new Point(tab[1].getX() - tab[0].getX(), tab[1].getY() - tab[0].getY(), tab[1].getZ() - tab[0].getZ());
            Point vector2 = new Point(tab[2].getX() - tab[0].getX(), tab[2].getY() - tab[0].getY(), tab[2].getZ() - tab[0].getZ());
            final Point Light = new Point(1, 1, 1);
            
            final int LIGHTCOLOR = 50;
            final int DARKCOLOR = 200;
            int color = (int) (Math.abs(Light.cosine(vector1.produitVectoriel(vector2))*(DARKCOLOR-LIGHTCOLOR))+0.5) + LIGHTCOLOR ;
            // On recupere la couleur
            g.setColor(new Color(color, color, color)); //On affecte la couleur au Graphics g
            if(i.getFilsDeFer()){
            	g.drawPolygon(listeX, listeY, 3); //on dessine la face
            }else{
            	g.fillPolygon(listeX, listeY, 3); //on dessine la face
            }
			
		}
	}


	
}
