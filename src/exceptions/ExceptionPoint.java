package exceptions;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExceptionPoint extends Exception{
	
	public ExceptionPoint() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"Probleme de point");
	}
}
