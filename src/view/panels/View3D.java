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
		/*for(int j = 0; j < i.getPoints().size(); j++){
			listeX[j] = (int)(i.getPoints().get(j).getX()*c.getZoomX() + x0 + c.getTransX());
			listeY[j] = (int)(i.getPoints().get(j).getY()*c.getZoomY() + y0 + c.getTransY());
		}*/
		
		for (Face f : i.getFaces()) {
			Point[] tab = f.getPoints();
			for(int j=0;j<3;j++){
				listeX[j] = (int)(tab[j].getX()*c.getZoomX() +x0 + c.getTransX());
				listeY[j] = (int)(tab[j].getY()*c.getZoomY() +x0 + c.getTransY());
			}
			g.drawPolygon(listeX, listeY, 3);
		}
	}   
}
