package events;

import instore.Store;
import simulator.Event;
import simulator.State;


public class StopEvent extends Event {

	private State state;

	public StopEvent(State state, Store store) {

		this.state = state;
	}

	/**
	 * Changes the stop flag in state, this ends the simulation.
	 */
	public void execute() {

		state.quitSimulation();
	}

	public double getTime() {

		return 999.00;
	}

	public String getEvent() {

		return "Stop";
	}

	public String getCustomerId() {

		return "999";
	}

}
