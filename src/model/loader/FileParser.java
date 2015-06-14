package model.loader;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.geometric.Face;
import model.geometric.Point;
import model.geometric.Segment;
import exceptions.ExceptionFace;
import exceptions.ExceptionPoint;
import exceptions.ExceptionSegment;


public class FileParser {
	//private String fileName = "cube.gts";
	//private String fileName = "sphere5.gts";
	//private String fileName = "x_wing.gts";
	//private String fileName = "poney.gts";
	//private String fileName = "horse.gts";
	//private String fileName = "space_shuttle.gts";
	
	private int nbPoint, nbSeg, nbFace;
	private Double x, y, z;
	private Point p1,p2;
	private Segment s1,s2,s3;
	private ArrayList<Point> p = new ArrayList<Point>();
	private ArrayList<Segment> s = new ArrayList<Segment>();
	private ArrayList<Face> f = new ArrayList<Face>();

	
	/**
	 * Verifie que le fichier en lecture existe et est conforme
	 * @throws ExceptionPoint si il y a une probleme avec les points
	 * @throws ExceptionSegment si il y a une probleme avec les segments
	 * @throws ExceptionFace si il y a une probleme avec les faces
	 */
	public FileParser(String filename) throws ExceptionPoint, ExceptionSegment, ExceptionFace {
		String sta;
		String currnt;
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			sta = br.readLine();
			try{
			initNbs(sta);
			}
			catch(Exception e){
				System.out.println("error");
			}
			for(int i = 0 ; i < nbPoint ; i++) {
				currnt = br.readLine();
				this.x = null;
				this.y = null;
				this.z = null;
				initPoints(currnt);
				if(x == null || y == null || z == null) {
					throw new ExceptionPoint();
				}
				p.add(new Point(x,y,z));
			}
			for(int j = 0 ; j < nbSeg; j++) {	
				currnt = br.readLine();
				this.p1 = null;
				this.p2 = null;
				initSegs(currnt);
				if(p.contains(p1) != true || p.contains(p2) != true) {
					throw new ExceptionSegment();
				}
				s.add(new Segment(p1,p2));
			}
			for(int k = 0 ; k < nbFace ; k++) {
				currnt = br.readLine();
				this.s1 = null;
				this.s2 = null;
				this.s3 = null;
				initFace(currnt);
				if(s.contains(s1) != true || s.contains(s2) != true || s.contains(s3) != true) {
					throw new ExceptionFace();
				}
				f.add(new Face(s1,s2));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Determine le nombre de points,de segments et de faces du modele
	 * @param sta String , premiere ligne du bufferReader
	 */
	private void initNbs(String sta) throws NullPointerException {
		String words[] = sta.split(" ");
		nbPoint = Integer.parseInt(words[0]);
		nbSeg = Integer.parseInt(words[1]);
		nbFace = Integer.parseInt(words[2]);
	}
	
	/**
	 * Determine les points de la figure
	 * @param currnt la ligne actuelle du bufferReader
	 */
	private void initPoints(String currnt) {
		String words[] = currnt.split(" ");
		x = Double.parseDouble(words[0]);
		y = Double.parseDouble(words[1]);
		z = Double.parseDouble(words[2]);
	}
	
	/**
	 * Determine les segments de la figure
	 * @param currnt la ligne actuelle du bufferReader
	 */
	private void initSegs(String currnt) {
		String words[] = currnt.split(" ");
		p1 = p.get(Integer.parseInt(words[0])-1);
		p2 = p.get(Integer.parseInt(words[1])-1);
	}
	
	/**
	 * Determine les faces de la figure
	 * @param currnt la ligne actuelle du bufferReader
	 */
	private void initFace(String currnt) {
		String words[] = currnt.split(" ");
		s1 = s.get(Integer.parseInt(words[0])-1);
		s2 = s.get(Integer.parseInt(words[1])-1);
		s3 = s.get(Integer.parseInt(words[2])-1);
	}
	
	/**
	 * Renvoie la liste des points de la figure
	 * @return ArrayList des points de la figure
	 */
	public ArrayList<Point> getListPoints(){
		return p;
	}

	/**
	 * Renvoie la liste des segments de la figure
	 * @return ArrayList des segments de la figure
	 */
	public ArrayList<Segment> getListSegments(){
		return s;
	}
	
	/**
	 * Renvoie la liste des faces de la figure
	 * @return ArrayList des faces de la figure
	 */
	public ArrayList<Face> getListFaces(){
		return f;
	}
	
}
