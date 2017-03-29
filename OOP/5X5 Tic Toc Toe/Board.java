import java.util.Scanner;
import java.math.*;

/**
 * Group Member: 
 * Hsin-Fu Chen, US4442 
 * Honglan Liu, sp6682 
 * Jyh-Yeuan Chen, df6937
 * Xingli Fang, aw7922
 */

public class Board {

	static Scanner in = new Scanner(System.in);

	public char[] squares = new char[26]; // element zero not used
	int[] skipNum;
	int moveCount = 0;
	static final char freeChar = '_'; // to indicate the square is available.

	public Board() {
		for (int i = 1; i <= 25; i++) {
			squares[i] = freeChar; // all 25 squares are initially available
		}
	}

	public boolean moveToSquare(int square) {
		if (squares[square] != freeChar) {
			return false;
		}// already and X or O at that location
		squares[square] = xturn() ? 'X' : 'O';
		moveCount++;
		return true;
	}

	public boolean moveToSquareP(int square, int p) {
		if (squares[square] != freeChar) {
			return false;
		}// already and X or O at that location
		squares[square] = p == 1 ? 'X' : 'O';
		moveCount++;
		return true;
	}

	boolean xturn() {
		return moveCount % 2 == 0;
	} // X's turn always follows an even number of previous moves

	boolean isFreeSquare(int square) {
		return squares[square] == freeChar;
	}

	void unDo(int square) {
		moveCount--;
		squares[square] = freeChar;
	}

	boolean boardFull() {
		return moveCount == 25;
	}

	int lineValue(int s1, int s2, int s3, int s4) {
		if (squares[s1] == 'X' && squares[s2] == 'X' && squares[s3] == 'X'
				&& squares[s4] == 'X')
			return 1; // win for X
		if (squares[s1] == 'O' && squares[s2] == 'O' && squares[s3] == 'O'
				&& squares[s4] == 'O')
			return -1; // win for O
		return 0; // nobody has won yet
	}

	void skipToPlace(int testNum) {
		switch (testNum) {
		case 1:
			skipNum = new int[] { 1, 2, 3, 4, 5, 6, 11, 16, 21, 7, 13, 19, 25 };
			break;
		case 2:
			skipNum = new int[] { 1, 2, 3, 4, 5, 7, 12, 17, 22, 6, 8, 14, 20 };
			break;
		case 3:
			skipNum = new int[] { 1, 2, 3, 4, 5, 8, 13, 18, 23, 7, 11, 9, 15 };
			break;
		case 4:
			skipNum = new int[] { 1, 2, 3, 4, 5, 9, 14, 19, 24, 8, 12, 16, 10 };
			break;
		case 5:
			skipNum = new int[] { 1, 2, 3, 4, 5, 10, 15, 20, 25, 9, 13, 17, 21 };
			break;
		case 6:
			skipNum = new int[] { 6, 7, 8, 9, 10, 1, 11, 16, 21, 2, 12, 18, 24 };
			break;
		case 7:
			skipNum = new int[] { 6, 7, 8, 9, 10, 2, 12, 17, 22, 1, 13, 19, 25,
					3, 11 };
			break;
		case 8:
			skipNum = new int[] { 6, 7, 8, 9, 10, 3, 13, 18, 23, 2, 14, 20, 4,
					12, 16 };
			break;
		case 9:
			skipNum = new int[] { 6, 7, 8, 9, 10, 4, 14, 19, 24, 3, 15, 5, 13,
					17, 21 };
			break;
		case 10:
			skipNum = new int[] { 6, 7, 8, 9, 10, 5, 15, 20, 25, 4, 14, 18, 22 };
			break;
		case 11:
			skipNum = new int[] { 11, 12, 13, 14, 15, 1, 6, 16, 21, 7, 3, 17,
					23 };
			break;
		case 12:
			skipNum = new int[] { 11, 12, 13, 14, 15, 2, 7, 17, 22, 6, 18, 24,
					4, 8, 16 };
			break;
		case 13:
			skipNum = new int[] { 11, 12, 13, 14, 15, 3, 8, 18, 23, 5, 9, 17,
					21, 1, 7, 19, 25 };
			break;
		case 14:
			skipNum = new int[] { 11, 12, 13, 14, 15, 4, 9, 19, 24, 10, 18, 22,
					2, 8, 20 };
			break;
		case 15:
			skipNum = new int[] { 11, 12, 13, 14, 15, 5, 10, 20, 25, 3, 9, 19,
					23 };
			break;
		case 16:
			skipNum = new int[] { 16, 17, 18, 19, 20, 1, 6, 11, 21, 22, 12, 8,
					4 };
			break;
		case 17:
			skipNum = new int[] { 16, 17, 18, 19, 20, 2, 7, 12, 22, 21, 13, 9,
					5, 11, 23 };
			break;
		case 18:
			skipNum = new int[] { 16, 17, 18, 19, 20, 3, 8, 13, 23, 6, 12, 24,
					22, 14, 10 };
			break;
		case 19:
			skipNum = new int[] { 16, 17, 18, 19, 20, 4, 9, 14, 24, 15, 23, 1,
					7, 13, 25 };
			break;
		case 20:
			skipNum = new int[] { 16, 17, 18, 19, 20, 5, 10, 15, 25, 2, 8, 14,
					24 };
			break;
		case 21:
			skipNum = new int[] { 21, 22, 23, 24, 25, 1, 6, 11, 16, 17, 13, 9,
					5 };
			break;
		case 22:
			skipNum = new int[] { 21, 22, 23, 24, 25, 2, 7, 12, 17, 16, 18, 14,
					10 };
			break;
		case 23:
			skipNum = new int[] { 21, 22, 23, 24, 25, 3, 8, 13, 18, 11, 17, 19,
					15 };
			break;
		case 24:
			skipNum = new int[] { 21, 22, 23, 24, 25, 4, 9, 14, 19, 20, 6, 12,
					18 };
			break;
		case 25:
			skipNum = new int[] { 21, 22, 23, 24, 25, 5, 10, 15, 20, 1, 7, 13,
					19 };
			break;
		default:
			System.out.println("invlad number");
			break;
		}
	}

