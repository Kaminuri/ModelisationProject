package view.frame.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImportFrame extends JFrame{
	public ImportFrame(File f){
		JPanel contain = new JPanel();
		//Label
		JPanel labelContainer = new JPanel();
		labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));
		JTextField name = new JTextField(f.getName());
		JLabel tag = new JLabel("Tags");
		JLabel desc = new JLabel("Description");
		labelContainer.add(name);
		labelContainer.add(tag);
		labelContainer.add(desc);
		
		//textSetter
		JPanel textContainer = new JPanel();
		textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
		JButton buttonToChangeFile = new JButton(f.getName());
		buttonToChangeFile.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				if(j.getSelectedFile() != null){
					//Utilisable pour l'instant par tout le monde
					File ImportFileTmp = j.getSelectedFile();
					new ImportFrame(ImportFileTmp);
				}
				
			}
		});
		JTextField non = new JTextField();
		JTextField tg = new JTextField();
		textContainer.add(buttonToChangeFile);
		textContainer.add(non);
		textContainer.add(tg);
		
		//les buttons
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
		JButton next = new JButton("Next");
		JButton cancel = new JButton("Cancel");
		cancel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				
			}
		});
		
		buttonContainer.add(next);
		buttonContainer.add(cancel);
		
		contain.add(labelContainer);
		contain.add(textContainer);
		contain.add(buttonContainer);
		getContentPane().add(contain);
		//this.setResizable(true);
		this.pack();
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
}