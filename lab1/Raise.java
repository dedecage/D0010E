package lab1;


public class Raise {
	static int counter1 = 0;
	static int counter2 = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i = 1; i<= 15; i++) {
//			TASK 9)
//			System.out.println("recRaiseOne: " + recRaiseOne(1.5, i));
//			System.out.println("recRaiseHalf: " + recRaiseHalf(1.5, i));
			
//			TASK 10)
//			/**
//			 * Counter resets after every
//			 * iteration to avoid double
//			 * counts.
//			 */
//			System.out.println("recRaiseOne: " + recRaiseOne(1.0001, i));
//			System.out.println("Antal steg för recRaiseOne: " + counter1);
//			System.out.println("recRaiseHalf: " + recRaiseHalf(1.0001, i));
//			System.out.println("Antal steg för recRaiseHalf: " + counter2);
//			counter1 = 0;
//			counter2 = 0;
		}
		
//		TASK 10.2)
		for(int i = 1; i <= 10001; i = i + 200) {
			/**
			 * in recRaiseOne, the number of steps
			 * is 1:1 to k
			 */
			
//			Prints the number of steps from k=1-1000;
//			recRaiseOne(1.0001, i);
//			System.out.println(counter1);
//			counter1 = 0;
//			System.out.println(i);
//			recRaiseHalf(1.0001, i);
//			System.out.println(counter2);
//			counter2 = 0;
		}
		
//		TASK 10.4)
		for(int i = 1; i < 65; i++) {
			System.out.println(i);
			recRaiseHalf(1.0001, i);
			System.out.println(counter2);
			counter2 = 0;
		}
		
		
	}

	public static double recRaiseOne(double x, int k) {
		if (k == 0) {
			return 1.0;
		} else {
			counter1 += 1;
			return x * recRaiseOne(x, k - 1);
		}
	}
	
	public static double recRaiseHalf(double x, int k) {
		if (k == 0) {
			counter2 += 1;
			return 1;
		} else if (k % 2 == 0) {
			counter2 += 1;
			double p = recRaiseHalf(x, (k / 2));
			return p * p;
		} else {
			counter2 += 1;
			double p = recRaiseHalf(x, (k / 2));
			return x * p * p;
		}
	}

}
