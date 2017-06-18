package mainprograme;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Question {

	/*
	 * La variable question contient l'enonce de la question qui sera affichee
	 * dans la console
	 */
	private String question = "";
	/*
	 * La variable reponse contient la reponse a laquelle la reponse tapee dans
	 * la console sera comparee
	 */
	private String reponse = "";
	/*
	 * La variable elementsQuestion contiendra la ligne tiree du fichier
	 * Liste_Question qui servira a construire la question
	 */
	private String elementsQuestion = "";
	/*
	 * La variable elementsReponse contiendra la ligne tiree du fichier
	 * Liste_Departements qui servira a construire la reponse
	 */
	private String elementsReponse = "";
	/*
	 * La variable fichierQuestions contiendra l'adresse du fichier 
	 * Liste_Questions
	 */
	private String fichierQuestions = "";
	/*
	 * La variable fichierReponse contiendra l'adresse du fichier 
	 * Liste_Departements�
	 */
	private String fichierReponses = "";
	/*
	 * La variable nombreDeQuestions contiendra le nombre de lignes du fichier
	 * Liste_Questions
	 */
	private int nombreDeQuestions = 0;
	/*
	 * La variable nombreDeReponses contiendra le nombre de lignes du fichier
	 * Liste_Departements
	 */
	private int nombreDeReponses = 0;
	private String showcheflieu = "";
	
	/*
	 * Constructeur de la classe Question, prenant en entree le path du fichier
	 * Liste_Departements et le path du fichier Liste_Questions
	 */
	public Question(String departements, String questions) 
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		// On met a jour les attributs contenant les paths
		this.fichierQuestions = questions;
		this.fichierReponses = departements;
		
		// On cree les flux pour lire les deux fichiers de BdD
        File fQuestions = new File(this.fichierQuestions);   
        File fReponses = new File(this.fichierReponses);
        BufferedReader brq = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(fQuestions), "UTF-8")); 
        BufferedReader brr = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(fReponses), "UTF-8"));
        
        // Tant qu'il y a encore des lignes non vides, on incremente les nombres
        // de reponses et de questions
        String lQuestions = brq.readLine();
        String lReponses = brr.readLine();
        
        while (lQuestions != null) {
        	this.nombreDeQuestions++;
        	lQuestions = brq.readLine();
        }
        
        while (lReponses != null) {
        	this.nombreDeReponses++;
        	lReponses = brr.readLine();
        }
        
        // On ferme les flux pour eviter les fuites de memoire
        brq.close();
        brr.close();
        
	}
	
	public void setQuestionEtReponse() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		// On met a jour les attributs contenant les elements de la question et
		// de la reponse
		this.getElementsQuestion();
		this.getElementsReponse();
		
		// Les elements sont separes par des virgules dans la BdD, on les
		// separe au niveau de la virgule 
		String[] eltQuestion = this.elementsQuestion.split(",");
		String[] eltReponse = this.elementsReponse.split(",");
		
		this.question = eltQuestion[0] + " ";
		
		String finQuestion = eltQuestion[1];
		if (finQuestion.equals("numéro")) {
			this.question += eltReponse[0];
		} else if (finQuestion.equals("nom")) {
			this.question += eltReponse[1];
		} else if (finQuestion.equals("cheflieu")) {
			this.question += eltReponse[2];
		}
		this.question += " ?";
		
		this.reponse = "";
		for (int i = 2; i < eltQuestion.length; i++) {
			if (eltQuestion[i].equals("numéro")) {
				this.reponse += eltReponse[0] + ",";
			} else if (eltQuestion[i].equals("nom")) {
				this.reponse += eltReponse[1] + ",";
			} else if (eltQuestion[i].equals("cheflieu")) {
				this.reponse += eltReponse[2] + ",";
			}
		}
		this.reponse = this.reponse.substring(0, this.reponse.length() - 1);
		this.showcheflieu = eltReponse[2];
		
	}
	
	private void getElementsQuestion() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		int numeroLigne = 1 + (int)(Math.random() * ((nombreDeQuestions - 1) + 1));		

		File fQuestions = new File(this.fichierQuestions);   
        BufferedReader brq = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(fQuestions), "UTF-8")); 
        
        String line = brq.readLine();
        int compteur = 1;

        while (compteur < numeroLigne) {
        	line = brq.readLine();
        	compteur++;
        }
        
        this.elementsQuestion = line;
        
        brq.close();
	}
	
	private void getElementsReponse() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		int numeroLigne = 1 + (int)(Math.random() * ((nombreDeReponses - 1) + 1));		

		File fQuestions = new File(this.fichierReponses);   
        BufferedReader brr = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(fQuestions), "UTF-8")); 
        
        String line = brr.readLine();
        int compteur = 1;

        while (compteur < numeroLigne) {
        	line = brr.readLine();
        	compteur++;
        }
        
        this.elementsReponse = line;
        
        brr.close();
	}
	
	public String getQuestion() {
		System.out.println("========" + this.reponse + "========");
		return this.question;
	}
	
	public boolean getReponse(String reponse) {
		return reponse.equals(this.reponse);
	}
	
	public String getChefLieu() {
		return this.showcheflieu;
	}
	
}
