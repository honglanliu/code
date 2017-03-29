public class LRUTest {
	static int BlockNum = 3; 
	static int listNum = 20; 
	static int pageorder[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 }; 
	static int time[] = new int[BlockNum];
	static int pageFrame[][] = new int[listNum][BlockNum];
	static int count = 0;
	boolean found;

	public static void show() {
		System.out.println("Reference String: ");
		for (int i = 0; i < listNum; i++){
			System.out.print(pageorder[i] + " ");
		}
		System.out.println("");
		for (int i = 0; i < listNum; i++){
			System.out.print("--");
		}
		System.out.println("");
		for (int j = 0; j < BlockNum; j++) {
			for (int i = 0; i < listNum; i++) {
				if (pageFrame[i][j] == -1)
					System.out.print(' ' + " ");
				else
					System.out.print(pageFrame[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("Page fault happens: " + count + " times.");
	}

	public static void init() {
		for (int i = 0; i < listNum; i++) {
			for (int j = 0; j < BlockNum; j++) {
				pageFrame[i][j] = -1;
			}
		}
		count = 1;
	}

	public static void LeastUsed() {
		init();
		pageFrame[0][0] = pageorder[0];
		int temp;
		int flag = 0;
		time[0] = 0;
		int i, j, k;
		for (i = 1; i < listNum; i++) {
			for (j = 0; j < BlockNum; j++) {
				if (pageorder[i] == pageFrame[flag][j]) { 
					time[j] = i;
					break;
				}
			}
			if (j != BlockNum)
				continue;
			for (k = 0; k < BlockNum; k++) {
				if (pageFrame[flag][k] == -1)
					break;
				else
					pageFrame[i][k] = pageFrame[flag][k];
			}
			for (j = 0; j < BlockNum; j++) {
				if (pageFrame[i][j] == -1) { 
					pageFrame[i][j] = pageorder[i];
					time[j] = i;
					count++;
					flag = i;
					break;
				}
			}
			if (j != BlockNum) 
				continue;
			temp = 0;
			for (j = 0; j < BlockNum; j++) { 
				if (time[temp] > time[j])
					temp = j;
			}
			pageFrame[i][temp] = pageorder[i];
			time[temp] = i;
			count++;
			flag = i;
		}
	}

	public static void main(String[] args) {
		LeastUsed();
		show();
	}
}