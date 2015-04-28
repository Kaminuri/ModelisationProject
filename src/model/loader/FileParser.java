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
	private String fileName = "cube.gts";
	//private String fileName = "sphere20.gts";
	//private String fileName = "x_wing.gts";
	//private String fileName = "poney.gts";
	//private String fileName = "horse.gts";
	//private String fileName = "space_shuttle.gts";
	
	private int nbPoint, nbSeg, nbFace;
	private Double x, y , z;
	private Point p1,p2;
	private Segment s1,s2,s3;
	private ArrayList<Point> p = new ArrayList<Point>();
	private ArrayList<Segment> s = new ArrayList<Segment>();
	private ArrayList<Face> f = new ArrayList<Face>();

	public FileParser() throws ExceptionPoint, ExceptionSegment, ExceptionFace {
		p = new ArrayList<>();
		s = new ArrayList<>();
		f = new ArrayList<>();
		String sta;
		String currnt;
		try(BufferedReader br = new BufferedReader(new FileReader("src/resources/models/"+ fileName))){
			sta = br.readLine();
			initNbs(sta);
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
				f.add(new Face(s1,s2,s3));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	private void initNbs(String sta) {
		String words[] = sta.split(" ");
		nbPoint = Integer.parseInt(words[0]);
		nbSeg = Integer.parseInt(words[1]);
		nbFace = Integer.parseInt(words[2]);
	}
	
	private void initPoints(String currnt) {
		String words[] = currnt.split(" ");
		x = Double.parseDouble(words[0]);
		y = Double.parseDouble(words[1]);
		z = Double.parseDouble(words[2]);
	}
	
	private void initSegs(String currnt) {
		String words[] = currnt.split(" ");
		p1 = p.get(Integer.parseInt(words[0])-1);
		p2 = p.get(Integer.parseInt(words[1])-1);
	}
	
	private void initFace(String currnt) {
		String words[] = currnt.split(" ");
		s1 = s.get(Integer.parseInt(words[0])-1);
		s2 = s.get(Integer.parseInt(words[1])-1);
		s3 = s.get(Integer.parseInt(words[2])-1);
	}
	
	public ArrayList<Point> getListPoints(){
		return p;
	}
	public ArrayList<Segment> getListSegments(){
		return s;
	}
	public ArrayList<Face> getListFaces(){
		return f;
	}
}