	int boardValue() {
		int[][] wins = { { 1, 2, 3, 4 }, { 2, 3, 4, 5 }, { 6, 7, 8, 9 },
				{ 7, 8, 9, 10 }, { 11, 12, 13, 14 }, { 12, 13, 14, 15 },
				{ 16, 17, 18, 19 }, { 17, 18, 19, 20 }, { 21, 22, 23, 24 },
				{ 22, 23, 24, 25 }, { 1, 6, 11, 16 }, { 6, 11, 16, 21 },
				{ 2, 7, 12, 17 }, { 7, 12, 17, 22 }, { 3, 8, 13, 18 },
				{ 8, 13, 18, 23 }, { 4, 9, 14, 19 }, { 9, 14, 19, 24 },
				{ 5, 10, 15, 20 }, { 10, 15, 20, 25 }, { 1, 7, 13, 19 },
				{ 7, 13, 19, 25 }, { 6, 12, 18, 24 }, { 2, 8, 14, 20 },
				{ 4, 8, 12, 16 }, { 5, 9, 13, 17 }, { 9, 13, 17, 21 },
				{ 10, 14, 18, 22 } };
		for (int i = 0; i < wins.length; i++) {
			int v = lineValue(wins[i][0], wins[i][1], wins[i][2], wins[i][3]);
			if (v != 0)
				return v; // a winning line of X's or O's has been found
		}
		return 0; // nobody has won so far
	}

