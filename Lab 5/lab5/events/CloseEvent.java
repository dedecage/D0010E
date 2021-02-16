package events;

import instore.Store;
import simulator.Event;
import simulator.State;


public class CloseEvent extends Event {

	private State state;
	private Store store;

	public CloseEvent(Store store, State state) {

		this.store = store;
		this.state = state;
	}

	/**
	 * Updates essential values in store, and sets the store to closed.
	 */
	public void execute() {

		store.updateTime(store.getClosingTime());
		store.updateTotalRegisterTime(store.getCurrentTime(), store.getPastTimeEvent());
		store.updateTotalQueueTime(store.getCurrentTime(), store.getPastTimeEvent());
		store.setCloseStore();
		state.update();
	}

	public double getTime() {

		return store.getClosingTime();
	}

	public String getCustomerId() {

		return "----";
	}

	public String getEvent() {

		return "Stänger";
	}

}
