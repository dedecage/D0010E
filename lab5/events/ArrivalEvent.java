package events;

import instore.Customer;

import instore.Store;
import instore.Time;
import simulator.Event;
import simulator.EventQueue;
import simulator.State;


public class ArrivalEvent extends Event {

	private State state;
	private Customer myCustomer;
	private Store store;
	private EventQueue queue;
	private double arrivalSavedTime;
	private Time generalTime;

	public ArrivalEvent(Store store, EventQueue queue, double arrivalSavedTime, State state, Time generalTime) {

		this.store = store;
		this.queue = queue;
		this.arrivalSavedTime = arrivalSavedTime;
		this.state = state;
		this.generalTime = generalTime;
	}

	/*
	 * Adds a pick-event events according to the mathematical formula in the
	 * ExponentialRandomStream until the store closes.
	 */

	public void execute() {

		// Updates the time count according to current time units.
		store.updateTime(arrivalSavedTime);
		store.updateTotalQueueTime(store.getCurrentTime(), store.getPastTimeEvent());
		store.updateTotalRegisterTime(store.getCurrentTime(), store.getPastTimeEvent());

		state.update();
		if (store.getPresentCustomers() < store.getMaxCustomers() && store.checkStoreOpen()) {

			PickEvent Pick = new PickEvent(store, queue, myCustomer, getPickTime(), state, generalTime);

			queue.addAndSort(Pick);

		} else {

			if (store.checkStoreOpen()) {

				store.addFailedPurchase();
			}
		}

		if (store.getPresentCustomers() < store.getMaxCustomers() && store.checkStoreOpen()) {
			store.addPresentCustomers();
		}
	}

	public void setCustomer(Customer c) {

		myCustomer = c;

	}

	public double getTime() {

		return arrivalSavedTime;
	}

	/**
	 * Takes the previous calculated time and adds it to the execution time and adds
	 * them in order to create a current time.
	 * 
	 * @return
	 */
	public double getPickTime() {

		return arrivalSavedTime + generalTime.getPickTime();
	}

	public Customer getStore() {

		return myCustomer;
	}

	public String getCustomerId() {

		return Integer.toString(myCustomer.getID());
	}

	public String getEvent() {

		return "Ankomst";
	}

}
