package events;

import instore.Customer;
import instore.Store;
import instore.Time;
import simulator.Event;
import simulator.EventQueue;
import simulator.State;


public class PayEvent extends Event {

	private State state;
	private Customer myCustomer;
	private Store store;
	private EventQueue queue;
	private double time;
	private Time generalTime;

	public PayEvent(Store store, EventQueue queue, Customer c, double time, State state, Time generalTime) {

		this.store = store;
		this.queue = queue;
		myCustomer = c;
		this.time = time;
		this.state = state;
		this.generalTime = generalTime;
	}

	/**
	 * If there is a queue, a new PayEvent is created for the first customer in
	 * line. Updates necessary values in store such as adding a successful purchase,
	 * and removing the customer linked to the event.
	 */
	public void execute() {

		store.updateTime(time);
		store.updateTotalRegisterTime(store.getCurrentTime(), store.getPastTimeEvent());
		store.updateTotalQueueTime(store.getCurrentTime(), store.getPastTimeEvent());

		state.update();
		store.addFreeRegisters();
		if (store.getFIFO().isEmpty() == false) {

			Customer customerFirstInLine = store.getFIFO().first();

			double payTime = this.time + generalTime.getPayTime();

			PayEvent queuePay = new PayEvent(store, queue, customerFirstInLine, payTime, state, generalTime);

			queue.addAndSort(queuePay);

			store.removeFreeRegister();
		}

		if (store.getFIFO().size() > 0) {
			store.getFIFO().removeFirst();
		}

		store.removePresentCustomer();
		store.addSuccessfulPurchase();
	}

	public double getTime() {

		return time;
	}

	public double totalTime() {

		return getTime() + time;
	}

	public String getCustomerId() {

		return Integer.toString(myCustomer.getID());
	}

	public String getEvent() {

		return "Betalning";
	}

}
