package view.panels;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.geometric.GeometricController;


public class OptionPanel extends JPanel{
	
	private JButton nZoom , pZoom, rotationX, rotationY, rotationZ, translationH, translationB, translationG, translationD ;
	private GeometricController gc;
	public OptionPanel(GeometricController gc){
		this.gc = gc;
		this.setLayout(new GridLayout(1,8));
		nZoom = new JButton();
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
		add(nZoom);
		add(rotationX);
		add(rotationY);
		add(rotationZ);
		add(translationH);
		add(translationB);
		add(translationG);
		add(translationD);

	}

	private void initNZoom(){
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
		pZoom.setSize(new Dimension(10,70));
		pZoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gc.increaseZoomX();
				gc.increaseZoomY();
			}
		}); 


	}
	
	private void initTranslationH(){
		translationH.setText("^");
		translationH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.decreaseTransY();
			}
		});
		translationH.setSize(new Dimension(10,70));
		
	}
	private void initTranslationB(){
		translationB.setText("↓");
		translationB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.increaseTransY();
			}
		});
		translationB.setSize(new Dimension(10,70));
	}
	private void initTranslationG(){
		translationG.setText("←");
		translationG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.decreaseTransX();
				
			}
		});
		translationG.setSize(new Dimension(10,70));
	}
	private void initTranslationD(){
		translationD.setText("→");
		translationD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.increaseTransX();
			}
		});
		translationD.setSize(new Dimension(10,70));
	}

	private void rotationX(){
		rotationX.setText("Rot X");
		rotationX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.rotationX(0.1);
			}
		});
	}
	
	private void rotationY(){
		rotationY.setText("Rot Y");
		rotationY.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.rotationY(0.1);
			}
		});
	}
	
	private void rotationZ(){
		rotationZ.setText("Rot Z");
		rotationZ.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.rotationZ(0.1);
			}
		});
	}
	
}
