package simulator;

import instore.Store;


public class RunSim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue queue = new EventQueue();
		State state = new State();
//		Store store = new Store(10, 5, 2, 1.0, 1234, 1.0, 3.0, 0.5, 2.0, queue);
		Store store = new Store(8, 7, 2, 3.0, 13, 0.9, 0.6, 0.6, 0.35, queue);

		PrintStore printstore = new PrintStore(store, queue, state);
		state.addObserver(printstore);

		Simulator sim = new Simulator(queue, state, store);
		sim.run();

	}

}
