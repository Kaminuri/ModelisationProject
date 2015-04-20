package view.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.geometric.Item3D;
import model.loader.FileParser;
import view.frame.tools.InternalFrameOption;
import view.frame.tools.MBar;
import view.panels.View3D;
import controller.geometric.GeometricController;



public class Interface implements Observer{
	private MBar menuBar;
	private GeometricController gc;
	private View3D view;
	private JFrame f;
	private Dimension screenSize;
	private Item3D item;
	public Interface(GeometricController gc, Item3D i){
		item = i;
		this.gc = gc;
		f = new JFrame();
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.width -= 42;
		screenSize.height -= 42;
		
	    f.setPreferredSize(screenSize);
	    f.setLocation(20, 20);
	    InternalFrameOption ifo = new InternalFrameOption(gc);
	    f.add(ifo);
	    f.getContentPane().add(ifo);
	    
		menuBar = new MBar();
		f.setJMenuBar(menuBar);
		//this.setResizable(true);
		f.pack();
	    //this.setLocationRelativeTo(null);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	    i.addObserver(this);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		view.repaint();
	}
}
