
public class Students implements Runnable{
	private AskQuestion askQuestion;
	private Professor professor;
	private Students student;
	private String studentName;
	public Students(AskQuestion askQuestion){
		this.askQuestion = askQuestion;
	}
	public Students(String student){
		this.studentName = student;
	}
	public void questionDone(){
		System.out.println(getStudent() + " has known the answer.");
	}
	public void questionStart(){
		System.out.println(getStudent() + " is asking question.");
	}
	public String getStudent(){
		return studentName;
	}
	public void run(){
		try{
			Professor professor = (Professor) askQuestion.professorAnsweringQuestion();
			Students student = (Students) askQuestion.studentAskingQuestion();
			professor.professorWake();
			student.questionStart();
			professor.answerStart();
			professor.answerDone();
			student.questionDone();
			professor.questionEnd();
			askQuestion.professorNap(professor);

		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
