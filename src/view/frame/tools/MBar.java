package view.frame.tools;


import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MBar extends JMenuBar{
	private JMenu menu, submenu;
	private JMenuItem menuItem;
	private JRadioButtonMenuItem rbMenuItem;
	private JCheckBoxMenuItem cbMenuItem;
	JFileChooser chooser;
   
	public MBar(){
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Model 3D GTS","gts");
		chooser.setFileFilter(filter);
		addFiles();
		addView();
		addHelp();
	}
	
	private void addFiles(){
		//File
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("File");
		this.add(menu);

		//Open
		menuItem = new JMenuItem("Open", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new Open();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Open a valid document from the Data Base");
		menu.add(menuItem);
		
		//Import
		menuItem = new JMenuItem("Import", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(null);
				if(chooser.getSelectedFile() != null){
					//Utilisable pour l'instant par tout le monde
					File ImportFileTmp = chooser.getSelectedFile();
					new ImportFrame(ImportFileTmp);
				}
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Try to import a File from your documents");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Delete
		menuItem = new JMenuItem("Delete", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");
		menu.add(menuItem);
		
		//Export
		menuItem = new JMenuItem("Export", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Export a File from data base to documents");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Quit
		menuItem = new JMenuItem("Quit", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Quit a application");
		menu.add(menuItem);
	}
	private void addView(){
		//File
		menu = new JMenu("Views");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Views Panels");
		
		//Normal Zoom
		menuItem = new JMenuItem("Normal Zoom",new ImageIcon("pictures/translationIcone.jpg") );
		/*menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");*/
		menu.add(menuItem);
		
		
		//+Zoom
		menuItem = new JMenuItem("+ Zoom",new ImageIcon("pictures/translationIcone.jpg") );
		/*menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");*/
		menu.add(menuItem);
		
		
		//-Zoom
		menuItem = new JMenuItem("- Zoom",new ImageIcon("pictures/translationIcone.jpg") );
		/*menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");*/
		menu.add(menuItem);
		
		menu.addSeparator();
		//Translation
		menuItem = new JMenuItem("Translation",new ImageIcon("pictures/translationIcone.jpg") );
		/*menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");*/
		menu.add(menuItem);
		
		//Rotation
		menuItem = new JMenuItem("Rotation", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Coupe
		menuItem = new JMenuItem("Sectional view", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Front
		menuItem = new JMenuItem("Front view", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Front view");
		menu.add(menuItem);
			
		//Top
		menuItem = new JMenuItem("Top view", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Top view");
		menu.add(menuItem);
		
		//Bot
		menuItem = new JMenuItem("Bottom view", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Bottom view");
		menu.add(menuItem);
		
		//Left
		menuItem = new JMenuItem("Left view", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Left view");
		menu.add(menuItem);
		
		
		//Right
		menuItem = new JMenuItem("Right view", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("right view");
		menu.add(menuItem);
		this.add(menu);
	}
	
	private void addHelp(){
		//?
		menu = new JMenu("?");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("File");
		this.add(menu);
		
		//help
		menuItem = new JMenuItem("Help", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("help");
		menu.add(menuItem);
		
		//A Propos
		menuItem = new JMenuItem("About", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("About this project");
		menu.add(menuItem);
	}
}

