
public class Professor {
	private String professorName;
	public Professor(String professorName){
		this.professorName = professorName;
	}
	public void professorWake(){
		System.out.println("Professor " + getProfessor() + " wakes up. ");
	}
	public void answerStart(){
		System.out.println("Professor " + getProfessor() + " is answering question. ");
		try{
			Thread.sleep(200);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public void answerDone(){
		System.out.println("Professor " + getProfessor() + " has answered question. ");
	}
	public void questionEnd(){
		System.out.println("Professor " + getProfessor() + " has a nap.");
	}
	public String getProfessor(){
		return professorName;
	}
	
	
}
