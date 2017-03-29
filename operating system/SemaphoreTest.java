import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
/*written by Honglan Liu(sp6682)*/
public class SemaphoreTest {
	private static char[] bufferTest;
	private static int n;// maximum number of bytes to copy in any given iteration
	private static int bufferSize;
	private static int i;// index of producer array
	private static int t;// index of input string
	private static String a;// input string
	private static int j;// index of consumer array
	private static int count;//track the number of item in buffer
	static Semaphore binary = new Semaphore(1);

	public static void main(String[] args) {
		final SemaphoreTest test = new SemaphoreTest();
		n = 5;//Integer.valueOf(args[2]);//if running program in commandline, use args[2]
		i = 0;
		j = 0;
		t = 0;
		a = null;
		count = 0;
		bufferSize = 10;//Integer.valueOf(args[3]);//if running program in commandline, use args[3]
		bufferTest = new char[bufferSize];
		while (true) {
			new Thread() {
				@Override
				public void run() {
					try {
						SemaphoreTest.producer();
					} catch (FileNotFoundException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();//create a producer thread 

			new Thread() {
				@Override
				public void run() {
					try {
						SemaphoreTest.consumer();
					} catch (InterruptedException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();//create a consumer thread
		}

	}

	private static void producer() throws InterruptedException,
			FileNotFoundException {
		binary.acquire();//critical section
		Scanner input = new Scanner(new File(
				"C:/Users/liuhonglan/Desktop/semaphore/input.txt"));
		if (input.hasNextLine()) {
			a = input.nextLine();
		}
		int maxCount = (int) (Math.random() * n + 1);// max count read in every time
		//read input.txt into buffer
		System.out.print("Producer: ");
			for (int k = 0; k < maxCount; k++) {
				if (count == bufferSize) {//if no buffer space, producer reads nothing
					System.out.println();
					input.close();
					binary.release();
					return;
				}
				bufferTest[i] = a.charAt(t);
				System.out.print(bufferTest[i]);
				i = (i + 1) % bufferSize;
				t = (t + 1) % a.length();
				count++;
			}
			System.out.println();
			input.close();
			binary.release();//exit critical section
			Thread.sleep(200);
			return;
		
	}

	private static void consumer() throws InterruptedException, IOException {
		binary.acquire();//enter critical section
		File file = new File("C:/Users/liuhonglan/Desktop/semaphore/output.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(
				"C:/Users/liuhonglan/Desktop/semaphore/output.txt", true);//no overlapping previous text
		PrintWriter output = new PrintWriter(fw);
		int maxCount = (int) (Math.random() * n + 1);
		System.out.print("Consumer: ");
		for (int z = 0; z < maxCount; z++) {
			if (count == 0) {//if no items to write, consumer reads nothing
				System.out.println();
				output.close();
				fw.close();
				binary.release();
				return;
			}
			output.print(bufferTest[j]);
			System.out.print(bufferTest[j]);
			j = (j + 1) % bufferSize;
			count--;
		}
		System.out.println();
		output.close();
		fw.close();
		binary.release();// exit critical section
		return;

	}

}
