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
	private FanPanel fans;

	public void setPlayer(Player player) {
		PowerUpPanel.setPlayer(player);
		nameLabel.setPlayer(player);
		imagePanel.setPlayer(player);
		power.setPlayer(player);
		speed.setPlayer(player);
		health.setPlayer(player);
		fans.setPlayer(player);

		if (player == null) {
			nameLabel.setVisible(false);
			imagePanel.setVisible(false);
			power.setVisible(false);
			speed.setVisible(false);
			health.setVisible(false);
			fans.setVisible(false);

		} else {
			nameLabel.setVisible(true);
			imagePanel.setVisible(true);
			power.setVisible(true);
			speed.setVisible(true);
			health.setVisible(true);
			fans.setVisible(true);

		}
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
		health.setBounds(0, 140, 40, 40);
		speed = new SpeedPanel();
		speed.setBounds(40, 140, 40, 40);
		power = new PowerPanel();
		power.setBounds(120, 140, 80, 30);
		fans = new FanPanel();
		fans.setBounds(80, 140, 40, 40);
		add(health);
		add(speed);
		add(power);
		add(fans);
		

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
