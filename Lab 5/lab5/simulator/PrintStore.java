package simulator;

import java.text.DecimalFormat;
import simulator.EventQueue;
import simulator.View;

import java.util.*;

import instore.Store;


public class PrintStore extends View {

	private EventQueue queue;
	private Store store;

	DecimalFormat df = new DecimalFormat("####0.00");
	DecimalFormat df2 = new DecimalFormat("#0");

	/**
	 * The contructor takes a store, queue and state as parameter.
	 */
	public PrintStore(Store store, EventQueue queue, State state) {

		this.store = store;
		this.queue = queue;

	}

	/**
	 * Prints the header.
	 */
	public void printHeader() {
		System.out.println("PARAMETRAR\n" + "==========\n" + "Antal kassor, N..........: " + store.getRegisters() + "\n"
				+ "Max som ryms, M..........: " + store.getMaxCustomers() + "\n" + "Plocktider, [P_min..Pmax]: ["
				+ store.getLowerPick() + ".." + store.getUpperPick() + "]" + "\n" + "Betaltider, [K_min..Kmax]: ["
				+ store.getLowerPay() + ".." + store.getUpperPay() + "]" + "\n" + "Ankomshastighet, lambda..: "
				+ store.getLambda() + "\n" + "Frö, f...................: " + store.getSeed() + "\n");

		System.out.println("FÖRLOPP\n" + "=======");

		System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Tid",
				"Händelse", "Kund", "?", "led", "ledT", "I", "$", ":-(", "köat", "köT", "köar", "[kassakö..]");

		System.out.println("0.00" + "\t" + " " + " " + " " + "Start");
	}

	/**
	 * Prints the events.
	 */
	public void printEvent() {

		System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
				df.format(queue.getCurrentEvent().getTime()), queue.getEvent(), queue.getCurrentEvent().getCustomerId(),
				openOrNot(), store.getFreeRegisters(), df.format(store.getRegisterTime()),
				df2.format(store.getPresentCustomers()), store.getSuccessfulPurchase(), store.getFailedPurchase(),
				store.getCustomersQueued()

				, df.format(store.getCollectedQueueTime()), store.getFIFO().size(), store.getPrintedLine());

	}

	/**
	 * Prints the footer.
	 */
	public void printFooter() {

		System.out.println("999.00" + "\t" + "   " + "Stop");

		System.out.println("\nRESULTAT" + "\n========" + "\n\n1) Av " + store.getTotalPurchase() + " kunder handlade "
				+ store.getSuccessfulPurchase() + " medan " + store.getFailedPurchase() + " missades.\n\n"

				+ "2) Total tid " + store.getRegisters() + " " + "kassor varit lediga: "
				+ df.format(store.getRegisterTime()) + " " + "te.\n" + "Genomsnittlig ledig kassatid: "
				+ df.format(store.getRegisterTime() / store.getRegisters()) + " te " + "(dvs" + " "
				+ df.format(
						((store.getRegisterTime() / store.getRegisters()) / store.getLastPaymentAverageTime()) * 100)
				+ "%" + " " + "av tiden från öppning tills sista kunden betalat).\n\n"

				+ "3) Total tid " + store.getCustomersQueued() + " " + "kunder tvingats köa:" + " "
				+ df.format(store.getCollectedQueueTime()) + " " + "te.\n" + "   " + "Genomsnittlig kötid: "
				+ df.format(store.getCollectedQueueTime() / store.getCustomersQueued()) + " " + "te.");
	}

	/**
	 * Return a "S" or a "Ö" depending on whether the store is opened or closed.
	 * 
	 * @return a "S" or a "Ö" depending on whether the store is opened or closed.
	 */
	private String openOrNot() {
		String x = "S";
		if (store.checkStoreOpen() == true) {
			x = "Ö";
		}
		return x;
	}

	/**
	 * This method calls on of the three methods above depending on the returning
	 * String from the first event in the queue.
	 */
	public void update(Observable arg0, Object arg1) {

		if ((queue.getEvent().equals("Start"))) {

			printHeader();

		}

		if (!(queue.getEvent().equals("Start"))) {

			if (queue.getEvent().equals("Stop")) {
				printFooter();
			} else {
				printEvent();
			}

		}

	}

}
