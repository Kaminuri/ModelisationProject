import view.frame.Interface;
import model.geometric.Item3D;
import model.loader.FileParser;
import controller.geometric.GeometricController;

public class Modelisation {
	
	/**
	 * Cree un objet modelisation, contenant un item3D, un GeometricController et une interface
	 */
	public Modelisation(){
		
		Item3D item=null;
		try{
			item = new Item3D(new FileParser("src/resources/models/.gts"));
		}catch(Exception e){
			
		}
		
		GeometricController gc = new GeometricController(item);
		new Interface(gc, item);
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          new Modelisation();
	        }
	    });
	}
}
