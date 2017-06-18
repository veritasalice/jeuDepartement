package mainprograme;

import java.util.ArrayList;
import java.util.Random;

public class Glouton {

	ArrayList<Point> pointsToVisit = new ArrayList<Point>();
	ArrayList<Point> pointsToVisitStatic = new ArrayList<Point>();
	ArrayList<ArrayList<Float>> distances = new ArrayList<ArrayList<Float>>();
	private ArrayList<Point> chemin = new ArrayList<Point>();
	
	public Glouton(ArrayList<Point> pointsToVisit) {
		
		pointsToVisitStatic = pointsToVisit;
		this.pointsToVisit = pointsToVisit;
		
		ArrayList<Integer> index = new ArrayList<Integer>();
		ArrayList<Integer> indexChemin = new ArrayList<Integer>();
		for (int i = 0; i < this.pointsToVisit.size(); i++) {
			index.add(i);
			
		}//initialisation du index 0123...
		this.initierDistances();
		
		Random rand = new Random();
		int debut = rand.nextInt(index.size());
		
		//System.out.println(debut);
		
		this.chemin.add(this.pointsToVisit.get(debut));
		indexChemin.add(index.get(debut));
		//this.pointsToVisit.remove(debut);
		index.remove(debut);
		
		//remember a verifier ici!
		for(int i = 0;i < this.pointsToVisitStatic.size()-1;i++){//while (!this.pointsToVisit.isEmpty()) {
			int nouveauPoint = this.getPointPlusProche(indexChemin.get(indexChemin.size() - 1), index);
			
			//System.out.println(nouveauPoint);
			
			this.chemin.add(pointsToVisitStatic.get(nouveauPoint));//prob
			indexChemin.add(nouveauPoint);//index.get(
			//this.pointsToVisit.remove(this.pointsToVisit.indexOf(nouveauPoint));
			index.remove(index.indexOf(nouveauPoint));			
		}
		//System.out.println(indexChemin);
		//System.out.println(index);
	}
	
	private void initierDistances() {
		for (int i = 0; i < this.pointsToVisit.size(); i++) {
			ArrayList<Float> functionTemp = new ArrayList<Float>();
			for (int j = 0; j < this.pointsToVisit.size(); j++) {
				functionTemp.add(pointsToVisit.get(i).calcDist(pointsToVisit.get(j)));
			} this.distances.add(functionTemp);
		}
	}
	
	private int getPointPlusProche(int index, ArrayList<Integer> indexAVisiter) {
		float dist = Float.MAX_VALUE;
		int indexMin = -1;
		for (Integer i : indexAVisiter) {
			float tempDist = this.distances.get(index).get(i);
				if (tempDist < dist) {
					dist = tempDist;
					indexMin = i;
				}
		}
		return indexMin;
	}
	
	public ArrayList<Point> getChemin() {
		return this.chemin;
	}
	
}
