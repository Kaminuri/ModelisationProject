package view.frame.tools;

import javax.swing.JInternalFrame;

import view.panels.OptionPanel;
import controller.geometric.GeometricController;


@SuppressWarnings("serial")
public class InternalFrameOption extends JInternalFrame{
	public InternalFrameOption(GeometricController cg){
		super("",false,true,false, true);
		OptionPanel op = new OptionPanel(cg);
		setContentPane(op);
		setSize(400,70);
		setLocation(50, 50);
	   	setVisible(true);
	}
}
