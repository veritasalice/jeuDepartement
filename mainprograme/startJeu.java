package mainprograme;

import java.awt.BorderLayout;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class startJeu {



	public static void main(String[] args) throws IOException {
		 
		String departements = "./src/Liste_Departements.txt";
		String questions = "./src/Liste_Questions.txt";

		jeuDevinettes devinettes = null;

		try {
			devinettes = new jeuDevinettes(departements, questions);		
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);

		System.out.println("Combien d'essais par question ?"
				+ " (Entrer un nombre entier)");
		int nbEssais = scanner.nextInt();

		System.out.println("Combien de rounds ? (Entrer un nombre entier)");
		int nbRound = scanner.nextInt();

		boolean test = false;
		ArrayList<Point> pointsAVisiter = new ArrayList<Point>();

		for(int i = 0; i < nbRound; i++) {

			
			Scanner jeuScan = new Scanner(System.in);

			try {
				devinettes.poserQuestion();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int j = 1; j <= nbEssais ; j++) {

				test = devinettes.verifierReponse(jeuScan);

				if (test) {
					System.out.println("Bonne réponse, round " 
							+ (i + 1) + " gagné !\n");

					//display cheflieu on Google map					
					System.out.println("Voilà le cheflieu "+devinettes.getChefLieu()+" sur Google Map");

					GoogleResponse res = new AddressConverter().convertToLatLong(devinettes.getChefLieu()+"région France");//cheflieu
					if(res.getStatus().equals("OK"))
					{
						for(Result result : res.getResults()) 
						{
							System.out.println("Lattitude of address is :" + result.getGeometry().getLocation().getLat());
							System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());

							String latitude = result.getGeometry().getLocation().getLat();
							String longitude = result.getGeometry().getLocation().getLng();

							Point point = new Point(Float.parseFloat(latitude), Float.parseFloat(longitude),devinettes.getChefLieu());
							pointsAVisiter.add(point);

							JGoogleMapEditorPan googleMap = new JGoogleMapEditorPan();
							try {
								googleMap.setApiKey("AIzaSyCHnHCVwv8_tcXjg_I0pJzoI4NHwnCg79M");

								//Afficher le cheflieu en fonction ses coordonnées             
								googleMap.showCoordinate(latitude, longitude, 400, 400);

							} catch (Exception ex) {
								Logger.getLogger(JGoogleMapEditorPan.class.getName()).log(Level.SEVERE, null, ex);
							}

							JFrame frame = new JFrame();
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame.add(googleMap);
							frame.setSize(400, 420);
							frame.setLocation(200, 200);
							frame.setVisible(true);
						}
					}
					else
					{
						System.out.println(res.getStatus());
					}

					System.out.println("\n");

					break;
				} else {
					if (j < nbEssais) {
						System.out.println("Mauvaise réponse, essayez encore.");
					} else {
						System.out.println("Mauvaise réponse, round " 
								+ (i + 1) + " perdu !\n");

						System.out.println("Voilà le cheflieu "+devinettes.getChefLieu()+" sur Google Map");

						GoogleResponse res = new AddressConverter().convertToLatLong(devinettes.getChefLieu()+"région France");//cheflieu
						if(res.getStatus().equals("OK"))
						{
							for(Result result : res.getResults()) 
							{
								System.out.println("Lattitude of address is :" + result.getGeometry().getLocation().getLat());
								System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());

								String latitude = result.getGeometry().getLocation().getLat();
								String longitude = result.getGeometry().getLocation().getLng();

								Point point = new Point(Float.parseFloat(latitude), Float.parseFloat(longitude),devinettes.getChefLieu());
								pointsAVisiter.add(point);

								JGoogleMapEditorPan googleMap = new JGoogleMapEditorPan();
								try {
									googleMap.setApiKey("AIzaSyCHnHCVwv8_tcXjg_I0pJzoI4NHwnCg79M");

									//Afficher le cheflieu en fonction ses coordonnées             
									googleMap.showCoordinate(latitude, longitude, 400, 400);

								} catch (Exception ex) {
									Logger.getLogger(JGoogleMapEditorPan.class.getName()).log(Level.SEVERE, null, ex);
								}


								JFrame frame = new JFrame();
								frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								frame.add(googleMap);
								frame.setSize(400, 420);
								frame.setLocation(200, 200);
								frame.setVisible(true);


							}
						}
						break;
					}
				}

			}
	
		
		//System.out.println("\n========= La partie est terminée ========");
	
			
		}
		//========= affichage de l'itinéraire avec  PointsAVisiter
		
		
		
		
		// a changer
		System.out.println("pointsAVisiter:");
		for(int i=0; i < pointsAVisiter.size();i++){
			 String iniroute = pointsAVisiter.get(i).getnom();
				System.out.print(iniroute+" -> ");	
			}
		System.out.println();
		
		System.out.println("chemin_fb:");
		forceBrute fb = new forceBrute(pointsAVisiter);		
		ArrayList<Point> chemin_fb = fb.getMeilleurChemin();
		for(int i=0;i<chemin_fb.size();i++){
			 String route = chemin_fb.get(i).getnom();
				System.out.print(route+" -> ");	
			}
		System.out.println();
	
		System.out.println("chemin_gl:");
		Glouton glouton = new Glouton(pointsAVisiter);
		ArrayList<Point> chemin_gl = glouton.getChemin();
		for(int i=0;i<chemin_gl.size();i++){
			 String route = chemin_gl.get(i).getnom();
				System.out.print(route+" -> ");	
			}
		System.out.println();
			
			
				JGoogleMapEditorPan googleMapfb = new JGoogleMapEditorPan();
				JGoogleMapEditorPan googleMapgl = new JGoogleMapEditorPan();
				
				try {
					googleMapfb.setApiKey("AIzaSyCHnHCVwv8_tcXjg_I0pJzoI4NHwnCg79M");					       				
					googleMapfb.showRoute(1000,1000,chemin_fb,"red");
					
					
					googleMapgl.setApiKey("AIzaSyCHnHCVwv8_tcXjg_I0pJzoI4NHwnCg79M");
					googleMapgl.showRoute(1000,1000,chemin_gl,"blue");
					
				} catch (Exception ex) {
					Logger.getLogger(JGoogleMapEditorPan.class.getName()).log(Level.SEVERE, null, ex);
				}

				
				
				/** GUI a faire: 
				 * JTextField pour get reponse
				 * affichage chaque ville dans la meme frame
				 *  deux itinéraire en meme frame
				**/
				
				JFrame frame = new JFrame("Decouvrir la France");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   
				Container contentPane = frame.getContentPane();  
		        
		        		        
			    JPanel BruteForce = new JPanel();
			    contentPane.add(BruteForce, BorderLayout.WEST);
			    BruteForce.add(googleMapfb);
			    
			    
		        JPanel Glouton = new JPanel();
		        contentPane.add(Glouton, BorderLayout.EAST);
		        Glouton.add(googleMapgl);
		        
			
				frame.setSize(1400, 680);
				frame.setLocation(10, 10);
				frame.setVisible(true);
	
				
			
				
				scanner.close();
	}

}

