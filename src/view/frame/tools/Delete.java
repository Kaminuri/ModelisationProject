package view.frame.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

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

public class Delete extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected Base bdd;
	protected JPanel panel, panel2, panel3, panel4, panel5;
	protected JLabel points, segments, faces; 
	protected JLabel des;
	protected JTextField recherche;
	@SuppressWarnings("rawtypes")
	protected JList list;
	protected HashMap<String, HashMap<String, String>> models;

	@SuppressWarnings("rawtypes")
	public Delete(){
		
		frame = this;
		
		frame.setResizable(false);
		panel = new JPanel(false);
		panel2 = new JPanel(false);
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel(null);
    	GridLayout grid = new GridLayout(4 ,1);

    	Container contentPane = frame.getContentPane();
    	frame.setPreferredSize(new Dimension(500, 300));
		panel.setPreferredSize(new Dimension(110,300));
    	panel2.setPreferredSize(new Dimension(380, 300));
    	panel3.setPreferredSize(new Dimension(380, 50));
    	panel4.setPreferredSize(new Dimension(380, 50));
    	panel5.setPreferredSize(new Dimension(380, 200));
    	
    	panel2.setLayout(new BorderLayout());
    	panel2.add(panel3, BorderLayout.NORTH);
    	panel2.add(panel5, BorderLayout.CENTER);
    	panel2.add(panel4, BorderLayout.SOUTH);
    	
    	panel5.setLayout(grid);
		
		bdd = new Base("Base.db");
		list = new JList();
		modifieList("select", null);
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setPreferredSize(new Dimension(100, 250));
		panel.add(scrollPane1);
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(panel2, BorderLayout.EAST);
		
		points = new JLabel("Points : ");
		segments = new JLabel("Segments : ");
		faces = new JLabel("Faces : ");
		des = new JLabel("Description : ");
		
		panel5.add(points, BorderLayout.CENTER);
 		panel5.add(segments);
 		panel5.add(faces);
 		panel5.add(des);
 		
 		recherche = new JTextField();
 		panel3.add(recherche, BorderLayout.WEST);
 		recherche.setPreferredSize(new Dimension(200, 25));
 		JButton find = new JButton("Search");
 		panel3.add(find, BorderLayout.EAST);
 		
 		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
			String find = recherche.getText();
			modifieList("recherche", find);
			frame.validate();
			}
 		};
 		find.addActionListener(listener);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  String select = (String)list.getSelectedValue();
		    	  points.setText("Points : " + models.get(select).get("points"));
		    	  segments.setText("Segments : " + models.get(select).get("segments"));
		    	  faces.setText("Faces : " + models.get(select).get("faces"));
		    	  des.setText("Description : " + models.get(select).get("Description"));
		    	  frame.validate();
		      }
		    };
		list.addListSelectionListener(listSelectionListener);
		
		JButton delete = new JButton("Delete");

		
		ActionListener listenerDelete = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
		    String select = (String)list.getSelectedValue();
		    if(select == null){
		    	
		    }
		    else{
		    	String filename = models.get(select).get("adresses");
		    	bdd.delete(select);
				models = bdd.select();
				frame.dispose();
				File file = new File(filename);
				file.delete();				
		    }
		    
			}
 		};
		delete.addActionListener(listenerDelete);
		
		panel4.add(delete);
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void modifieList(String methode, String find){
		if(methode.equals("select")){
			models = bdd.select();
		}
		else if(methode.equals("recherche")){
			String[] tab2 = find.split(", ");
			models = bdd.recherche(tab2);
		}
		int i = 0;
		String [] tab = new String [models.size()];
		for(String mapKey : models.keySet()){
			tab[i] = models.get(mapKey).get("nom");
			i ++;
		}
		list.setListData(tab);
	}
}
	