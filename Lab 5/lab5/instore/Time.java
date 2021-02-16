package instore;

import time.ExponentialRandomStream;
import time.UniformRandomStream;


public class Time {

	private ExponentialRandomStream arrivalExponential;
	private UniformRandomStream pickUniform;
	private UniformRandomStream payUniform;

	/**
	 * The constructor where three object's of the two different time classes are
	 * created.
	 */
	public Time(Store store) {

		arrivalExponential = new ExponentialRandomStream(store.getLambda(), store.getSeed());
		pickUniform = new UniformRandomStream(store.getLowerPick(), store.getUpperPick(), store.getSeed());
		payUniform = new UniformRandomStream(store.getLowerPay(), store.getUpperPay(), store.getSeed());

	}

	/**
	 * Returns the next exponential time value.
	 */
	public double getArrivalTime() {

		return arrivalExponential.next();
	}

	/**
	 * Returns the next uniform time value.
	 */
	public double getPickTime() {

		return pickUniform.next();
	}

	/**
	 * Returns the next uniform time value.
	 */
	public double getPayTime() {

		return payUniform.next();
	}

}
