package lab4.data;

import java.util.Observable;


public class GameGrid extends Observable {

	public static final int ME = 1;
	public static final int OTHER = 2;
	public static final int EMPTY = 3;
	private int[][] list;
	private static final int INROW = 3;

	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid Creates a grid of numbers
	 *             starting from 1 to size * size;
	 */
	public GameGrid(int size) {
		list = new int[size][size];
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++) {
				list[i][j] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y) {
		return list[x][y];
	}

	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize() {
		return list.length;
	}

	/**
	 * Enters a move in the game grid
	 * 
	 * @param x      the x position
	 * @param y      the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player) {
		if (list[x][y] == EMPTY) {
			list[x][y] = player;
			setChanged();
			notifyObservers();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid() {
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++) {
				list[i][j] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Checks win condition
	 * 
	 * @param player the player to check for
	 * @return true if player has INROW in row, false otherwise
	 */
	public boolean isWinner(int player) {

		int counter = 0;
		int span = 0;

		for (int x = 0; x < list.length; x++) {
			for (int y = 0; y < list.length; y++) {

				// Vertically spanning to the right
				while (span < INROW && x + span < list.length && list[x + span++][y] == player) {
					counter++;
					if (counter == INROW) {
						return true;
					}
				}
				counter = 0;
				span = 0;

				// Horizontally spanning downwards
				while (span < INROW && y + span < list.length && list[x][y + span++] == player) {
					counter++;
					if (counter == INROW) {
						return true;
					}
				}
				counter = 0;
				span = 0;

				// Down right diagonal
				while (x + span < list.length && y + span < list.length && list[x + span][y + span++] == player) {
					counter++;
					if (counter == INROW) {
						return true;
					}
				}
				counter = 0;
				span = 0;

				// Down left diagonal
				while (x - span >= 0 && y + span < list.length && list[x - span][y + span++] == player) {
					counter++;
					if (counter == INROW) {
						return true;
					}
				}
				counter = 0;
				span = 0;
			}
		}
		return false;
	}
}
