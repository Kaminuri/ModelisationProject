package controller.geometric;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import model.geometric.Item3D;
import model.geometric.Point;

public class GeometricController {
	private Item3D it;
	private boolean ok;

	/**
	 * Cree un GeometricController, permettant d'effectuer les calculs sur le modele
	 * @param item Le modele sur lequel porteront les calculs
	 */
	public GeometricController(Item3D item){
		it = item;
		
	}

	public MouseWheelListener getZoomListener() {
		return new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() < 0){
					it.increaseZoomX();
		            it.increaseZoomY();
				}else if(e.getWheelRotation() > 0){
					it.decreaseZoomX();
					it.decreaseZoomY();
				}
			}
		
		};
	}
	public MouseListener getTranslationListener(){
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			
				if(e.getButton() == MouseEvent.BUTTON1){
					ok =true;
				}else if(e.getButton() == MouseEvent.BUTTON3){
					ok = false;
				}
				it.setPosXY(e.getX(), e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	public MouseMotionListener getTransRotaListener() {
		return new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!ok){
					double x1 = e.getX();
			        double y1 = e.getY();
			        double distanceX = x1 - it.getPosXItem();
			        double distanceY = y1 - it.getPosYItem();
			        if (distanceX >= 1 || distanceX <= -1 || distanceY >= 1 || distanceY <= -1) {
			            for (Point p : it.getPoints()) {
			                double x2 = p.getX() + (distanceX / 450);
			                double y2 = p.getY() + (distanceY / 450);
			                p.setX(x2);
			                p.setY(y2);
			                it.setPosXY(x1, x2);
			            }
			        }
			        ok = false;
				}else if(ok){
			        double x2 = e.getX();
			        double y2 = e.getY();
			        double angleY = x2 - it.getPosXItem();
			        double angleX = y2 - it.getPosYItem();
			        double px, py;
			        //barycenter = this.barycenter();
			        if (angleX >= 1 || angleX <= -1 || angleY >= 1 || angleY <= -1) {
			            angleX *= -(2.0 * Math.PI) / 360.0;
			            angleY *= (2.0 * Math.PI) / 360.0;
			            it.rotationY(angleY, it.barycentre());
			            it.rotationX(angleX, it.barycentre());
			        }
			    }

			}
		};
	}

	public ActionListener getMinusZoomListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.decreaseZoomY();
				it.decreaseZoomX();			
				
			}
		};
	}

	public ActionListener getPlusZoomListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				it.increaseZoomX();
				it.increaseZoomY();
			}
		}; 
	}

	public ActionListener getTranslationHautListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				it.decreaseTransY();
			}
		};
	}

	public ActionListener getTranslationBasListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.increaseTransY();
			}
		};
	}

	public ActionListener getTranslationGaucheListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.decreaseTransX();
				
			}
		};
	}

	public ActionListener getTranslationDroiteListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.increaseTransX();
			}
		};
	}

	public ActionListener getRotationXListener() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.rotationX(Math.PI/32, null);
			}
		};
	}

	public ActionListener getRotationYListener() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.rotationY(Math.PI/32,null);
			}
		};
	}

	public ActionListener getRotationZListener() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.rotationZ(Math.PI/32);
			}
		};
	}
	
}
