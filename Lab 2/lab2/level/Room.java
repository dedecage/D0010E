package lab2.level;


import java.awt.Color;

public class Room {
	
	Room northDoorway = null;
	Room southDoorway = null;
	Room eastDoorway = null;
	Room westDoorway = null;
	
	Level findObject;
	Color floorColor = null;
	
	int rx;
	int ry;
	int dimx;
	int dimy;
	
	boolean inRoom = false;
	
	public Room(int dx, int dy, Color color) {
	
		dimx = dx;
		dimy = dy;
		floorColor = color;
	}

	public void connectNorthTo(Room r) {
		if (this.findObject == r.findObject) {
		northDoorway = r;
		}
		
	}
	public void connectEastTo(Room r) {
		if (this.findObject == r.findObject) {
		eastDoorway = r;
		}
	}
	public void connectSouthTo(Room r) {
		if (this.findObject == r.findObject) {
		southDoorway = r;
		
		}
	}
	public void connectWestTo(Room r) {
		if (this.findObject == r.findObject) {
		westDoorway = r;
		}
		
	}

}