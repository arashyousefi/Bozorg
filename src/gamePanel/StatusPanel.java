package gamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import bozorg.common.objects.Constants;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private Player player;
	private static final int WIDTH = 200;
	private int height;
	private NameLabel nameLabel;
	private ImagePanel imagePanel;

	public void setPlayer(Player p) {
		player = p;
	}

	public StatusPanel(int height) {
		this.height = height;
		setLayout(null);
		setSize(new Dimension(WIDTH, height));
		initImage();
		initName();
		initPowerUps();
		setVisible(true);
	}

	private void initImage() {
		imagePanel = new ImagePanel();
		add(imagePanel);
		imagePanel.setVisible(true);
		imagePanel.setPlayer(player);
	}

	private void initName() {
		nameLabel = new NameLabel();
		add(nameLabel);
		nameLabel.setVisible(true);
		nameLabel.setPlayer(player);
	}

	private void initPowerUps() {
		PowerUpPanel sight = new PowerUpPanel(Constants.SIGHT);
		PowerUpPanel phase = new PowerUpPanel(Constants.PHASE);
		PowerUpPanel haste = new PowerUpPanel(Constants.HASTE);
		PowerUpPanel stun = new PowerUpPanel(Constants.STUNNED);
		sight.setLocation(25, 150);
		phase.setLocation(25, 200);
		haste.setLocation(25, 250);
		stun.setLocation(25, 300);
		add(stun);
		add(haste);
		add(phase);
		add(sight);

		PowerUpPanel.setPlayer(player);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		arg0.setColor(Color.WHITE);
		arg0.fillRect(0, 0, WIDTH, height);
		updatePlayers();
		super.paintComponent(arg0);
	}

	private void updatePlayers() {
		PowerUpPanel.setPlayer(player);
		nameLabel.setPlayer(player);
		imagePanel.setPlayer(player);
	}
}
