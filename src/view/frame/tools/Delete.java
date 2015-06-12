package view.frame.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Delete extends Panel{
	
	private static final long serialVersionUID = 1L;


	public Delete(){
		super();
		JButton delete = new JButton("Delete");

		ActionListener listenerDelete = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
		    String select = (String)list.getSelectedValue();
		    if(select == null){
		    	JOptionPane.showMessageDialog(new JFrame(), "Vous n'avez selectionner aucun element");
		    }
		    else{
		    	String filename = models.get(select).get("adresse");
		    	bdd.delete(select);
				models = bdd.select();
				dispose();
				File file = new File(filename);
				file.delete();
				JOptionPane.showMessageDialog(new JFrame(), "La Supression a ete effectuee avec succes");
		    }
		    
			}
 		};
		delete.addActionListener(listenerDelete);
		
		panel4.add(delete);
		pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
}
	