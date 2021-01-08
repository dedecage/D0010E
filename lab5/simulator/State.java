package simulator;


import java.util.Observable;


public class State extends Observable {

	private boolean stopFlag = false;

	/**
	 * This method quits the simulation by setting the boolean "stopFlag" to false.
	 */
	public void quitSimulation() {
		stopFlag = true;

		update();

	}

	/**
	 * Return the boolean stopFlag.
	 * 
	 * @return the boolean stopFlag.
	 */
	public boolean getStopFlag() {

		return stopFlag;
	}

	/**
	 * This method contains the methods setChanged() and notifyObservers().
	 */
	public void update() {

		setChanged();
		notifyObservers();
	}

}

