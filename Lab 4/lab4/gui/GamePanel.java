package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;


public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 10;
	private GameGrid grid;
	private Color myColor = Color.blue;
	private Color otherColor = Color.red;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, 
				grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		int[] list = { x / UNIT_SIZE, y / UNIT_SIZE };
		return list;
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Draws out the grid based on UNIT_SIZE
		for (int x = 0; x < grid.getSize(); x++) {
			for (int y = 0; y < grid.getSize(); y++) {
				g.drawRect(x*UNIT_SIZE, y*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				
			}
			
		}
		
		// Draws a circle if a square is occupied
		for ( int i = 0; i < grid.getSize(); i++ ) {
			for ( int j = 0; j < grid.getSize(); j++ ) {
				if (grid.getLocation(i, j) == GameGrid.ME) {
					g.setColor(myColor);
					g.fillOval( (i * UNIT_SIZE), (j * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
				} else if (grid.getLocation(i, j) == GameGrid.OTHER) {
					g.setColor(otherColor);
					g.fillOval( (i * UNIT_SIZE), (j * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
				}
			}
		}
	}
	
}