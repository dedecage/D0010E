/*
 * Created on 2007 feb 8
 */
package lab4.data;

import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;


public class GomokuGameState extends Observable implements Observer{


	// Game variables
	private final int DEFAULT_SIZE = 10;
	private GameGrid gameGrid;
	
    	//Possible game states
	private final int NOT_STARTED = 0;
	private static final int MY_TURN = 1;
	private static final int OTHERS_TURN = 2;
	private static final int FINISHED = 3;
	private int currentState;
	
	private GomokuClient client;
	
	private String message;
	
	/**
	 * The constructor
	 * 
	 * @param gc The client used to communicate with the other player
	 */
	public GomokuGameState(GomokuClient gc){
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		gameGrid = new GameGrid(DEFAULT_SIZE);
	}
	

	/**
	 * Returns the message string
	 * 
	 * @return the message string
	 */
	public String getMessageString(){
		return message;
	};
	
	/**
	 * Returns the game grid
	 * 
	 * @return the game grid
	 */
	public GameGrid getGameGrid(){
		return gameGrid;
	}

	/**
	 * This player makes a move at a specified location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void move(int x, int y){

		if(currentState == NOT_STARTED) {
			message = "A game has not yet started";
			setNot();
		} else if (currentState == FINISHED) {
			message = "The game has ended";
			setNot();
		} else if(currentState == MY_TURN) {
			if(gameGrid.move(x, y, GameGrid.ME)) {
				message = "You have made your move";
				client.sendMoveMessage(x, y);
				if(gameGrid.isWinner(GameGrid.ME)) {
					message = "You win! Congratulations";
					currentState = FINISHED;
					setNot();
				} else {
					currentState = OTHERS_TURN;
					setNot();
				}
			} else {
				message = "Illegal move";
				setNot();
			}
		} else {
			message = "You'll have to wait your turn";
			setNot();
		}
		
	}
	
	/**
	 * Starts a new game with the current client
	 */
	public void newGame(){
		gameGrid.clearGrid();
		currentState = OTHERS_TURN;
		message = "A new game has begun, opponents turn to move";
		client.sendNewGameMessage();
		setNot();
	}
	
	/**
	 * Other player has requested a new game, so the 
	 * game state is changed accordingly
	 */
	public void receivedNewGame(){
		gameGrid.clearGrid();
		currentState = MY_TURN;
		message = "A new game has started, your move";
		setNot();
	}
	
	/**
	 * The connection to the other player is lost, 
	 * so the game is interrupted
	 */
	public void otherGuyLeft(){
		gameGrid.clearGrid();
		currentState = NOT_STARTED;
		message = "Your opponent has disconnected.";
		client.disconnect();
		setNot();
	}
	
	/**
	 * The player disconnects from the client
	 */
	public void disconnect(){
		gameGrid.clearGrid();
		currentState = NOT_STARTED;
		message = "You have disconnected";
		client.disconnect();
		setNot();
	}
	
	/**
	 * The player receives a move from the other player
	 * 
	 * @param x The x coordinate of the move
	 * @param y The y coordinate of the move
	 */
	public void receivedMove(int x, int y){
		gameGrid.move(x, y, GameGrid.OTHER);
		if(gameGrid.isWinner(GameGrid.OTHER)) {
			message = "You have lost the game";
			currentState = FINISHED;
			setNot();
		} else {
			message = "Your turn to move";
			currentState = MY_TURN;
			setNot();
		}
	}
	
	public void update(Observable o, Object arg) {
		
		switch(client.getConnectionStatus()){
		case GomokuClient.CLIENT:
			message = "Game started, it is your turn!";
			currentState = MY_TURN;
			break;
		case GomokuClient.SERVER:
			message = "Game started, waiting for other player...";
			currentState = OTHERS_TURN;
			break;
		}
		setNot();
	}
	
	/**
	 * A convenient method for setChanged and notifyObservers. Ensures
	 * that one does not have to type out both commands every time one
	 * wants to use the commands.
	 */
	public void setNot() {
		setChanged();
		notifyObservers();
	}
	
}