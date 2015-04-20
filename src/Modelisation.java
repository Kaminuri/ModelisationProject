import view.frame.Interface;
import model.geometric.Item3D;
import model.loader.FileParser;
import controller.geometric.GeometricController;

public class Modelisation {
	public Modelisation(){
		GeometricController gc = new GeometricController();
		Item3D item=null;
		try{
			item = new Item3D(new FileParser());
		}catch(Exception e){
			
		}
		Interface inter = new Interface(gc, item);
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          new Modelisation();
	        }
	    });
	}
}