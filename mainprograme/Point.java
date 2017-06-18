package mainprograme;
import java.lang.Math;


public class Point {
	public float x;
	public float y;
	public static float  R = 6371;//km
	private Point p;
	int numville;
	String nom;

	
	/*
	       Construit le point
	       lat coordonnée en x
	       lon coordonnee en y
	 */
	public Point(float lat,float lon,String nom){
		this.x = lat;
		this.y = lon; 
		this.nom = nom;
	}


	//calcul la distance
	public float calcDist(Point p){
		return Math.abs((float) (R*(Math.sin(p.getX())*Math.sin(this.getX()) + Math.cos(p.getX())*Math.cos(this.getX())*Math.cos(p.getY()-this.getY()))));
	}
	//calcul la distance static
	public static float calcDist(Point p1,Point p2){
		
		return Math.abs((float)(R*(Math.sin(p1.getX())*Math.sin(p2.getX()) + Math.cos(p1.getX())*Math.cos(p2.getX())*Math.cos(p1.getY()-p2.getY()))));
	}



	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}



	public int size() {		
		return this.numville;
	}

	public Point get(int i) {
		return this.p;
	}


	public void addString(String string) {
		this.addString(string);
	}


	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getnom(){
		
		return this.nom;
	}



}
