package view.frame.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BDD.Base;

public class Open extends JFrame{
	protected JFrame frame;
	protected Base bdd;
	protected JPanel panel, panel2, panel3, panel4, panel5;
	protected JLabel points, segments, faces; 
	protected JLabel des;
	protected JTextField recherche;
	protected JList list;
	protected HashMap<String, HashMap<String, String>> models;
	public Open(){
		
		frame = this;
		frame.setPreferredSize(new Dimension(500, 300));
		frame.setResizable(false);
		panel = new JPanel(false);
		panel.setPreferredSize(new Dimension(110,300));
		panel2 = new JPanel(false);
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel(null);
		GridLayout grid1 = new GridLayout(3, 1);
    	GridLayout grid = new GridLayout(4 ,1);
    	panel2.setLayout(grid1);
    	panel2.setPreferredSize(new Dimension(380, 300));
    	panel3.setPreferredSize(new Dimension(60, 300));
    	panel4.setPreferredSize(new Dimension(60, 300));
    	panel5.setPreferredSize(new Dimension(260, 150));
    	panel2.add(panel3, BorderLayout.NORTH);
    	panel2.add(panel5, BorderLayout.CENTER);
    	panel2.add(panel4, BorderLayout.SOUTH);
    	panel5.setLayout(grid);
		frame.setSize(new Dimension(200, 200));
		Container contentPane = frame.getContentPane();
		
		bdd = new Base("Base.db");
		models = bdd.select();
		String [] tab = new String [models.size()];
		int i = 0;
		
		for(String mapKey : models.keySet()){
			tab[i] = models.get(mapKey).get("nom");
			i ++;
		}
		
		list = new JList(tab);
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setPreferredSize(new Dimension(100, 250));
		panel.add(scrollPane1);
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(panel2, BorderLayout.EAST);
		points = new JLabel("Points : ");
		panel5.add(points, BorderLayout.CENTER);
 		segments = new JLabel("Segments : ");
 		panel5.add(segments);
 		faces = new JLabel("Faces : ");
 		panel5.add(faces);
 		des = new JLabel("Description : ");
 		
 		recherche = new JTextField();
 		panel3.add(recherche, BorderLayout.WEST);
 		recherche.setPreferredSize(new Dimension(200, 25));
 		JButton find = new JButton("Recherche");
 		panel3.add(find, BorderLayout.EAST);
 		
 		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
			String find = recherche.getText();
			String[] tab = find.split(", ");
			models = bdd.recherche(tab);
			
			frame.validate();
			}
 		};

 		des.setPreferredSize(new Dimension(40, 40));
 		panel5.add(des);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  JList list = ((JList)listSelectionEvent.getSource());
		    	  String select = (String)list.getSelectedValue();
		    	  points.setText("Points : " + models.get(select).get("points"));
		    	  segments.setText("Segments : " + models.get(select).get("segments"));
		    	  faces.setText("Faces : " + models.get(select).get("faces"));
		    	  des.setText("Description : " + models.get(select).get("des"));
		  		  String [] tab = new String [models.size()];
		  		  int i = 0;
				  for(String mapKey : models.keySet()){
					tab[i] = models.get(mapKey).get("nom");
					i ++;
		  		  }
  				  frame.validate();
		      }
		    };
		list.addListSelectionListener(listSelectionListener);
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}
	