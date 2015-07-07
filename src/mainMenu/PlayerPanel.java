package mainMenu;

import gameController.GameController;
import gamePanel.BozorgMenuBar;
import gamePanel.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.Client;
import mapCreator.MapCreator;
import bozorg.judge.Judge;

public class PlayerPanel extends JPanel {
	JTextField firstField = new JTextField(), secondField = new JTextField(),
			thirdField = new JTextField(), fourthField = new JTextField(),
			portField = new JTextField();
	JLabel ipLabel = new JLabel("Server IP:"), portLabel = new JLabel("Port:");
	JLabel ipErrorLable = new JLabel(
			"<html><font color = 'red'>Port must be between 49152 and 65535</font></html>");
	JLabel portErrorLable = new JLabel(
			"<html><font color = 'red'>Port must be between 49152 and 65535</font></html>");
	MapCreator mapCreator;
	JButton back = new JButton("Back");
	JButton startGame = new JButton("join");

	private Judge engine;
	private GameController controller;
	private GamePanel panel;

	@SuppressWarnings("deprecation")
	public PlayerPanel(BozorgMenuFrame menuFrame) {
		this.setLayout(null);
		this.setSize(500, 500);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuFrame.changeToOnlineMode();

			}
		});

		startGame.addActionListener(new ActionListener() {
			int first = -1, second = -1, third = -1, fourth = -1, port = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					first = Integer.parseInt(firstField.getText());
					second = Integer.parseInt(secondField.getText());
					third = Integer.parseInt(thirdField.getText());
					fourth = Integer.parseInt(fourthField.getText());
					port = Integer.parseInt(portField.getText());
				} catch (Exception e) {
					mapCreator = null;
					return;
				}
				if (first > 255 || first < 0 || second > 255 || second < 0
						|| third > 255 || third < 0 || fourth > 255
						|| fourth < 0)
					ipErrorLable.setVisible(true);
				else
					ipErrorLable.setVisible(false);
				if (port > 65535 || port < 49152)
					portErrorLable.setVisible(true);
				else
					portErrorLable.setVisible(false);
				if (ipErrorLable.isVisible() || portErrorLable.isVisible()) {
					mapCreator = null;
					return;
				}
				// TODO do something here
				String IP = "" + first + "." + second + "." + third + "."
						+ fourth;
				Client client = new Client(IP, port);
				menuFrame.hide();
			}

		});

		this.add(ipLabel);
		ipLabel.setSize(100, 25);
		ipLabel.setLocation(25, 50);

		this.add(firstField);
		firstField.setSize(50, 25);
		firstField.setLocation(25, 75);
		this.add(secondField);
		secondField.setSize(50, 25);
		secondField.setLocation(85, 75);
		this.add(thirdField);
		thirdField.setSize(50, 25);
		thirdField.setLocation(145, 75);
		this.add(fourthField);
		fourthField.setSize(50, 25);
		fourthField.setLocation(205, 75);
		this.add(ipErrorLable);
		ipErrorLable.setSize(300, 25);
		ipErrorLable.setLocation(25, 100);
		ipErrorLable.hide();

		this.add(portLabel);
		portLabel.setSize(50, 25);
		portLabel.setLocation(25, 275);
		this.add(portField);
		portField.setSize(50, 25);
		portField.setLocation(25, 300);
		this.add(portErrorLable);
		portErrorLable.setSize(300, 25);
		portErrorLable.setLocation(150, 300);
		portErrorLable.hide();

		this.add(startGame);
		startGame.setSize(150, 25);
		startGame.setLocation(40, 350);

		this.add(back);
		back.setSize(100, 25);
		back.setLocation(25, 400);

	}
}
