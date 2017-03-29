import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
	public static void main(String[] args) {
		final int threadCount = 6;
		final ExecutorService exService = Executors.newFixedThreadPool(threadCount);
		final AskQuestion askQuestion = new AskQuestion();
		for(int i=0; i < threadCount; i++) {
			Students student = new Students(askQuestion);
			exService.execute(student);
		}
		exService.shutdown();
	}
}
