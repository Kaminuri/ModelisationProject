package view.frame.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Export extends Panel{

	private static final long serialVersionUID = 1L;

	/**
	 * Met un listener sur le bouton Export
	 */
	public Export(){
		super();
		JButton export = new JButton("Export");
		panel4.add(export);
		
		ActionListener listenerExport = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
		    String select = (String)list.getSelectedValue();
		    JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Model 3D GTS","gts");
			chooser.setFileFilter(filter);
		    chooser.showOpenDialog(null);
			if(chooser.getSelectedFile() != null){
				//Utilisable pour l'instant par tout le monde
				File exportFile = chooser.getSelectedFile();
				File file = new File(models.get(select).get("adresse"));
				if(copyFile(file, exportFile)){
					dispose();
					JOptionPane.showMessageDialog(new JFrame(), "L'Exportation a ete effectuee avec succes");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Une erreur est arrivee lors de l'exportation");
				}
				
				}
			}
			};
		export.addActionListener(listenerExport);
	}
	
	private static boolean copyFile(File source, File dest){
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