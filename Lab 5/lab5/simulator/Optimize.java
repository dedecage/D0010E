package simulator;

import java.util.Random;

import instore.Store;


public class Optimize {

	/**
	 * Runs the first method with set parameters and returns the amount of failed
	 * purchases.
	 * 
	 * @return the amount of failed purchases.
	 */
	public int metod1(double closingTime, int maxCustomers, int registers, double lambda, long seed, double upperPick,
			double upperPay, double lowerPick, double lowerPay, State state, EventQueue queue) {

		Store store = new Store(closingTime, maxCustomers, registers, lambda, seed, upperPick, upperPay, lowerPick,
				lowerPay, queue);

		Simulator simulator = new Simulator(queue, state, store);
		simulator.run();
		return store.getFailedPurchase();
	}

	/**
	 * Runs the first method until the minimum amount of registers are found without
	 * getting a higher amount of missed customers. The amount of registers start
	 * with the maximum amount of customers and iterates downwards until the optimal
	 * amount of registers have been found.
	 * 
	 * @return optimal number of registers for this seed.
	 */
	public int metod2(long seed) {
		// Set parameters for the first method.
		int maxCustomers = 5;
		double closingTime = 10;
		double minPickTime = 0.5;
		double maxPickTime = 1;
		double minPayTime = 2;
		double maxPayTime = 3;
		double lambda = 1;
		State state = new State();
		EventQueue queue = new EventQueue();

		int minRegisters = maxCustomers;

		int missedCustomers = metod1(closingTime, maxCustomers, minRegisters, lambda, seed, maxPickTime, maxPayTime,
				minPickTime, minPayTime, state, queue);

		while (minRegisters >= 1) {

			int newMissedCustomers = metod1(closingTime, maxCustomers, minRegisters, lambda, seed, maxPickTime,
					maxPayTime, minPickTime, minPayTime, state, queue);

			if (missedCustomers != newMissedCustomers) {
				return minRegisters + 1;
			}
			minRegisters -= 1;
		}
		return maxCustomers;
	}

	/**
	 * Runs the second method with different seeds. The maximum of the minimum
	 * number of registers is saved. The method continues until until the value has
	 * not changed during 100 iterations.
	 * 
	 * @return the maximum of the minimum number of registers with the different
	 *         seeds.
	 */
	public int metod3(long seed) {
		Random ran = new Random(seed);
		int counter = 0;
		int maxMinRegisters = 0;

		while (counter < 100) {
			int newAmountOfRegisters = metod2(ran.nextLong());

			if (maxMinRegisters != Math.max(maxMinRegisters, newAmountOfRegisters)) {
				counter = 0;
			} else {
				counter += 1;
			}
			maxMinRegisters = Math.max(maxMinRegisters, newAmountOfRegisters);
		}
		return maxMinRegisters;
	}

	public static void main(String[] args) {
		Optimize op = new Optimize();
		long seed = 1234;
		System.out.println(op.metod2(seed));

	}
}