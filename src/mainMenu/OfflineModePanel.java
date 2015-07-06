package mainMenu;

import gameController.GameController;
import gamePanel.BozorgMenuBar;
import gamePanel.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mapCreator.MapCreator;
import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class OfflineModePanel extends JPanel {
	int[] player = { 0, 1 };
	JTextField widthField = new JTextField(), heightField = new JTextField();
	JLabel widthLabel = new JLabel("Map Width:"), heightLabel = new JLabel(
			"Map Height:");
	JLabel widthErrorLable = new JLabel(
			"<html><font color = 'red'>Map width must be between 10 and 100</font></html>");
	JLabel heightErrorLable = new JLabel(
			"<html><font color = 'red'>Map height must be between 10 and 100</font></html>");
	MapCreator mapCreator;
	JButton back = new JButton("Back");
	JLabel player1 = new JLabel("Player 1:");
	JLabel player2 = new JLabel("Player 2:");
	JButton startGame = new JButton("Start Game!");
	JLabel startGameError = new JLabel(
			"<html><font color = 'red'>First create a map, then start the game!</font></html>");

	String[] playerNames = { "Saman", "Jafar", "Reza", "Hasin" };
	JRadioButton[] radioGroup1 = { null, null, null, null };
	JRadioButton[] radioGroup2 = { null, null, null, null };
	ButtonGroup group1 = new ButtonGroup(), group2 = new ButtonGroup();

	private Judge engine;
	private GameController controller;
	private GamePanel panel;

	@SuppressWarnings("deprecation")
	public OfflineModePanel(BozorgMenuFrame menuFrame) {
		this.setLayout(null);
		this.setSize(500, 500);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeToMainMenu();

			}
		});

		for (int i = 0; i < 4; ++i) {
			radioGroup1[i] = new JRadioButton(playerNames[i]);
			radioGroup2[i] = new JRadioButton(playerNames[i]);
		}

		radioGroup1[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioGroup2[0].hide();
				radioGroup2[1].show();
				radioGroup2[2].show();
				radioGroup2[3].show();
				player[0] = 0;
				if (player[1] == 0) {
					radioGroup2[1].setSelected(true);
					player[1] = 1;
				}
			}
		});

		radioGroup1[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioGroup2[0].show();
				radioGroup2[1].hide();
				radioGroup2[2].show();
				radioGroup2[3].show();
				player[0] = 1;
				if (player[1] == 1) {
					radioGroup2[2].setSelected(true);
					player[1] = 2;
				}
			}
		});

		radioGroup1[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioGroup2[0].show();
				radioGroup2[1].show();
				radioGroup2[2].hide();
				radioGroup2[3].show();
				player[0] = 2;
				if (player[1] == 2) {
					radioGroup2[3].setSelected(true);
					player[1] = 3;
				}
			}
		});

		radioGroup1[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioGroup2[0].show();
				radioGroup2[1].show();
				radioGroup2[2].show();
				radioGroup2[3].hide();
				player[0] = 3;
				if (player[1] == 3) {
					radioGroup2[0].setSelected(true);
					player[1] = 0;
				}
			}
		});

		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int width = 0, height = 0;
				try {
					width = Integer.parseInt(widthField.getText());
					height = Integer.parseInt(heightField.getText());
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
				if (heightErrorLable.isVisible() || widthErrorLable.isVisible()) {
					mapCreator = null;
					return;
				}
				mapCreator = new MapCreator(width, height, 2);

				if (mapCreator == null) {
					startGameError.show();
					return;
				}
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
		});

		radioGroup2[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player[1] = 0;
			}
		});

		radioGroup2[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player[1] = 1;
			}
		});

		radioGroup2[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player[1] = 2;
			}
		});

		radioGroup2[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player[1] = 3;
			}
		});
		radioGroup1[0].setSelected(true);
		radioGroup2[1].setSelected(true);
		radioGroup2[0].hide();

		for (int i = 0; i < 4; ++i) {
			group1.add(radioGroup1[i]);
			group2.add(radioGroup2[i]);

		}

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
		player1.setSize(100, 25);
		player1.setLocation(25, 225);
		this.add(player2);
		player2.setSize(100, 25);
		player2.setLocation(150, 225);

		for (int i = 0; i < 4; ++i) {
			this.add(radioGroup1[i]);
			radioGroup1[i].setSize(100, 25);
			radioGroup1[i].setLocation(25, 250 + 25 * i);
		}
		for (int i = 0; i < 4; ++i) {
			this.add(radioGroup2[i]);
			radioGroup2[i].setSize(100, 25);
			radioGroup2[i].setLocation(150, 250 + 25 * i);
		}

		this.add(startGame);
		startGame.setSize(150, 25);
		startGame.setLocation(40, 350);

		this.add(startGameError);
		startGameError.setSize(300, 25);
		startGameError.setLocation(200, 350);
		startGameError.hide();

		this.add(back);
		back.setSize(100, 25);
		back.setLocation(25, 400);

	}
}
