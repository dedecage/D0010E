package lab1;


public class LifeLength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 6;
		switch (n) {
		case 1:
			task1(args);
			break;
		case 2:
			task2();
			break;
		case 3:
			task3();
			break;
		case 4:
			task4();
			break;
		case 6:
			task6();
			break;
		default:
			System.out.println("Invalid value");
			break;
		}


	}

	public static int f1(int a0) {	
		if (a0 == 1) {
			return 1;
		} else if ((a0 % 2) == 0) {
			return a0 / 2;

		} else {
			return ((a0 * 3) + 1);
		}
	}

	public static int f2(int n) {
		return f1(f1(n));
	}

	public static int f4(int n) {
		/**
		 * instead of running f1 4 times, we can utilize
		 * f2 which runs n 2 times. This halves the amount
		 * of commands needed for f4.
		 */
		return f2(f2(n));
	}

	public static int f8(int n) {
		return f4(f4(n));
	}

	public static int f16(int n) {
		return f8(f8(n));
	}

	public static int f32(int n) {
		return f16(f16(n));
	}

	public static int interateF(int a0, int n) {
		/**
		 * a0 continually gains the value of f1(a0)
		 * n amount of times
		 */
		for (int i = 0; i < n; i++) {
			a0 = f1(a0);
		}
		return a0;
	}

	public static int iterLifeLength(int a0) {
		/**
		 * runs a0 through the f1 method until
		 * a0 = 1. The counter checks how many
		 * iterations this process takes.
		 */
		int counter = 0;
		while (a0 != 1) {
			a0 = f1(a0);
			counter += 1;
		}
		return counter;
	}

	public static String intsToString(int X, int Y) {
		/**
		 * By passing the original value a0 and 
		 * the return value of a life length 
		 * method, we can utilize this method to 
		 * return a string with information about 
		 * a0:s life length.
		 */
		Integer.toString(X);
		Integer.toString(Y);
		return "The life length of " + X + " is " + Y + ".";
	}

	public static int recLifeLength(int n) {
		/**
		 * Uses recursion until n = 1. The
		 * variable len acts as a counter
		 * to see how many steps were needed
		 * to reach n = 1.
		 */
		if (n == 1) {
			return 0;
		} else {
			int len = recLifeLength(f1(n)) + 1;
			return len;
		}
	}

	public static void task1(String args[]) {
		/**
		 * takes arguments from run configuration and
		 * runs them in f1.
		 */
		int first = Integer.parseInt(args[0]);
		int second = Integer.parseInt(args[1]);
		System.out.println(f1(first));
		System.out.println(f1(second));
	}

	public static void task2() {
		System.out.println(f1(42));
		System.out.println(f2(42));
		System.out.println(f4(42));
		System.out.println(f8(42));
		System.out.println(f16(42));
		System.out.println(f32(42));
	}

	public static void task3() {
		System.out.println(interateF(3, 5));
		System.out.println(interateF(42, 3));
		System.out.println(interateF(1, 3));
	}

	public static void task4() {
		/**
		 * Prints out the life lengths of 
		 * integers 1-15 with the iterative
		 * method iterLifeLength.
		 */
		for (int i = 1; i <= 15; i++) {
			int x = iterLifeLength(i);
			System.out.println(intsToString(i, x));
		}
	}

	public static void task6() {
		/**
		 * Prints out the life lengths of 
		 * integers 1-15 with the recursive
		 * method recLifeLength.
		 */
		for (int i = 1; i <= 15; i++) {
			int y = recLifeLength(i);
			System.out.println(intsToString(i, y));
		}
	}

}
