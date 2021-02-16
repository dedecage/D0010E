package simulator;

import events.StartEvent;
import events.StopEvent;
import instore.Store;
import instore.Time;


public class Simulator {

	private Store store;
	private EventQueue queue;
	private State state;
	private Time generalTime;

	public Simulator(EventQueue queue, State state, Store store) {
		this.queue = queue;
		this.state = state;
		this.store = store;
		generalTime = new Time(store);
	}

	/**
	 * The run method is removes events from the event queue by calling the
	 * eventDone() method in the EventQueue object until the queue is empty.
	 */
	public void run() {

		queue.addAndSort(new StartEvent(store, queue, state, generalTime));
		queue.addAndSort(new StopEvent(state, store));

		while (!state.getStopFlag()) {

			queue.eventDone();

		}
	}

}
