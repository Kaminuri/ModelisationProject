package view.panels;


import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.geometric.GeometricController;


@SuppressWarnings("serial")
public class OptionPanel extends JPanel{
	
	private JButton mZoom , pZoom, rotationX, rotationY, rotationZ, translationH, translationB, translationG, translationD ;
	private GeometricController gc;
	
	/**
	 * Cree un OptionPanel, couple a un GeometricController
	 * @param gc le GeometricController qui permettra d'effectuer les actions sur un modele, definit dans le GeometricController
	 */
	public OptionPanel(GeometricController gc){
		this.gc = gc;
		this.setLayout(new GridLayout(1,9));
		
		mZoom = new JButton();
		pZoom = new JButton();
		rotationX = new JButton();
		rotationY = new JButton();
		rotationZ = new JButton();
		translationH = new JButton();
		translationB = new JButton();
		translationG = new JButton();
		translationD = new JButton();
		
		initMZoom();
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
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton mZoom
	 */
	private void initMZoom(){
		mZoom.setText("-");
		mZoom.setSize(new Dimension(10,70));
		mZoom.addActionListener(gc.getMinusZoomListener());
	}
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton pZoom
	 */
	private void initPZoom(){
		pZoom.setText("+");
		pZoom.setSize(new Dimension(10,70));
		pZoom.addActionListener(gc.getPlusZoomListener());
	}
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton translationH
	 */
	private void initTranslationH(){
		translationH.setText("▲");
		translationH.addActionListener(gc.getTranslationHautListener());
		translationH.setSize(new Dimension(10,70));
		
	}
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton translationB
	 */
	private void initTranslationB(){
		translationB.setText("▼");
		translationB.addActionListener(gc.getTranslationBasListener());
		//translationB.setSize(new Dimension(10,70));
	}
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton translationG
	 */
	private void initTranslationG(){
		translationG.setText("◄");
		translationG.addActionListener(gc.getTranslationGaucheListener());
		translationG.setSize(new Dimension(10,70));
	}
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton translationD
	 */
	private void initTranslationD(){
		translationD.setText("►");
		translationD.addActionListener(gc.getTranslationDroiteListener());
		translationD.setSize(new Dimension(10,70));
	}

	/**
	 * Ajoute un texte,un listener et definit la taille du bouton rotationX
	 */
	private void rotationX(){
		rotationX.setText("Rot X");
		rotationX.addActionListener(gc.getRotationXListener());
	}

	/**
	 * Ajoute un texte,un listener et definit la taille du bouton rotationY
	 */
	private void rotationY(){
		rotationY.setText("Rot Y");
		rotationY.addActionListener(gc.getRotationYListener());
	}
	
	/**
	 * Ajoute un texte,un listener et definit la taille du bouton rotationZ
	 */
	private void rotationZ(){
		rotationZ.setText("Rot Z");
		rotationZ.addActionListener(gc.getRotationZListener());
	}


	
	
}
