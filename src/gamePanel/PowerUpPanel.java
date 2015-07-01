package gamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class PowerUpPanel extends JPanel {
	private static Player player;
	private static BufferedImage[] pws;
	int type;

	public PowerUpPanel(int type) {
		initPowerUps();
		this.type = type;
		setPreferredSize(new Dimension(50, 50));
	}

	private static void initPowerUps() {
		pws = new BufferedImage[4];
		try {
			pws[0] = ImageIO.read(new File("resources/haste.png"));
			pws[1] = ImageIO.read(new File("resources/gem.png"));
			pws[2] = ImageIO.read(new File("resources/stun.jpg"));
			pws[3] = ImageIO.read(new File("resources/phase.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setPlayer(Player player) {
		PowerUpPanel.player = player;
	}

	@Override
	protected void paintComponent(Graphics arg0) {

		if (player != null)
			arg0.drawImage(pws[type], 0, 0, this);
		if (player != null)
			if (player.hasPowerUp(type))
				setVisible(true);
			else
				setVisible(false);
	}
}