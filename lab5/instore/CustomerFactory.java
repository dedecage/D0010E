package instore;


public class CustomerFactory {

	private int customerID = 0;

	/**
	 * Return a new customer with a unique customer id.
	 * 
	 * @return a new customer with a unique customer id.
	 */
	public Customer createCustomer() {

		return new Customer(customerID++);
	}

}