	public int getMaxPossibleLength(int loc, int dir, char player) {// found a max possible length to win
		if (loc <= 0 || loc > 25)
			return 0;

		int leftBoundary = loc % 5 == 0 ? (loc / 5 - 1) * 5 : (loc / 5) * 5;
		int rightBoundary = leftBoundary + 6;

		int length = 1;

		if (dir == 5) {

			int currloc = loc + dir;

			while (currloc >= 1 && currloc <= 25
					&& (squares[currloc] == player || squares[currloc] == '_')) {

				length++;
				currloc += dir;
			}

			currloc = loc - dir;
			while (currloc >= 1 && currloc <= 25
					&& (squares[currloc] == player || squares[currloc] == '_')) {
				length++;
				currloc -= dir;
			}
		}
		if (dir == 1) {

			int currloc = loc + dir;

			while (currloc > leftBoundary && currloc < rightBoundary
					&& (squares[currloc] == player || squares[currloc] == '_')) {

				length++;

				currloc += dir;
			}

			currloc = loc - dir;
			while (currloc > leftBoundary && currloc < rightBoundary
					&& (squares[currloc] == player || squares[currloc] == '_')) {
				length++;
				currloc -= dir;
			}
		}
		if (dir == 6) {

			int currloc = loc + dir;
			int remainder = currloc % 5;
			while (currloc >= 1 && currloc <= 25
					&& (squares[currloc] == player || squares[currloc] == '_')) {
				if (currloc % 5 == 1)
					break;
				length++;
				if (remainder == 0 || remainder == 1)
					break;
				currloc += dir;
			}

			currloc = loc - dir;
			remainder = currloc % 5;
			while (currloc >= 1 && currloc <= 25
					&& (squares[currloc] == player || squares[currloc] == '_')) {
				if (currloc % 5 == 0)
					break;
				length++;
				if (remainder == 0 || remainder == 1)
					break;
				currloc -= dir;
			}
		}
		if (dir == 4) {

			int currloc = loc + dir;
			int remainder = currloc % 5;

			while (currloc > 1 && currloc < 25
					&& (squares[currloc] == player || squares[currloc] == '_')) {
				if (currloc % 5 == 0)
					break;
				length++;
				if (remainder == 0 || remainder == 1)
					break;

				currloc += dir;
				remainder = currloc % 5;
			}

			currloc = loc - dir;
			remainder = currloc % 5;
			while (currloc > 1 && currloc < 25
					&& (squares[currloc] == player || squares[currloc] == '_')) {
				if (currloc % 5 == 1)
					break;
				length++;
				if (remainder == 0 || remainder == 1)
					break;

				currloc -= dir;
				remainder = currloc % 5;
			}
		}

		return length;
	}

	public int getLength(int loc, int dir) {// found the current length
		int leftBoundary = loc % 5 == 0 ? (loc / 5 - 1) * 5 : (loc / 5) * 5;
		int rightBoundary = leftBoundary + 6;
		int length = 1;
		try {
			if (loc < 1 || loc > 25)
				return 0;
			if (squares[loc] == '_')
				return 0;

			if (dir == 5) {
				int currloc = loc + dir;

				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == squares[loc]) {
					length++;
					currloc += dir;
				}

				currloc = loc - dir;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == squares[loc]) {
					length++;
					currloc -= dir;
				}

			}
			if (dir == 1) {
				int currloc = loc + dir;

				while (currloc > leftBoundary && currloc < rightBoundary
						&& squares[currloc] == squares[loc]) {
					length++;
					currloc += dir;
				}

				currloc = loc - dir;
				while (currloc > leftBoundary && currloc < rightBoundary
						&& squares[currloc] == squares[loc]) {
					length++;
					currloc -= dir;
				}

			}
			if (dir == 6) {
				int currloc = loc + dir;
				int remainder = currloc % 5;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == squares[loc]) {
					if (currloc % 5 == 1)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc += dir;
					remainder = currloc % 5;
				}

