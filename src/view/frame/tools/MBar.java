package view.frame.tools;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import model.geometric.Item3D;

public class MBar extends JMenuBar{
	
	private static final long serialVersionUID = 1L;
	private JMenu menu;
	private JMenuItem menuItem;
	private JFrame frame;
	private Item3D i;
	private InternalFrameOption ifo;
	/**
	 * Cree une MBar
	 * @param frame La frame sur laquelle mettre la MBar
	 * @param i l'Item3D sur lequel affecter la MBar
	 * @param ifo 
	 */
	public MBar(JFrame frame, Item3D i, InternalFrameOption ifo){
		this.ifo = ifo;
		this.frame = frame;
		this.i = i;
		addFiles();
		addView();
		addHelp();
	}
	
	/**
	 * Ajoute les onglets a la MBar
	 */
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
					new Open(i);
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
					new Import();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Try to import a File from your documents");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Delete
		menuItem = new JMenuItem("Delete", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Delete();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Delete a file from the Data Base");
		menu.add(menuItem);
		
		//Export
		menuItem = new JMenuItem("Export", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Export();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Export a File from data base to documents");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		//Quit
		menuItem = new JMenuItem("Quit", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Quit a application");
		menu.add(menuItem);
	}
	
	
	/**
	 * Ajoute la vue
	 */
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
		
		//Show
		menuItem = new JMenuItem("Show Option Frame", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ifo.setVisible(true);
				
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Front view");
		menu.add(menuItem);
			
		
		this.add(menu);
	}
	
	/**
	 * Ajoute l'aide a la MBar
	 */
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
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
					    "L'aide sera disponible dans notre prochain DLC ! N'attendez plus et precommandez le e l'adresse suivante : www.onaimelesdlc.com", "Help", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		//A Propos
		menuItem = new JMenuItem("About", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("About this project");
menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
					    "Modelisation 3D vers. 0.01", "Information", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		menu.add(menuItem);
	}
	
}

