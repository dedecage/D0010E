package instore;

import java.util.ArrayList;


import instore.FIFO;
import simulator.Event;
import simulator.EventQueue;
import simulator.State;


public class Store extends State {

	private double lastPaymentAverageTime = 0;
	private double currentEventTime = 0;
	private double lastEventTime = 0;
	private double collectedQueueTime = 0;
	private double collectedRegisterTime = 0;
	private int presentCustomers = 0;
	private long seed;
	private double lambda;
	private double lowerPick;
	private double upperPick;
	private double lowerPay;
	private double upperPay;

	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private EventQueue queue;
	private int successfulPurchase;
	private int failedPurchase;
	private double closingTime;

	private int maxCustomers;
	private boolean openStore;
	private int maxRegisters;
	private int freeRegisters;

	private FIFO fifo;

	/**
	 * 
	 * The constructor of this class takes a number o parameters.
	 */
	public Store(double closingTime, int maxCustomers, int registers, double lambda, long seed, double upperPick,
			double upperPay, double lowerPick, double lowerPay, EventQueue queue) {

		this.closingTime = closingTime;
		this.queue = queue;
		this.maxCustomers = maxCustomers;
		this.lambda = lambda;
		this.seed = seed;
		this.lowerPick = lowerPick;
		this.upperPick = upperPick;
		this.lowerPay = lowerPay;
		this.upperPay = upperPay;
		this.maxRegisters = registers;
		freeRegisters = registers;
		openStore = true;
		fifo = new FIFO();
	}

	/**
	 * Adds a customer to the array.
	 */
	public void addCustomerArray(Customer c) {
		customerList.add(c);
	}

	/**
	 * Adds successful purchases.
	 */
	public void addSuccessfulPurchase() {

		successfulPurchase++;

	}

	/**
	 * Adds failed purchases.
	 */
	public void addFailedPurchase() {

		failedPurchase++;

	}

	/**
	 * Adds present customer.
	 */
	public void addPresentCustomers() {

		presentCustomers++;
	}

	/**
	 * Adds free registers.
	 */
	public void addFreeRegisters() {

		freeRegisters++;
	}

	/**
	 * Closing the store by by changing the variable to false.
	 */
	public void setCloseStore() {

		openStore = false;
	}

	/**
	 * Return if the store is open or closed.
	 * 
	 * @return if the store is open or closed.
	 */
	public boolean checkStoreOpen() {

		return openStore;
	}

	/**
	 * Return the size of the queue.
	 * 
	 * @return the size of the queue.
	 */
	public int getLineSize() {

		return fifo.size();
	}

	/**
	 * Return the queue and converts it to a string.
	 * 
	 * @return the queue and converts it to a string.
	 */
	public String printFIFO() {

		return fifo.toString();
	}

	/**
	 * Removes a present customer.
	 */
	public void removePresentCustomer() {

		presentCustomers--;
	}

	/**
	 * Removes a free register.
	 */
	public void removeFreeRegister() {

		freeRegisters--;
	}

	/**
	 * Return the value of lambda.
	 * 
	 * @return the value of lambda.
	 */
	public double getLambda() {

		return lambda;
	}

	/**
	 * Return he value of the seed
	 * 
	 * @return the value of the seed.
	 */
	public long getSeed() {

		return seed;
	}

	/**
	 * Return the lower pick time.
	 * 
	 * @return the lower pick time.
	 */
	public double getLowerPick() {

		return lowerPick;
	}

	/**
	 * Returns the upper pick time.
	 * 
	 * @return the upper pick time.
	 */
	public double getUpperPick() {

		return upperPick;
	}

	/**
	 * Returns the lower pay time
	 * 
	 * @return the lower pay time.
	 */
	public double getLowerPay() {

		return lowerPay;
	}

	/**
	 * Return the upper pay time.
	 * 
	 * @return the upper pay time.
	 */
	public double getUpperPay() {

		return upperPay;
	}

	/**
	 * Return the closing time.
	 * 
	 * @return the closing time.
	 */
	public double getClosingTime() {

		return closingTime;
	}

