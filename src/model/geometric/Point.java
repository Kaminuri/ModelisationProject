package model.geometric;

import java.util.Comparator;


public class Point implements Comparable<Point>,Comparator<Point>{
	private double x, y , z;
	public Point(double x, double y, double z){
		this.x = x;
		this.y=y;
		this.z = z;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}

	public double getZ(){
		return z;
	}
	public String toString(){
		return "[" +x+ "/" +y+"/" +z +"]";
	}
	public boolean equals(Point p){
		return x == p.x && y == p.y && z == p.z;
	}
	public double scalarProduct(Point p){
		return x*p.x+y*p.y+z*p.z;
	}

	public void multiply(int m){
		x*=m;
		y*=m;
		z*=m;
	}
	@Override
	public int compareTo(Point p) {
		if(p.getZ()<this.getZ()){
			return 1;
		}
		else if(p.getZ()>this.getZ()){
			return -1;
		}
		else if(p.getY() < this.getY()){
			return 1;
		}
		else if(p.getY() > this.getY()){
			return -1;
		}
		return 0;
	}
	@Override
	public int compare(Point p1, Point p2) {
		if(p1.getZ()<p2.getZ()){
			return 1;
		}
		else if(p1.getZ()>p2.getZ()){
			return -1;
		}
		else if(p1.getY() < p2.getY()){
			return 1;
		}
		else if(p1.getY() > p2.getY()){
			return -1;
		}
		return 0;
	}
}
