package gamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import bozorg.common.objects.Constants;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private static final int WIDTH = 200;
	private int height;
	private NameLabel nameLabel;
	private ImagePanel imagePanel;
	private PowerPanel power;
	private HealthPanel health;
	private SpeedPanel speed;

	public void setPlayer(Player player) {
		PowerUpPanel.setPlayer(player);
		nameLabel.setPlayer(player);
		imagePanel.setPlayer(player);
		power.setPlayer(player);
		speed.setPlayer(player);
		health.setPlayer(player);
		if (player == null)
			setVisible(false);
		else
			setVisible(true);
	}

	public StatusPanel(int height) {
		this.height = height;
		setLayout(null);
		setPreferredSize(new Dimension(WIDTH, height));
		initImage();
		initName();
		initStats();
		initPowerUps();
		setVisible(true);
	}

	private void initStats() {
		health = new HealthPanel();
		health.setBounds(0, 140, 50, 50);
		speed = new SpeedPanel();
		speed.setBounds(50, 140, 50, 50);
		power = new PowerPanel();
		power.setBounds(100, 140, 100, 150);
		add(health);
		add(speed);
		add(power);

	}

	private void initImage() {
		imagePanel = new ImagePanel();
		add(imagePanel);
		imagePanel.setVisible(true);
	}

	private void initName() {
		nameLabel = new NameLabel();
		add(nameLabel);
		nameLabel.setVisible(true);
	}

	private void initPowerUps() {
		PowerUpPanel sight = new PowerUpPanel(Constants.SIGHT);
		PowerUpPanel phase = new PowerUpPanel(Constants.PHASE);
		PowerUpPanel haste = new PowerUpPanel(Constants.HASTE);
		PowerUpPanel stun = new PowerUpPanel(Constants.STUNNED);
		add(stun);
		add(haste);
		add(phase);
		add(sight);

	}

	@Override
	protected void paintComponent(Graphics arg0) {
		arg0.setColor(Color.WHITE);
		arg0.fillRect(0, 0, WIDTH, height);
	}

}
