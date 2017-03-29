import java.util.concurrent.Semaphore;


public class AskQuestion {
	 private static final int MAX_ProfessorNumber = 1;
	 private static final int MAX_StudentNumber = 6;
	 private final Semaphore askingProfessor = new Semaphore(MAX_ProfessorNumber, true);
	 private final Semaphore studentAsking = new Semaphore(MAX_StudentNumber, true);
	 private Professor[] professors = {new Professor("Simon")};
	 private Students[] students = {new Students("Student 1"), new Students("Student 2"), new Students("Student 3"), new Students("Student 4"), new Students("Student 5"),new Students("Student 6")}; 
	 private boolean[] beingAnswering = new boolean[MAX_ProfessorNumber];
	 private boolean[] beingAsking = new boolean[MAX_StudentNumber];
	 
	 //professor is preparing;
	 public Object professorAnsweringQuestion() throws InterruptedException{
		 askingProfessor.acquire();
		 return professorWait();
	 }
	 //student is coming to ask question;
	 public Object studentAskingQuestion() throws InterruptedException{
		 studentAsking.acquire();
		 return studentCome();

	 }
	 private synchronized Professor professorWait(){
		 Professor professor = null;
		 for(int i = 0; i < MAX_ProfessorNumber; i++){
			 if(!beingAnswering[i]){
				 beingAnswering[i] = true;
				 professor = professors[i];
				 System.out.println("Professor " + professor.getProfessor() + " takes a nap.");
				 break;
			 }
		 }
		 return professor;
	 }
	 
	 private synchronized Students studentCome(){
		 Students student = null;
		 for(int i = 0; i < MAX_StudentNumber; i++){
			 if(!beingAsking[i]){
				 beingAsking[i] = true;
				 student = students[i];
				 System.out.println(student.getStudent() + " is coming to ask question.");
				 break;
			 }
		 }
		 return student;
	 }
	 
	 //professor has answered question.
	 public void professorNap(Professor professor){
		 if(professorFree(professor)){ 
			 studentAsking.release();
			 askingProfessor.release();

		 }
	 }
	 private synchronized boolean professorFree(Professor professor){
		 boolean flag = false;
		 for(int i = 0; i <  MAX_ProfessorNumber; i++){
			 if(professor == professors[i]){
				 if(beingAnswering[i]){
					 beingAnswering[i] = false;
					 flag = true;
					 System.out.println("");
				 }
				 break;
			 }
		 }
		 return flag;
	 }

	 
	 
}
