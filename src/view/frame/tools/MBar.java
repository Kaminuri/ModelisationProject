package view.frame.tools;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
		//Fichier
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("Fichier");
		this.add(menu);

		
		
		
		//Ouvrir
		menuItem = new JMenuItem("Ouvrir", KeyEvent.VK_O);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new Open(i);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Ouvre un document valide depuis la base de donnees");
		menu.add(menuItem);
		
		
		
		
		
		
		
		//Importer
		menuItem = new JMenuItem("Importer", KeyEvent.VK_I);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new Import();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Importe un fichier depuis les documents de l'utilisateur");
		menu.add(menuItem);
		
		
		
		
		
		menu.addSeparator();
		
		
		
		//Supprimer
		menuItem = new JMenuItem("Supprimer", KeyEvent.VK_S);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Delete();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Supprime un fichier dans la base de donnees");
		menu.add(menuItem);
		
		
		
		
		
		
		//Exporter
		menuItem = new JMenuItem("Exporter", KeyEvent.VK_E);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Export();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Exporte un fichier de la base de donnees aux documents de l'utilisateur");
		menu.add(menuItem);
		
		
		
		
		
		menu.addSeparator();
		
		
		
		
		
		
		
		//Quitter
		menuItem = new JMenuItem("Quitter", KeyEvent.VK_Q);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Quitte l'application");
		menu.add(menuItem);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Cree l'onglet views
	 */
	private void addView(){
		//Vues
		menu = new JMenu("Vues");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Panel vue");
		
		
		//Fil de fer
		menuItem = new JMenuItem("Dessin fil de fer");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i.setFilsDefer(true);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Dessiner le modele en fil de fer");
		menu.add(menuItem);
		
		
		
		
		//Rempli
		menuItem = new JMenuItem("Dessin rempli");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i.setFilsDefer(false);
			}
		});
		menuItem.getAccessibleContext().setAccessibleDescription("Dessiner le modele avec les faces remplies");
		menu.add(menuItem);
		
		
		//Afficher la fenetre des options si elle a ete formee
		menuItem = new JMenuItem("Afficher options", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ifo.setVisible(true);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Front view");
		menu.add(menuItem);
		//Ajoute le sous-onglet Show option frame

		
		this.add(menu);
	}
	
	/**
	 * Ajoute l'aide a la MBar
	 */
	private void addHelp(){
		menu = new JMenu("?");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("File");
		this.add(menu);
		
		//help
		menuItem = new JMenuItem("Aide", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("help");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
					    "Pour choisir le modele, cliquez sur vue", "Help", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		//A Propos
		menuItem = new JMenuItem("A propos", KeyEvent.VK_T);
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

