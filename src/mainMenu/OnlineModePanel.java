package mainMenu;

import gameController.GameController;
import gamePanel.BozorgMenuBar;
import gamePanel.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mapCreator.MapCreator;
import bozorg.judge.Judge;

public class OnlineModePanel extends JPanel {
	int[] player;
	JTextField widthField = new JTextField(), heightField = new JTextField(),
			playerField = new JTextField();
	JLabel widthLabel = new JLabel("Map Width:"), heightLabel = new JLabel(
			"Map Height:");
	JLabel widthErrorLable = new JLabel(
			"<html><font color = 'red'>Map width must be between 10 and 100</font></html>");
	JLabel heightErrorLable = new JLabel(
			"<html><font color = 'red'>Map height must be between 10 and 100</font></html>");
	JLabel playerErrorLable = new JLabel(
			"<html><font color = 'red'>Number of players must be between 2 and 4</font></html>");
	MapCreator mapCreator;
	JButton back = new JButton("Back");
	JLabel player1 = new JLabel("Number of Players:");
	JButton startGame = new JButton("Start Game!");

	String[] playerNames = { "Saman", "Jafar", "Reza", "Hasin" };

	private Judge engine;
	private GameController controller;
	private GamePanel panel;

	@SuppressWarnings("deprecation")
	public OnlineModePanel(BozorgMenuFrame menuFrame) {
		this.setLayout(null);
		this.setSize(500, 500);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeToMainMenu();

			}
		});

		startGame.addActionListener(new ActionListener() {
			int width = 0, height = 0, number = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					width = Integer.parseInt(widthField.getText());
					height = Integer.parseInt(heightField.getText());
					number = Integer.parseInt(playerField.getText());

				} catch (Exception e) {
					mapCreator = null;
					return;
				}
				if (width > 100 || width < 10)
					widthErrorLable.show();
				else
					widthErrorLable.hide();
				if (height > 100 || height < 10)
					heightErrorLable.show();
				else
					heightErrorLable.hide();
				if (number < 2 || number > 4)
					playerErrorLable.show();
				else {
					playerErrorLable.hide();
					setPlayers();
				}
				if (heightErrorLable.isVisible() || widthErrorLable.isVisible()
						|| playerErrorLable.isVisible()) {
					mapCreator = null;
					return;
				}
				mapCreator = new MapCreator(width, height, player.length);
				engine = new Judge();
				engine.loadMap(mapCreator.getCellTypes(),
						mapCreator.getWallTypes(), player);
				panel = new GamePanel();
				controller = new GameController();
				controller.init(engine, panel);
				panel.init(engine, controller);
				panel.setJMenuBar(new BozorgMenuBar(engine, panel));
				panel.pack();
				panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				panel.setVisible(true);
				panel.repaint();
				menuFrame.hide();
				controller.start();
				// TODO
			}

			@SuppressWarnings("serial")
			private void setPlayers() {
				player = new int[number];
				Random random = new Random();
				ArrayList<Integer> arr = new ArrayList<Integer>() {
					{
						add(0);
						add(1);
						add(2);
						add(3);
					}
				};
				for (int i = 0; i < number; ++i) {
					int j = random.nextInt(arr.size());
					player[i] = arr.get(j);
					arr.remove(j);
				}

			}
		});

		this.add(widthLabel);
		widthLabel.setSize(100, 25);
		widthLabel.setLocation(25, 50);
		this.add(widthField);
		widthField.setSize(100, 25);
		widthField.setLocation(25, 75);
		this.add(widthErrorLable);
		widthErrorLable.setSize(300, 25);
		widthErrorLable.setLocation(150, 75);
		widthErrorLable.hide();
		this.add(heightLabel);
		heightLabel.setSize(100, 25);
		heightLabel.setLocation(25, 125);
		this.add(heightField);
		heightField.setSize(100, 25);
		heightField.setLocation(25, 150);
		this.add(heightErrorLable);
		heightErrorLable.setSize(300, 25);
		heightErrorLable.setLocation(150, 150);
		heightErrorLable.hide();

		this.add(player1);
		player1.setSize(150, 25);
		player1.setLocation(25, 200);
		this.add(playerField);
		playerField.setSize(100, 25);
		playerField.setLocation(25, 225);
		this.add(playerErrorLable);
		playerErrorLable.setSize(300, 25);
		playerErrorLable.setLocation(150, 225);
		playerErrorLable.hide();

		this.add(startGame);
		startGame.setSize(150, 25);
		startGame.setLocation(40, 350);

		this.add(back);
		back.setSize(100, 25);
		back.setLocation(25, 400);

	}
}
