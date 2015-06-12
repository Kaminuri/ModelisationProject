package view.frame.tools;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import BDD.Base;

@SuppressWarnings("serial")
public class ImportFrame extends JFrame{
	
	protected Base bdd;
	private JTextField tag, name;
	private JTextArea descript;
	private File f;
	
	public ImportFrame(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Model 3D GTS","gts");
		chooser.setFileFilter(filter);
		if (chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
			JPanel contain = new JPanel();
			bdd = new Base("Base.db");
			//Label
			JPanel labelContainer = new JPanel();
			labelContainer.setLayout(new GridLayout(7,1));
			String nomFile = f.getName().split(".gts")[0];
			name = new JTextField(nomFile);
			JLabel tagz = new JLabel("Tags");
			JLabel desc = new JLabel("Description");
			labelContainer.add(name);
			labelContainer.add(tagz);
			labelContainer.add(desc);
			
			//textSetter
			JPanel textContainer = new JPanel();
			textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
			JButton buttonToChangeFile = new JButton("Fichiers");
			tag = new JTextField();
			descript = new JTextArea();
			descript.setPreferredSize(new Dimension(100,100));
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
					dispose();
					new ImportFrame();
				}
			});
			
			textContainer.add(buttonToChangeFile);
			textContainer.add(tag);
			textContainer.add(descript);
			
			//les buttons
			JPanel buttonContainer = new JPanel();
			buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
			JButton next = new JButton("  Next  ");
			next.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e){
				String tags = tag.getText();
				String nam = name.getText();
				String des =  descript.getText();
				String[] tab = tags.split(", ");
				ArrayList<String> tab2 = new ArrayList<String>() ;
				if(!bdd.estDeja(nam)){
					File dest = new File("resources/models/" + nam + ".gts");
					copyFile(f, dest);
					if(!descript.equals(" ")){
						tab2.add(nam);						
						if(tab.length >= 3){
							for(int i = 1; i < tab.length; i++){
							tab2.add(tab[i-1]);
							}
						}
						else if(tab.length < 3){
							int i = 1;
							for(; i < tab.length + 1; i++){
								tab2.add(tab[i-1]);
							}
							for(; i < 4; i++){
								tab2.add("null");
							}
						}
					}
					else{
						tab2.add(nam);
						for(int i = 1; i < 3; i ++){
							tab2.add("null");
						}
					}
					Scanner scan = null;
					try {
						scan = new Scanner(f);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					int sommets = Integer.parseInt(scan.next());
					int segments = Integer.parseInt(scan.next());
					int faces = Integer.parseInt(scan.next());
					bdd.insert(nam, nam + ".gts", tab2.get(0), tab2.get(1), tab2.get(2), tab2.get(3), sommets, segments, faces, des);
					dispose();
					JOptionPane.showMessageDialog(new JFrame(), "L'importation a ete effectuee avec succes");
				}
					
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Le nom existe deja");
				}
								
			}
		}); 
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
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
		    this.setVisible(true);
		}
	}
	
	public static boolean copyFile(File source, File dest){
		try{
			// Declaration et ouverture des flux
			java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);
	 
			try{
				java.io.FileOutputStream destinationFile = null;
	 
				try{
					destinationFile = new FileOutputStream(dest);
	 
					// Lecture par segment de 0.5Mo 
					byte buffer[] = new byte[512 * 1024];
					int nbLecture;
	 
					while ((nbLecture = sourceFile.read(buffer)) != -1){
						destinationFile.write(buffer, 0, nbLecture);
					}
				} finally {
					destinationFile.close();
				}
			} finally {
				sourceFile.close();
			}
		} catch (IOException e){
			e.printStackTrace();
			return false; // Erreur
		}
	 
		return true; // Resultat OK  
	}
}