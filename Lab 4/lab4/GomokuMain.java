package lab4;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;


public class GomokuMain {

	// Connect client to the game
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GomokuClient gClient = new GomokuClient(4005);
		GomokuGameState gState = new GomokuGameState(gClient);
		GomokuGUI gGui = new GomokuGUI(gState, gClient);
		
	}
	
	
}
