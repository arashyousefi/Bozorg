package gamePanel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private Player player;
	private static final int WIDTH = 200;
	private int height;

	public StatusPanel(int height) {
		this.height = height;
		setLayout(null);
		setVisible(true);
		setSize(WIDTH, height);
		initImage();
		initName();
		initPowerUps();

	}

	private void initImage() {
		JPanel imagePanel = new ImagePanel(player);
		add(imagePanel);
		imagePanel.setVisible(true);
		imagePanel.setLocation(25, 25);
	}

	private void initName() {
		NameLabel nameLabel = new NameLabel(player);
		add(nameLabel);
		nameLabel.setVisible(true);
		nameLabel.setLocation(25, 110);
	}

	private void initPowerUps() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void paintComponent(Graphics arg0) {
		arg0.setColor(Color.WHITE);
		arg0.fillRect(0, 0, WIDTH, height);
		super.paintComponent(arg0);
	}
}
