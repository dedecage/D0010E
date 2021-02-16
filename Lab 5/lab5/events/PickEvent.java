package events;

import instore.Customer;
import instore.FIFO;
import instore.Store;
import instore.Time;
import simulator.Event;
import simulator.EventQueue;
import simulator.State;


public class PickEvent extends Event {

	private State state;
	private Customer myCustomer;
	private Store store;
	private EventQueue queue;
	private double pickSavedTime;
	private Time generalTime;

	public PickEvent(Store store, EventQueue queue, Customer c, double time, State state, Time generalTime) {

		this.store = store;
		this.queue = queue;
		myCustomer = c;
		pickSavedTime = time;
		this.state = state;
		this.generalTime = generalTime;

	}

	/**
	 * Execute either creates a payment event if there are available registers. If
	 * no registers are available, the customer linked to the event gets added to
	 * the queue.
	 */
	public void execute() {

		FIFO storeFIFO = store.getFIFO();

		if (store.getFreeRegisters() > 0) {

			// Updates the time according to the current time count.
			store.updateTime(pickSavedTime);
			store.updateTotalQueueTime(store.getCurrentTime(), store.getPastTimeEvent());
			store.updateTotalRegisterTime(store.getCurrentTime(), store.getPastTimeEvent());

			PayEvent queuePay = new PayEvent(store, queue, myCustomer, getPayTime(), state, generalTime);

			queue.addAndSort(queuePay);

			state.update();
			store.removeFreeRegister();

		} else {

			store.updateTime(pickSavedTime);

			store.updateTotalQueueTime(store.getCurrentTime(), store.getPastTimeEvent());
			store.updateTotalRegisterTime(store.getCurrentTime(), store.getPastTimeEvent());

			state.update();
			storeFIFO.add(myCustomer);

		}

	}

	public double getTime() {

		return pickSavedTime;
	}

	public double getPayTime() {

		return pickSavedTime + generalTime.getPayTime();
	}

	public String getCustomerId() {

		return Integer.toString(myCustomer.getID());
	}

	public String getEvent() {

		return "Plock";
	}

}