				currloc = loc - dir;
				remainder = currloc % 5;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == squares[loc]) {
					if (currloc % 5 == 0)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc -= dir;
					remainder = currloc % 5;
				}

			}
			if (dir == 4) {
				int currloc = loc + dir;
				int remainder = currloc % 5;

				while (currloc > 1 && currloc < 25
						&& squares[currloc] == squares[loc]) {
					if (currloc % 5 == 0)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc += dir;
				}

				currloc = loc - dir;
				remainder = currloc % 5;
				while (currloc > 1 && currloc < 25
						&& squares[currloc] == squares[loc]) {
					if (currloc % 5 == 1)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc -= dir;
				}

			}

		} catch (Exception e) {
			System.out.println("loc = " + loc + " dir =" + dir
					+ " leftBoundary = " + leftBoundary + " rightBoundary = "
					+ rightBoundary);
		}
		return length;

	}

	public int skipP(int loc, int dir, int p) {
		int leftBoundary = loc % 5 == 0 ? (loc / 5 - 1) * 5 : (loc / 5) * 5;
		int rightBoundary = leftBoundary + 6;
		int count = 0;
		int length1 = 1;
		int length2 = 1;
		if (loc < 1 || loc > 25)
			return 0;
		if (squares[loc] == '_')
			return 0;

		if (dir == 5) {
			int currloc = loc + dir;

			if (currloc >= 1 && currloc <= 25 && squares[currloc] == freeChar) {
				currloc += dir;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')
						&& length1 < 3) {
					length1++;
					currloc += dir;
				}
			}

			currloc = loc - dir;

			if (currloc >= 1 && currloc <= 25 && squares[currloc] == freeChar) {
				currloc -= dir;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')
						&& length2 < 3) {
					length2++;
					currloc -= dir;
				}
			}

		}

		if (dir == 1) {
			int currloc = loc + dir;

			if (currloc > leftBoundary && currloc < rightBoundary
					&& squares[currloc] == freeChar) {
				currloc += dir;
				while (currloc > leftBoundary && currloc < rightBoundary
						&& squares[currloc] == (p == 1 ? 'X' : 'O')
						&& length1 < 3) {
					length1++;
					currloc += dir;
				}
			}

			currloc = loc - dir;

			if (currloc > leftBoundary && currloc < rightBoundary
					&& squares[currloc] == freeChar) {
				currloc -= dir;
				while (currloc > leftBoundary && currloc < rightBoundary
						&& squares[currloc] == (p == 1 ? 'X' : 'O')
						&& length2 < 3) {
					length2++;
					currloc -= dir;
				}
			}

		}

		if (dir == 6) {
			int currloc = loc + dir;
			if ((loc == 1 || loc == 2 || loc == 6 || loc == 7 || loc == 13)
					&& squares[currloc] == freeChar) {
				if (squares[currloc + dir] == (p == 1 ? 'X' : 'O')) {
					length1++;
					if (loc != 13
							&& squares[currloc + 2 * dir] == (p == 1 ? 'X'
									: 'O'))
						length1++;
				}

			}
		}

		if (dir == 4) {
			int currloc = loc - dir;
			if ((loc == 4 || loc == 5 || loc == 9 || loc == 10 || loc == 14)
					&& squares[currloc] == freeChar) {
				if (squares[currloc - dir] == (p == 1 ? 'X' : 'O')) {
					length1++;
					if (loc != 14
							&& squares[currloc - 2 * dir] == (p == 1 ? 'X'
									: 'O'))
						length1++;
				}

			}
		}
		//
		return Math.max(length1, length2);
	}

	public int getLengthP(int loc, int dir, int p) {
		int leftBoundary = loc % 5 == 0 ? (loc / 5 - 1) * 5 : (loc / 5) * 5;
		int rightBoundary = leftBoundary + 6;
		int length = 1;
		try {
			if (loc < 1 || loc > 25)
				return 0;
			if (squares[loc] == '_')
				return 0;

			if (dir == 5) {
				int currloc = loc + dir;

				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					length++;
					currloc += dir;
				}

				currloc = loc - dir;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					length++;
					currloc -= dir;
				}

			}
			if (dir == 1) {
				int currloc = loc + dir;

				while (currloc > leftBoundary && currloc < rightBoundary
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					length++;
					currloc += dir;
				}

				currloc = loc - dir;
				while (currloc > leftBoundary && currloc < rightBoundary
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					length++;
					currloc -= dir;
				}

			}
			if (dir == 6) {
				int currloc = loc + dir;
				int remainder = currloc % 5;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					if (currloc % 5 == 1)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc += dir;
					remainder = currloc % 5;
				}

				currloc = loc - dir;
				remainder = currloc % 5;
				while (currloc >= 1 && currloc <= 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					if (currloc % 5 == 0)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc -= dir;
					remainder = currloc % 5;
				}

			}
			if (dir == 4) {
				int currloc = loc + dir;
				int remainder = currloc % 5;

				while (currloc > 1 && currloc < 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					if (currloc % 5 == 0)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc += dir;
				}

				currloc = loc - dir;
				remainder = currloc % 5;
				while (currloc > 1 && currloc < 25
						&& squares[currloc] == (p == 1 ? 'X' : 'O')) {
					if (currloc % 5 == 1)
						break;
					length++;
					if (remainder == 0 || remainder == 1)
						break;
					currloc -= dir;
				}

			}
		} catch (Exception e) {
			System.out.println("loc = " + loc + " dir =" + dir
					+ " leftBoundary = " + leftBoundary + " rightBoundary = "
					+ rightBoundary);
		}
		return length;

	}

	// draw the board
	public void draw() {
		for (int i = 1; i < 26; i++) {
			if (isFreeSquare(i))
				System.out.print(i);
			else
				System.out.print(squares[i]);
			System.out.print(" ");
			if (i % 5 == 0)
				System.out.println();
		}
	}

	public void numStart(int startTotalNum) {
		if (startTotalNum == 2) {
			int firstKey = (int) (Math.random() * 25 + 1);
			moveToSquare(firstKey);
			int secondKey = (int) (Math.random() * 25 + 1);
			while (secondKey == firstKey)
				secondKey = (int) (Math.random() * 25 + 1);
			moveToSquare(secondKey);
		} else if (startTotalNum == 4) {
			int firstKey = (int) (Math.random() * 25 + 1);
			moveToSquare(firstKey);
			int secondKey = (int) (Math.random() * 25 + 1);
			while (secondKey == firstKey)
				secondKey = (int) (Math.random() * 25 + 1);
			moveToSquare(secondKey);
			int thirdKey = (int) (Math.random() * 25 + 1);
			skipToPlace(firstKey);
			for (int i = 0; i < skipNum.length; i++) {
				if (thirdKey == skipNum[i])
					thirdKey = (int) (Math.random() * 25 + 1);
				moveToSquare(thirdKey);
				break;
			}
			int forthKey = (int) (Math.random() * 25 + 1);
			skipToPlace(secondKey);
			for (int i = 0; i < skipNum.length; i++) {
				if (forthKey == skipNum[i])
					forthKey = (int) (Math.random() * 25 + 1);
				moveToSquare(forthKey);
				break;
			}
		} else if (startTotalNum == 6) {
			int firstKey = (int) (Math.random() * 25 + 1);
			moveToSquare(firstKey);
			int secondKey = (int) (Math.random() * 25 + 1);
			while (secondKey == firstKey)
				secondKey = (int) (Math.random() * 25 + 1);
			moveToSquare(secondKey);
			int thirdKey = (int) (Math.random() * 25 + 1);
			skipToPlace(secondKey);
			for (int i = 0; i < skipNum.length; i++) {
				if (thirdKey == skipNum[i])
					thirdKey = (int) (Math.random() * 25 + 1);
				moveToSquare(thirdKey);
				break;
			}
			int forthKey = (int) (Math.random() * 25 + 1);
			skipToPlace(secondKey);
			for (int i = 0; i < skipNum.length; i++) {
				if (forthKey == skipNum[i])
					forthKey = (int) (Math.random() * 25 + 1);
				moveToSquare(forthKey);
				break;
			}
			int fifthKey = (int) (Math.random() * 25 + 1);
			skipToPlace(thirdKey);
			for (int i = 0; i < skipNum.length; i++) {
				if (fifthKey == skipNum[i] || fifthKey == firstKey)
					fifthKey = (int) (Math.random() * 25 + 1);
				moveToSquare(fifthKey);
				break;
			}
			int sixthKey = (int) (Math.random() * 25 + 1);
			skipToPlace(forthKey);
			for (int i = 0; i < skipNum.length; i++) {
				if (sixthKey == skipNum[i] || sixthKey == secondKey)
					sixthKey = (int) (Math.random() * 25 + 1);
				moveToSquare(sixthKey);
				break;
			}
		}

	}

	// get next move from the user.
	public boolean userMove() {
		boolean legalMove;
		int s;
		System.out.print("\nEnter a square number: ");
		do {
			s = in.nextInt();
			legalMove = squares[s] == freeChar;
			if (!legalMove)
				System.out.println("Try again: ");
		} while (!legalMove);
		Move m = new Move(s, evaluateMove(s));
		moveToSquare(s);
		System.out.println("Human move: " + m);
		this.draw();
		if (this.boardValue() != 0)
			return true; // a winning move
		return false;
	}

	public boolean computerMove() {
		// try {Thread.sleep(600);} catch (InterruptedException e) {}
		Move m = this.bestMove();
		moveToSquare(m.square);
		System.out.println("\nComputer move: " + m);
		draw();
		if (this.boardValue() != 0)
			return true; // a winning move
		return false;
	}

	// get a random number from min to max inclusive
	static int rand(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	/*
	 * Return a Move object representing a best move for the current player. Use
	 * minimax strategy, meaning out of all possible moves, choose the one that
	 * is the worst for your opponent. Provisionally make a move, then
	 * recursively evaluate your opponent's possible responses. Your best move
	 * is the one that "minimizes" the value of your opponent's best response.
	 */
	public Move bestMove() {
		Move bestSoFar = new Move(); // an impossibly "bad" move.

		if (moveCount == 1 && squares[13] != freeChar) {
			int[] defense = { 7, 9, 19, 17 };
			bestSoFar = new Move(defense[(int) (Math.random() * 4)], 8);
			return bestSoFar;
		}

		if (moveCount == 0) {
			bestSoFar = new Move(13, 8);
			return bestSoFar;
		}

		Move m = new Move();

		int[] dir = { 1, 5, 6, 4 };
		for (int length = 4; length >= 1; length--) {

			for (int p = 0; p < 2; p++) {

				int locFound = 0;
				int maxValue = 0;
				for (int i = 1; i < 26; i++) {
					if (squares[i] != freeChar) {
						continue;
					}
					moveToSquareP(i, p);
					boolean fitlength = false;
					for (int r = 0; r < 4; r++) {
						if (getLengthP(i, dir[r], p) == length
								&& getMaxPossibleLength(i, dir[r], p == 1 ? 'X'
										: 'O') >= 4) {
							fitlength = true;
						}
					}

					if (fitlength) {
						int value = 0;
						value += evaluateMoveP(i, p);
						value += evaluateMoveP(i, p + 1);
						if (value > maxValue) {
							maxValue = value;
							locFound = i;
						}
					}
					unDo(i);
				}
				if (locFound > 0) {
					bestSoFar = new Move(locFound, maxValue);
					return bestSoFar;
				}
			}
		}
		// When no best value is found, selected an empty block.
		for (int i = 1; i < 26; i++) {
			if (squares[i] == freeChar) {
				m = new Move(i, 0);
				break;
			}
		}
		bestSoFar = m;
		return bestSoFar;
	}

	public int evaluateMove(int square) {

		int[] dir = { 1, 5, 6, 4 };

		moveToSquare(square);

		int value = 0;
		for (int r = 0; r < 4; r++) {
			if (getMaxPossibleLength(square, dir[r], xturn() ? 'X' : 'O') >= 4)
				value += getLength(square, dir[r]);
		}

		return value;

	}

	public int evaluateMoveP(int square, int p) {

		int[] dir = { 1, 5, 6, 4 };

		moveToSquareP(square, p);

		int value = 0;
		for (int r = 0; r < 4; r++) {
			int max = getMaxPossibleLength(square, dir[r], p == 1 ? 'X' : 'O');
			int length = getLengthP(square, dir[r], p);
			// int skip = skipP(square, dir[r],p);
			if (max == 4) {
				value += length * length / 2;
			} else if (max == 5) {
				value += length * length;
			}

		}

		return value;

	}

	// Move is an inner class and allows us to wrap a square and a value
	// together.
	// It's an inner class so we have access to the xturn() method of Board.
	class Move {
		int square, value;

		public Move(int square, int value) {
			this.square = square;
			this.value = value;
		}

		public Move() {
			this(14, Board.this.xturn() ? 0 : 12); // give this impossible move an impossibly bad value
		}

		boolean betterThan(Move m) {
			if (Board.this.xturn())
				return this.value > m.value;
			else
				return this.value < m.value;
		}

		public String toString() {
			return "[ square=" + square + ", value=" + value + " ]";
		}
	}

	public static void main(String[] args) {
		Board b = new Board();
		b.draw();
		System.out.println();
		System.out.println("Board is initiated as follows: ");
		double decidedNumOfStart = Math.random();
		if (decidedNumOfStart > 0.25 && decidedNumOfStart < 0.5)
			b.numStart(2);
		else if (decidedNumOfStart > 0.5 && decidedNumOfStart < 0.75)
			b.numStart(4);
		else if (decidedNumOfStart > 0.75 && decidedNumOfStart < 0.1)
			b.numStart(6);
		double whoGoesFirst = Math.random();
		if (whoGoesFirst < 0.5)
			b.computerMove();
		else
			b.draw(); // human will move first
		while (!b.boardFull()) {
			if (b.userMove()) {
				System.out.println("Congratulations! You win!");
				break;
			}
			if (!b.boardFull() && b.computerMove()) {
				System.out.println("Computer wins this one.");
				break;
			}
		}
		if (b.boardValue() == 0)
			System.out.println("Tie!");
	}

}
