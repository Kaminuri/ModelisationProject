package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

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
		listeX = new int[i.getPoints().size()];
		listeY = new int[i.getPoints().size()];

		double x0 = d.width / 2;
		double y0 = d.height - 60;
		double z0 = (x0/2)+y0/2;
		for(int j = 0; j < i.getPoints().size(); j++){
			listeX[j] = (int)(i.getPoints().get(j).getX()*c.getZoomX() + x0 + c.getTransX());
			listeY[j] = (int)(i.getPoints().get(j).getY()*c.getZoomY() + y0 + c.getTransY());
		}
		g.drawPolygon(listeX, listeY, i.getPoints().size());
	}   





}
