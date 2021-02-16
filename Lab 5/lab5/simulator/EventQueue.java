package simulator;

import java.util.ArrayList;


public class EventQueue {

	private ArrayList<Event> eventList;

	public EventQueue() {
		eventList = new ArrayList<Event>();
	}

	/**
	 * This is a method that sorts events in an ArrayList based on the runtime of
	 * the event.
	 */
	public void addAndSort(Event e) {
		ArrayList<Event> updatedEventList = new ArrayList<Event>();

		/*
		 * Adds all events with shorter runtime than the event we wish to add to an
		 * updated list.
		 */
		for (Event shorterEvent : eventList) {
			if (shorterEvent.getTime() <= e.getTime()) {
				updatedEventList.add(shorterEvent);
			}
		}
		/*
		 * Adds the event itself after the events with lesser runtime has been added.
		 */
		updatedEventList.add(e);

		/*
		 * Adds all events with longer runtime than the event we just added to the list.
		 */
		for (Event lengthierEvent : eventList) {
			if (lengthierEvent.getTime() > e.getTime()) {
				updatedEventList.add(lengthierEvent);
			}
		}
		/*
		 * Reassigns eventList to the new updated list.
		 */
		eventList = updatedEventList;
	}

	/**
	 * Removes the first element in the event list.
	 */
	public void eventDone() {
		Event event = eventList.get(0);
		event.execute();
		eventList.remove(0);

	}

	public boolean isEmpty() {

		return eventList.isEmpty();
	}

	public String getEvent() {

		return eventList.get(0).getEvent();
	}

	public Event getCurrentEvent() {
		return eventList.get(0);
	}

	public ArrayList<Event> getList() {
		return eventList;
	}

	public String toString() {
		return eventList.toString();
	}

}
