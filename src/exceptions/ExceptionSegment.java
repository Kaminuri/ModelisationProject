package exceptions;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ExceptionSegment extends Exception{
	
	public ExceptionSegment() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"Probleme de segment");
	}

}
