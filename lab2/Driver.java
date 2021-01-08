package lab2;


import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {
	
public void run() {
	
	Room r1 = new Room (80, 80, Color.blue);
	Room r2 = new Room (80, 80, Color.magenta);
	Room r3 = new Room (80, 80, Color.orange);
	Room r4 = new Room (80, 80, Color.yellow);
	Room r5 = new Room (80, 80, Color.pink);
	Room r6 = new Room (80, 80, Color.red);
	Room r7 = new Room (80, 80, Color.cyan);
	
	Level level = new Level();
	
	
	
	level.place(r1, 10, 10);
	level.place(r2, 150, 10);
	level.place(r3, 270, 10);
	level.place(r4, 10, 150 );
	level.place(r5, 270, 150);
	level.place(r6, 10, 10);

	
	r1.connectEastTo(r2);
	r1.connectSouthTo(r4);
	r2.connectWestTo(r1);
	r2.connectEastTo(r3);
	r2.connectSouthTo(r5);
	r3.connectWestTo(r2);
	r3.connectSouthTo(r6);
	r4.connectNorthTo(r1);
	r4.connectEastTo(r5);
	r5.connectWestTo(r4);
	r5.connectNorthTo(r2);
	r5.connectEastTo(r6);
	r6.connectWestTo(r5);
	r6.connectNorthTo(r3);
	
	level.firstLocation(r5);
	
	level.place(r7, 150, 250);
	
	LevelGUI levelGUI = new LevelGUI(level, "Rooms");
	
	}


}
