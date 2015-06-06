package view.frame.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	protected JPanel panel, panel2, panel3, panel4;
	protected JLabel points, segments, faces; 
	protected JLabel des;
	protected HashMap<String, HashMap<String, String>> models;
	public Open(){
		
		frame = this;
		frame.setPreferredSize(new Dimension(500, 300));
		panel = new JPanel(false);
		panel.setPreferredSize(new Dimension(110,300));
		panel2 = new JPanel(null);
		panel3 = new JPanel();
		panel4 = new JPanel();
			
    	GridLayout grid = new GridLayout(4 ,1);
    	panel2.setLocation(0, 0);
    	panel2.setPreferredSize(new Dimension(290, 300));
    	panel2.setLayout(grid);
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
		JList list = new JList(tab);
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setPreferredSize(new Dimension(100, 250));
		panel.add(scrollPane1);
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(panel2, BorderLayout.EAST);
		points = new JLabel("Points : ");
		panel2.add(points);
 		segments = new JLabel("Segments : ");
 		panel2.add(segments);
 		faces = new JLabel("Faces : ");
 		panel2.add(faces);
 		des = new JLabel("Description : ");

 		des.setPreferredSize(new Dimension(40, 40));
 		panel2.add(des);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  JList list = ((JList)listSelectionEvent.getSource());
		    	  String select = (String)list.getSelectedValue();
		    	  points.setText("Points : " + models.get(select).get("points"));
		    	  segments.setText("Segments : " + models.get(select).get("segments"));
		    	  faces.setText("Faces : " + models.get(select).get("faces"));
		    	  des.setText("<html>bla bla 1ère ligne <br> bla bla 2ème ligne</html>Description :sdhjfidshjigfhsdklhglksghnjlkfds,ngskosd,jklgj,fdlskglksjglksdjoijgkofkgsdfjgdgjpdsjgfk,dsjiodjiogfdjkiofsgiguodsifisuyfghduqhfuishgfidhghndsohgodshgojkhfdsjkogidohgiodiuoghigoiioughiogjois");
		  		  frame.validate();
		      }
		    };
		list.addListSelectionListener(listSelectionListener);
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}
	