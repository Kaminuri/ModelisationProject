package exceptions;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExceptionFace extends Exception{
	
	public ExceptionFace() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"Probleme de Face");
	}

}
