import java.util.ArrayList;
import java.util.NoSuchElementException;


public class FIFO implements Queue{

	private ArrayList<Object> list = new ArrayList<Object>();
	private int maxSize;
	
	public int size() {
		return list.size();
	}
	
	public int maxSize() {
		return maxSize;
	}
	
	public boolean isEmpty() {
		if(list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Object first() throws NoSuchElementException{
		/*
		 * returns the object at index 0 in the list,
		 * if the list is empty, throws exception.
		 */
		if(list.size() == 0) {
			throw new NoSuchElementException("Empty");
		} else {
			return list.get(0);
		}
	}
	
	public boolean equals(Object f) {
		/*
		 * Checks that basic conditions such as
		 * list size and class type of two lists
		 * are the same.
		 */
		if(f.getClass() == this.getClass()) {
			FIFO newf = (FIFO)f;
			if(this.size() == newf.size() && compare(newf)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new ClassCastException();
		}
	}
	
	private boolean compare(FIFO f) {
		/*
		 * Goes through all of the
		 * elements in two lists
		 * to check if the elements
		 * in both lists are equal.
		 */
		
		for (int i = 0; i<list.size(); i++) {
			
			if(this.list.get(i) == null || f.list.get(i) == null) {
				if(this.list.get(i) != null || f.list.get(i) != null) {
					return false; 
				}
			}
			
			else if(!(list.get(i).equals(f.list.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		/*
		 * toString follows the provided
		 * ADT specifications.
		 */
		String queue = "Queue: ";
		for (Object x: list) {
			queue += "(" + String.valueOf(x) + ") ";
		}
		return queue;
	}

	public void add(Object item) {
		/*
		 * Adds item to the list,
		 * also tracks the maximum
		 * size of the list.
		 */
		list.add(item);
		
		if(maxSize < list.size()) {
			maxSize += 1;
		}
		
	}

	public void removeFirst() throws NoSuchElementException {
		/*
		 * If there is a list containing items,
		 * removes the item at index 0.
		 */
		if(list.size() == 0) {
			throw new NoSuchElementException("Empty");
		} else {
			list.remove(0);
		}
	}
	
}
