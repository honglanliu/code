import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LRU {
static int frameNum; 
static int pageNum; 
static int page[]; 
static int recentUsed[];
static int frame[];
static int count = 0;
    
public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader bufferRead= new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the number of frames: ");
    frameNum = Integer.parseInt(bufferRead.readLine());
    System.out.println("Enter number of pages: ");
    pageNum = Integer.parseInt(bufferRead.readLine());
    
    frame = new int[frameNum];
    page = new int[pageNum];
    recentUsed = new int[frameNum];
    
    System.out.println("Enter page number: ");
    for(int i = 0; i < pageNum; i++)
        page[i] = Integer.parseInt(bufferRead.readLine());
    init();
	display();
	recentlyLeastUsed();

}
	
	public static void display() {
		System.out.println("Reference String is: ");
		for (int i = 0; i < pageNum; i++){
			System.out.print(page[i] + " ");
		}
		System.out.println("");
		for (int i = 0; i < pageNum; i++){
			System.out.print("--");
		}
		System.out.println("");
		System.out.print("Frame: ");
		for (int j = 0; j < frameNum; j++) {            
			System.out.print(frame[j] + " ");
		}
		System.out.println("");
		
	}

	public static void init() {//initialize all frames and recentUsed frame record to -1;
		for(int i = 0; i < frameNum; i++)    
        {
            frame[i] = -1;
            recentUsed[i] = -1;
        }
		count = 1;
	}

	public static void recentlyLeastUsed() {
		frame[0] = page[0];
		int temp;
		recentUsed[0] = 0;
		int i, j;
		for (i = 1; i < pageNum; i++) {
			System.out.print("Frame: ");
			for (j = 0; j < frameNum; j++) {
				if (page[i] == frame[j]) { 
					recentUsed[j] = i;
					for(int k2 = 0; k2 < frameNum; k2++){
						System.out.print(frame[k2] + " ");
					}
					break;
				}
				else
					System.out.print(frame[j] + " ");
			}
			System.out.println("");
			if (j != frameNum){
				continue;
			}
		
			for (j = 0; j < frameNum; j++) {
				if (frame[j] == -1) { 
					frame[j] = page[i];
					recentUsed[j] = i;
					count++;
					/*for(int k2=0;k2<frameNum;k2++){
						System.out.print(frame[k2] + " ");
					}
					System.out.println("");*/
					break;
				}
			}
			if (j != frameNum){
				continue;
			}
			temp = 0;
			for (j = 0; j < frameNum; j++) { 
				if (recentUsed[temp] > recentUsed[j])
					temp = j;
			}
	
			frame[temp] = page[i];
			recentUsed[temp] = i;
			count++;
		}
		System.out.print("Frame: ");
		for(int k1 = 0; k1 < frameNum; k1++){
			System.out.print(frame[k1] + " ");
		}
		System.out.println("");
		System.out.println("Page fault happens: " + count + " times.");
	}
	
}