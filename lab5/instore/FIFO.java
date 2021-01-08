package instore;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class FIFO {

	private int orgSize;
	private int customersQueued = 0;

	ArrayList<Customer> elementArr;

	/**
	 * Constructor. Creating an array-list where we store the customers in line.
	 */
	public FIFO() {

		elementArr = new ArrayList<Customer>();

	}

	/**
	 * Returns the amount of total customers queued
	 */
	public int getCustomersQueued() {
		return customersQueued;
	}

	/**
	 * The method add, adds a customer to the queue.
	 */
	public void add(Customer item) {

		elementArr.add(item);
		addCustomerQueued();

		if (orgSize < elementArr.size()) {

			orgSize += 1;

		}

	}

	/**
	 * Increases the counter by one each time a new customer enters the queue.
	 */
	public void addCustomerQueued() {
		customersQueued++;
	}

	/**
	 * Removes the first customer from the queue.
	 */
	public void removeFirst() {

		try {
			elementArr.remove(0);
		} catch (Exception IndexOutOfBoundException) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the first customer in the queue.
	 */
	public Customer first() {
		try {
			return elementArr.get(0);
		} catch (Exception IndexOutOfBoundException) {
			throw new NoSuchElementException();
		}

	}

	public int maxSize() {
		return orgSize;

	}

	public boolean isEmpty() {
		if (elementArr.size() == 0) {
			return true;
		}
		return false;

	}

	public int size() {
		return elementArr.size();

	}

	public String toString() {

		String returnString = "[ ";
		for (int i = 0; i < elementArr.size(); i++) {
			Customer c = elementArr.get(i);
			returnString += c.getID() + " ";
		}
		returnString += "]";
		return returnString;

	}
}
