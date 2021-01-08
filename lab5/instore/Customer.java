package instore;


public class Customer {

	private int customerID;

	/**
	 * Every customer has its own id.
	 */
	public Customer(int ID) {

		customerID = ID;

	}

	public int getID() {

		return customerID;
	}

}
