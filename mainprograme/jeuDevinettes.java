package mainprograme;
import java.io.IOException;
import java.util.Scanner;

public class jeuDevinettes {
		
	Question question = null;
	
	
	public jeuDevinettes(String departements, String questions) 
			throws IOException {
		this.question = new Question(departements, questions);
		
	}
	
	public void poserQuestion() throws IOException {
		this.question.setQuestionEtReponse();
		System.out.println(this.question.getQuestion());		
	}	

	public boolean verifierReponse(Scanner scanner) {		
		System.out.println("Quelle est la réponse ? "
				+ "(Si besoin, séparer les réponses par une virgule)");
		
		boolean test = this.question.getReponse(scanner.nextLine());
		
		return test;
	}
	
	public String getChefLieu() {
		return this.question.getChefLieu();
	}
	 
}
