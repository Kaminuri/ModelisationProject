package view.panels;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.geometric.GeometricController;


@SuppressWarnings("serial")
public class OptionPanel extends JPanel{
	
	private JButton mZoom , pZoom, rotationX, rotationY, rotationZ, translationH, translationB, translationG, translationD ;
	private GeometricController gc;
	public OptionPanel(GeometricController gc){
		this.gc = gc;
		this.setLayout(new GridLayout(1,8));
		mZoom = new JButton();
		pZoom = new JButton();
		rotationX = new JButton();
		rotationY = new JButton();
		rotationZ = new JButton();
		translationH = new JButton();
		translationB = new JButton();
		translationG = new JButton();
		translationD = new JButton();
		initNZoom();
		
		initPZoom();
		initTranslationH();
		initTranslationB();
		initTranslationD();
		initTranslationG();
		rotationX();
		rotationY();
		rotationZ();
		
		

		add(pZoom);
		add(mZoom);
		add(rotationX);
		add(rotationY);
		add(rotationZ);
		add(translationH);
		add(translationB);
		add(translationG);
		add(translationD);

	}

	private void initNZoom(){
		setButIcon(mZoom, "resources/pictures/Zoom-Minus-icon.png");
		mZoom.addActionListener(gc.getMinusZoomListener());
		mZoom.setSize(new Dimension(10,70));


	}
	private void initPZoom(){
		setButIcon(pZoom,"resources/pictures/Zoom-Plus-icon.png");
		pZoom.setText("+");
		pZoom.setSize(new Dimension(10,70));
		pZoom.addActionListener(gc.getPlusZoomListener());


	}
	
	private void initTranslationH(){
		translationH.setText("â†‘");
		translationH.addActionListener(gc.getTranslationHautListener());
		translationH.setSize(new Dimension(10,70));
		
	}
	private void initTranslationB(){
		translationB.setText("â†“");
		translationB.addActionListener(gc.getTranslationBasListener());
		translationB.setSize(new Dimension(10,70));
	}
	private void initTranslationG(){
		translationG.setText("â†�");
		translationG.addActionListener(gc.getTranslationGaucheListener());
		translationG.setSize(new Dimension(10,70));
	}
	private void initTranslationD(){
		translationD.setText("â†’");
		translationD.addActionListener(gc.getTranslationDroiteListener());
		translationD.setSize(new Dimension(10,70));
	}

	private void rotationX(){
		rotationX.setText("Rot X");
		rotationX.addActionListener(gc.getRotationXListener());
	}
	
	private void rotationY(){
		rotationY.setText("Rot Y");
		rotationY.addActionListener(gc.getRotationYListener());
	}
	
	private void rotationZ(){
		rotationZ.setText("Rot Z");
		rotationZ.addActionListener(gc.getRotationZListener());
	}
	private void setButIcon(JButton b, String path){
		try {
			//BufferedImage img = ImageIO.read(this.getClass().getResource(path));
		    Image img = ImageIO.read(new File(path));
		    Image newimg = img.getScaledInstance( 40, 36,  java.awt.Image.SCALE_SMOOTH ) ;
		    b.setIcon(new ImageIcon(newimg));
		  } 
		catch (IOException ex) {
		  }
	}
	
	
}
