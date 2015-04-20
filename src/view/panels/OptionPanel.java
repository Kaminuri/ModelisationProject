package view.panels;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.geometric.GeometricController;


public class OptionPanel extends JPanel{
	private JButton nZoom , mZoom , pZoom, rotationG, rotationD, translationH, translationB, translationG, translationD ;
	private GeometricController gc;
	public OptionPanel(GeometricController gc){
		this.gc = gc;
		this.setLayout(new GridLayout(1,8));
		nZoom = new JButton();
		mZoom = new JButton();
		pZoom = new JButton();
		rotationG = new JButton();
		rotationD = new JButton();
		translationH = new JButton();
		translationB = new JButton();
		translationG = new JButton();
		translationD = new JButton();
		initNZoom();
		initMZoom();
		initPZoom();
		initRotationG();
	//	initRotationD();
		initTranslationH();
		initTranslationB();
		initTranslationD();
		initTranslationG();

		add(pZoom);
		add(nZoom);
		add(mZoom);
		add(rotationG);
		add(rotationD);
		add(translationH);
		add(translationB);
		add(translationG);
		add(translationD);

	}

	private void initNZoom(){
		//	nZoom.setIcon(new ImageIcon(""));
		nZoom.setText("-");
		nZoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				gc.decreaseZoomY();
				gc.decreaseZoomX();			
			}
		});
		nZoom.setSize(new Dimension(10,70));


	}
	private void initPZoom(){
		pZoom.setText("+");
		//	pZoom.setIcon(new ImageIcon("/resources/Zoom+.jpg"));
		pZoom.setSize(new Dimension(10,70));
		pZoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Quand on clic sur le bouton -> zoom avant

				gc.increaseZoomX();
				gc.increaseZoomY();
			}
		}); 


	}
	
	private void initTranslationH(){
		translationH.setText("â†‘");
		translationH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.decreaseTransY();
			}
		});
		translationH.setSize(new Dimension(10,70));
	}
	private void initTranslationB(){
		translationB.setText("â†“");
		translationB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.increaseTransY();
			}
		});
		translationB.setSize(new Dimension(10,70));
	}
	private void initTranslationG(){
		translationG.setText("â†�");
		translationG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.decreaseTransX();
				
			}
		});
		translationG.setSize(new Dimension(10,70));
	}
	private void initTranslationD(){
		translationD.setText("â†’");
		translationD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.decreaseTransX();
			}
		});
		translationD.setSize(new Dimension(10,70));
	}

	private void initMZoom(){
		nZoom.setIcon(new ImageIcon(""));
	}
	private void initRotationG(){
		nZoom.setIcon(new ImageIcon(""));
	}
	
}
