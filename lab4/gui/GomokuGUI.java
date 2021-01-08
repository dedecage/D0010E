package lab4.gui;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;


public class GomokuGUI implements Observer {

	private GomokuClient client;
	private GomokuGameState gamestate;
	private JLabel messageLabel;
	private GamePanel gameGridPanel;
	private JButton connectButton;
	private JButton newGameButton;
	private JButton disconnectButton;

	/**
	 * The constructor
	 * 
	 * @param g The game state that the GUI will visualize
	 * @param c The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c) {

		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);

		// construction of game buttons
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");

		JFrame frame = new JFrame("Gomoku!");
		
		// container is set as contentPane
		Container container = frame.getContentPane();
		
		messageLabel = new JLabel("Welcome to Gomoku!");

		gameGridPanel = new GamePanel(gamestate.getGameGrid());
		
		JPanel contentPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		// gameGridPanel adds a listener using an anonymous class
		gameGridPanel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int[] pos = gameGridPanel.getGridPosition(x, y);
				gamestate.move(pos[0], pos[1]);
			}
		});

		// ConnectButton adds a listener using an anonymous class
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ConnectionWindow(client);
			}
		});

		// newGameButton adds a listener using an anonymous class
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gamestate.newGame();
			}
		});

		// disconnectButton adds a listener using an anonymous class
		disconnectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gamestate.disconnect();
			}
		});

		// adds the buttons to the buttonPanel
		buttonPanel.add(connectButton);
		buttonPanel.add(newGameButton);
		buttonPanel.add(disconnectButton);
		
		// adds buttonPanel, messageLabel and gamegridPanel to the contentPanel.
		contentPanel.add(buttonPanel);
		contentPanel.add(messageLabel);
		contentPanel.add(gameGridPanel);
		
		// saves the values of the buttonPanel and gameGridPanel
		int buttonWidth = buttonPanel.getPreferredSize().width;
		int panelHeight = gameGridPanel.getPreferredSize().height + 60;
		
		// sets the width on the contentPanel based on the width of buttonWidth/gameGridPanel
		if (buttonWidth > gameGridPanel.getPreferredSize().width) {
			contentPanel.setPreferredSize(new Dimension(buttonWidth, panelHeight));
		} else {
			contentPanel.setPreferredSize(new Dimension(gameGridPanel.getPreferredSize().width, panelHeight));
		}
		
		SpringLayout layout = new SpringLayout();
		
		// sets constraints on the layout so that everything is ordered properly
		layout.putConstraint(SpringLayout.NORTH, buttonPanel, 5, SpringLayout.SOUTH, gameGridPanel);
		layout.putConstraint(SpringLayout.NORTH, messageLabel, 0, SpringLayout.SOUTH, buttonPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gameGridPanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttonPanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, messageLabel, 0, SpringLayout.HORIZONTAL_CENTER, contentPanel);
		contentPanel.setLayout(layout);
		
		// adds a small amount of padding to the components
		container.setLayout(new FlowLayout());
		container.add(contentPanel);
		
		// turns off newGameButton and disconnectButton when no connection has been established
		newGameButton.setEnabled(false);
		disconnectButton.setEnabled(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(0, 0);
		
	}

	public void update(Observable arg0, Object arg1) {

		// Update the buttons if the connection status has changed
		if (arg0 == client) {
			if (client.getConnectionStatus() == GomokuClient.UNCONNECTED) {
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			} else {
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}

		// Update the status text if the gamestate has changed
		if (arg0 == gamestate) {
			messageLabel.setText(gamestate.getMessageString());
		}

	}

}