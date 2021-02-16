package simulator;


public abstract class Event {

	public abstract String getEvent();

	public abstract String getCustomerId();

	public abstract void execute();

	public abstract double getTime();

}
