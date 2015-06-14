package controller.geometric;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import model.geometric.Item3D;

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
	
	/**
	 * Recupere un MouseWheelListener pour le zoom sur l'item3D associe
	 * @return MouseWheelListener
	 */
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
	
	/**
	 * Recupere un MouseWheelListener pour la translation sur l'item3D associe
	 * @return MouseWheelListener
	 */
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
	
	/**
	 * Recupere un MouseWheelListener pour la  sur l'item3D associe
	 * @return MouseWheelListener
	 */
	public MouseMotionListener getTransRotaListener() {
		return new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(ok){
					it.translateByMouse(e);
			        ok = true;
				}else if(!ok){
			        it.rotateByMouse(e);
				}
			}
		};
	}
	
	/**
	 * Creation d'un listener pour le zoom arriere 
	 * @return ActionListener
	 */
	public ActionListener getMinusZoomListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.decreaseZoomY();
				it.decreaseZoomX();			
				
			}
		};
	}
	/**
	 * Creation d'un listener pour le zoom avant
	 * @return ActionListener
	 */
	public ActionListener getPlusZoomListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				it.increaseZoomX();
				it.increaseZoomY();
			}
		}; 
	}
	
	/**
	 * Creation d'un listener pour la translation vers le haut 
	 * @return ActionListener
	 */
	public ActionListener getTranslationHautListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				it.decreaseTransY();
			}
		};
	}

	/**
	 * Creation d'un listener pour la translation vers le bas
	 * @return ActionListener
	 */
	public ActionListener getTranslationBasListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.increaseTransY();
			}
		};
	}

	/**
	 * Creation d'un listener pour la translation vers la gauche 
	 * @return ActionListener
	 */
	public ActionListener getTranslationGaucheListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.decreaseTransX();
				
			}
		};
	}

	/**
	 * Creation d'un listener pour la translation vers la droite
	 * @return ActionListener
	 */
	public ActionListener getTranslationDroiteListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.increaseTransX();
			}
		};
	}

	/**
	 * Creation d'un listener pour la rotation sur X
	 * @return ActionListener
	 */
	public ActionListener getRotationXListener() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.rotationX(Math.PI/32);
			}
		};
	}

	/**
	 * Creation d'un listener pour la rotation sur Y
	 * @return ActionListener
	 */
	public ActionListener getRotationYListener() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				it.rotationY(Math.PI/32);
			}
		};
	}

	/**
	 * Creation d'un listener pour la rotation sur Z
	 * @return ActionListener
	 */
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
