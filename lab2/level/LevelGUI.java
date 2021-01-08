package lab2.level;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {


	private Level lv;
	private Display d;

	public LevelGUI(Level level, String name) {

		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: You should change 200 to a value
		// depending on the size of the level
		d = new Display(lv, 200, 200);

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0, 0);
		frame.setVisible(true);
		lv.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}

	private class Display extends JPanel {

		public Display(Level fp, int x, int y) {

			addKeyListener(new Listener());

			setBackground(Color.GREEN);
			setPreferredSize(new Dimension(x + 20, y + 20));
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			colorRoom(g);
			buildBridge(g);
			selectRoom(g);

		}

		private void colorRoom(Graphics g) {

			Room r;

			for (int i = 0; i < lv.list.length; i++) {

				r = lv.list[i];

				g.setColor(r.floorColor);
				g.fillRect(r.rx, r.ry, r.dimx, r.dimy);
			}

		}

		private void buildBridge(Graphics g) {

			Room r;

			for (int i = 0; i < lv.list.length; i++) {

				r = lv.list[i];
				g.setColor(Color.black);
				
				if (r.northDoorway != null) {
					g.drawLine((r.rx + r.dimx / 2), (r.ry + r.dimy / 2), (r.northDoorway.rx + r.northDoorway.dimx / 2),
							(r.northDoorway.ry + r.northDoorway.dimy / 2));
					}
				if (r.southDoorway != null) {
					g.drawLine((r.rx + r.dimx / 2), (r.ry + r.dimy / 2), (r.southDoorway.rx + r.southDoorway.dimx / 2),
							(r.southDoorway.ry + r.southDoorway.dimy / 2));

				}
				if (r.westDoorway != null) {
					g.drawLine((r.rx + r.dimx / 2), (r.ry + r.dimy / 2), (r.westDoorway.rx + r.westDoorway.dimx / 2),
							(r.westDoorway.ry + r.westDoorway.dimy / 2));

				}
				if (r.eastDoorway != null) {
					g.drawLine((r.rx + r.dimx / 2), (r.ry + r.dimy / 2), (r.eastDoorway.rx + r.eastDoorway.dimx / 2),
							(r.eastDoorway.ry + r.eastDoorway.dimy / 2));

				}

			}

		}

		private void selectRoom(Graphics g) {

			Room r;

			for (int i = 0; i < lv.list.length; i++) {

				r = lv.list[i];

				if (r.inRoom == true) {
					g.setColor(Color.white);
					if(r.eastDoorway != null) {
						g.fillOval(( r.rx + r.dimx - 16), (r.ry + r.dimy / 2 - 8), 
								15, 15);
					}if(r.southDoorway != null) {
						g.fillOval(( r.rx + r.dimx / 2 - 8), (r.ry + r.dimy - 16), 
								15, 15);
					}if(r.westDoorway != null) {
						g.fillOval((r.rx), (r.ry + r.dimy / 2 - 8), 
								15, 15);
					}if(r.northDoorway != null) {
						g.fillOval(( r.rx + r.dimx / 2 - 8), (r.ry), 
								15, 15);
					}
				}

			}

		}

		private class Listener implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent event) {
				
				switch ( event.getKeyChar()) {
				
				case 'w' : 
					lv.moveNorth(findRoom()); 
					break;
				case 's' :
					lv.moveSouth(findRoom());
					break;
				case 'a' :
					lv.moveWest(findRoom());
					break;
				case 'd' :
					lv.moveEast(findRoom());
					break;
				default :
					System.out.println("Invalid key!");
					break;
				}
			}
			private Room findRoom() {
				for (int i = 0; i < lv.list.length; i++) {
					Room r = lv.list[i];
					if (r.inRoom == true) {
						return r;
					}
				}
				return lv.list[-1];
			}
		}
	}

}
