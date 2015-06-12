package view.frame.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import exceptions.ExceptionFace;
import exceptions.ExceptionPoint;
import exceptions.ExceptionSegment;
import model.geometric.Item3D;
import model.loader.FileParser;

@SuppressWarnings("serial")
public class Open extends Panel{
	protected Item3D item;

	public Open(Item3D i){
		super();
		this.item = i;

		JButton open = new JButton("Open");
		panel4.add(open);
		
		ActionListener listenerOpen = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg) {
		    String select = (String)list.getSelectedValue();
		    if(select == null){
		    	
		    }
		    else{
		    	dispose();
		    	String filename = models.get(select).get("adresse");
		    	try {
					item.recreateItem(new FileParser(filename));
				} catch (ExceptionPoint | ExceptionSegment | ExceptionFace e) {
					
					e.printStackTrace();
				}
		    }
		    	
			}
 		};
		open.addActionListener(listenerOpen);
		
		pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
}
	