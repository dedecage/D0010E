package events;

import instore.Customer;
import instore.CustomerFactory;
import instore.Store;
import instore.Time;
import simulator.Event;
import simulator.EventQueue;
import simulator.State;


public class StartEvent extends Event {

	private CustomerFactory custFact = new CustomerFactory();

	private State state;
	private Customer myCustomer;
	private EventQueue queue;
	private Store store;
	private Time generalTime;
	double arrivalSpawnTime;

	public StartEvent(Store store, EventQueue queue, State state, Time generalTime) {

		this.store = store;
		this.queue = queue;
		this.state = state;
		this.generalTime = generalTime;
		arrivalSpawnTime = 0;

	}

	/**
	 * Adds arrival events according to the mathematical formula in the
	 * ExponentialRandomStream until the store closes. A customer is also created
	 * and added to an array in customer.
	 */
	public void execute() {

		while (arrivalSpawnTime < store.getClosingTime()) {

			arrivalSpawnTime += generalTime.getArrivalTime();
			ArrivalEvent arrival = new ArrivalEvent(store, queue, arrivalSpawnTime, state, generalTime);

			queue.addAndSort(arrival);
			myCustomer = custFact.createCustomer();

			arrival.setCustomer(myCustomer);

			store.addCustomerArray(myCustomer);

		}

		CloseEvent closeStore = new CloseEvent(store, state);

		queue.addAndSort(closeStore);

		state.update();
	}

	/**
	 * Returns the time for StartEvent, which will always be 0.
	 */
	public double getTime() {

		return 0.00;

	}

	public String getCustomerId() {

		return "0";
	}

	/*
	 * Returns the String of the Event (based on the name).
	 */
	public String getEvent() {

		return "Start";
	}

}