	/**
	 * Return the present customers in the store.
	 * 
	 * @return the present customers in the store.
	 */
	public int getPresentCustomers() {

		return presentCustomers;
	}

	/**
	 * Return the max customers in the store.
	 * 
	 * @return the max customers in the store.
	 */
	public int getMaxCustomers() {

		return maxCustomers;
	}

	/**
	 * Return the max registers.
	 * 
	 * @return the max registers.
	 */
	public int getRegisters() {

		return maxRegisters;
	}

	/**
	 * Return amount of free registers.
	 * 
	 * @return amount of free registers.
	 */
	public int getFreeRegisters() {

		return freeRegisters;
	}

	/**
	 * Return the amount of successful purchases.
	 * 
	 * @return the amount of successful purchases.
	 */
	public int getSuccessfulPurchase() {

		return successfulPurchase;

	}

	/**
	 * Return the amount of failed purchases.
	 * 
	 * @return the amount of failed purchases.
	 */
	public int getFailedPurchase() {

		return failedPurchase;
	}

	/**
	 * Return the amount of total purchases.
	 * 
	 * @return the amount of total purchases.
	 */
	public int getTotalPurchase() {

		return successfulPurchase + failedPurchase;
	}

	/**
	 * 
	 * Updates the total amount of collected register free time and store the times
	 * for the last pay-event.
	 */
	public void updateTotalRegisterTime(double currentTime, double pastEventTime) {

		if (checkStoreOpen()) {
			collectedRegisterTime += (currentTime - pastEventTime) * getFreeRegisters();
		} else {
			int counter = 0;
			ArrayList<Event> eList = queue.getList();
			for (Event e : eList) {
				if (e.getEvent() == "Betalning") {
					counter++;
					lastPaymentAverageTime = e.getTime();
				}
			}
			if (counter > 0) {
				collectedRegisterTime += (currentTime - pastEventTime) * getFreeRegisters();
			}
		}
	}

	/**
	 * 
	 * Calculates the total collected queue-time.
	 */
	public void updateTotalQueueTime(double currentTime, double pastEventTime) {
		collectedQueueTime += (currentTime - pastEventTime) * fifo.size();
	}

	/**
	 * Updates the time by setting last event time (lastEventTime) to current event
	 * time (currentEventTime). The value given in the parameter is then given to
	 * currentEventTime.
	 */
	public void updateTime(double time) {

		lastEventTime = currentEventTime;

		currentEventTime = time;

	}

	/**
	 * Return the last event time.
	 * 
	 * @return the last event time.
	 */
	public double getPastTimeEvent() {
		return lastEventTime;
	}

	/**
	 * Return the current event time.
	 * 
	 * @return the current event time.
	 */
	public double getCurrentTime() {
		return currentEventTime;
	}

	/**
	 * Return the time for the last pay event.
	 * 
	 * @return the time for the last pay event.
	 */
	public double getLastPaymentAverageTime() {

		return lastPaymentAverageTime;
	}

	/**
	 * Return the collected register free time.
	 * 
	 * @return the collected register free time.
	 */
	public double getRegisterTime() {

		return collectedRegisterTime;
	}

	/**
	 * Return the collected queue time.
	 * 
	 * @return the collected queue time.
	 */
	public double getCollectedQueueTime() {
		return collectedQueueTime;
	}

	/**
	 * Return the max size of the queue.
	 * 
	 * @return the max size of the queue.
	 */
	public int getMaxLine() {

		return fifo.maxSize();
	}

	/**
	 * Return the fifo (queue) as a string.
	 * 
	 * @return the fifo (queue) as a string.
	 */
	public String getPrintedLine() {

		return fifo.toString();
	}

	/**
	 * Return the fifo.
	 * 
	 * @return the fifo.
	 */
	public FIFO getFIFO() {

		return fifo;

	}

	/**
	 * Return the amount of customer that have queued.
	 * 
	 * @return
	 */
	public int getCustomersQueued() {
		return fifo.getCustomersQueued();
	}

}
	


