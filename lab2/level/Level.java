package lab2.level;


import java.util.Observable;


public class Level extends Observable {
	
	boolean inPlace = false;
	

	Room[] list = new Room[0];

	public Level() {

	}

	public boolean place(Room r, int x, int y) {

		if (inPlace == false) {
			for (int i = 0; i < list.length; i++) {
				double distancex = Math.abs((list[i].rx + (list[i].dimx / 2)) - (x + (r.dimx / 2)));
				double distancey = Math.abs((list[i].ry + (list[i].dimy / 2)) - (y + (r.dimy / 2)));
				if (distancex < ((r.dimx / 2) + (list[i].dimx / 2))
						&& distancey < ((r.dimy / 2) + (list[i].dimy / 2))) {
					return false;
				}
			} 
			r.findObject = this;
			r.rx = x;
			r.ry = y;
			incList(r);
		}
		return true;
	}

	public void firstLocation(Room r) {
		if (inPlace == false) {
			inPlace = true;
			r.inRoom = true;
		}

	}

	private void incList(Room r) {

		Room[] templist = new Room[list.length + 1];
		for (int i = 0; i < list.length; i++) {
			templist[i] = list[i];
		}
		templist[templist.length - 1] = r;
		list = templist;
	}

	public void moveNorth(Room r) {
		if (r.northDoorway != null) {

			r.inRoom = false;
			r.northDoorway.inRoom = true;
			System.out.println("w is pressed");
			setChanged();
			notifyObservers();
		}
		else {
		System.out.println("Please choose another direction!");
		}

	}

	public void moveSouth(Room r) {
		if (r.southDoorway != null) {
			
			r.inRoom = false;
			r.southDoorway.inRoom = true;
			System.out.println("s is pressed");
			setChanged();
			notifyObservers();
		}
		else {
		System.out.println("Please choose another direction!");
		}
	}

	public void moveWest(Room r) {
		if (r.westDoorway != null) {

			r.inRoom = false;
			r.westDoorway.inRoom = true;
			System.out.println("a is pressed");
			setChanged();
			notifyObservers();

		}
		else {
		System.out.println("Please choose another direction!");
		}
	}

	public void moveEast(Room r) {
		if (r.eastDoorway != null) {

			r.inRoom = false;
			r.eastDoorway.inRoom = true;
			System.out.println("d is pressed");
			setChanged();
			notifyObservers();
		}
		else {
		System.out.println("Please choose another direction!");
		}

	}
}