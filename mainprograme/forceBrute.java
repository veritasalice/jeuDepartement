package mainprograme;




import java.util.ArrayList;

import java.lang.String;

public class forceBrute {

	ArrayList<Point> pointsToVisit = new ArrayList<Point>();
	ArrayList<String> permutations = new ArrayList<String>();
	ArrayList<ArrayList<Float>> distances = new ArrayList<ArrayList<Float>>();
	private float meilleureDistance = Float.MAX_VALUE;
	private String meilleurePermutation;
	private ArrayList<Point> meilleurChemin = new ArrayList<Point>();

	public forceBrute(ArrayList<Point> pointsToVisit) {
		this.pointsToVisit = pointsToVisit;
		this.initierDistances();
		this.getPermutations("", this.permutationOrigine());
		this.getBestDistance();
		
		//get tous les sequence
		for (int i = 0; i < this.meilleurePermutation.length(); i++) {
			int pointToAdd = Integer.parseInt(Character.toString(this.meilleurePermutation.charAt(i)));
			//System.out.println(pointToAdd);
			this.meilleurChemin.add(this.pointsToVisit.get(pointToAdd));
		}

		//System.out.println(this.meilleurChemin.get(0).nom);
		//////System.out.println(this.meilleurePermutation);
	}
	
	
	private void initierDistances() {
		for (int i = 0; i < this.pointsToVisit.size(); i++) {
			ArrayList<Float> functionTemp = new ArrayList<Float>();
			for (int j = 0; j < this.pointsToVisit.size(); j++) {
				functionTemp.add(pointsToVisit.get(i).calcDist(pointsToVisit.get(j)));
			} this.distances.add(functionTemp);
		}
	}
	
	private String permutationOrigine() {
		String baseString = "";
		for (int i = 0; i < this.pointsToVisit.size(); i++) {
			baseString += i;
		}
		return baseString;
	}
	
	private void getPermutations(String startString, String origine) {
		if (origine.length() == 0) {
			this.permutations.add(startString);
		} else {
			for (int i = 0; i < origine.length(); i++) {
				this.getPermutations(startString + origine.charAt(i), origine.substring(0, i) + origine.substring(i+1));
			}
		}
	}

	private void getBestDistance() {
		for (int i = 0; i < this.permutations.size(); i++) {
			float tempDistance = (float) 0;
			for (int j = 0; j < this.permutations.get(i).length(); j++) {
				int origin = Integer.parseInt(Character.toString(this.permutations.get(i).charAt(j)));
				int destination = Integer.parseInt(Character.toString(this.permutations.get(i).charAt((j+1)%this.permutations.get(i).length())));
				tempDistance += (this.distances.get(origin).get(destination));
			}	
			//System.out.println(tempDistance);
			 if (tempDistance < this.meilleureDistance) {
				this.meilleureDistance = tempDistance;
				this.meilleurePermutation = this.permutations.get(i);
			 }
		    			
		}
	}
	
	public ArrayList<Point> getMeilleurChemin() {
		return this.meilleurChemin;
	}

}
