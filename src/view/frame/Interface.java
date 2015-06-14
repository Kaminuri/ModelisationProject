package view.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.geometric.Item3D;
import view.frame.tools.InternalFrameOption;
import view.frame.tools.MBar;
import view.frame.tools.Open;
import view.panels.View3D;
import controller.geometric.GeometricController;



public class Interface implements Observer{
	private MBar menuBar;
	private View3D view;
	private JFrame f;
	private Dimension screenSize;
	public Interface(GeometricController gc, Item3D i){
		f = new JFrame("Modelisation");
		view = new View3D(i, gc);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.width -= 42;
		screenSize.height -= 42;
		
	    f.setPreferredSize(screenSize);
	    f.setLocation(20, 20);
	    InternalFrameOption ifo = new InternalFrameOption(gc);
	    view.add(ifo);
	    f.setContentPane(view);
	    
		menuBar = new MBar(f, i, ifo);
		f.setJMenuBar(menuBar);
		f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	    i.addObserver(this);
	    new Open(i);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		view.repaint();
	}
}
