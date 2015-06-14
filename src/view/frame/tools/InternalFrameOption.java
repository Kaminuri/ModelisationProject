package view.frame.tools;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import view.panels.OptionPanel;
import controller.geometric.GeometricController;


@SuppressWarnings("serial")
public class InternalFrameOption extends JInternalFrame{
	
	/**
	 * Cree une InternalFrameOption permettant de controler un modele
	 * @param cg Le GeometricController qui permettra de controler le modele auquel il est rattache
	 */
	public InternalFrameOption(GeometricController cg){
		super("",false,true,false, true);
		OptionPanel op = new OptionPanel(cg);
		setContentPane(op);
		setLocation(50, 50);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	   	setVisible(true);
	   	pack();
	}
}
