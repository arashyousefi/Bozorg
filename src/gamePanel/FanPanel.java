package gamePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class FanPanel extends JPanel {
	private Player player;
	private TitledBorder border;
	private BufferedImage image;

	public FanPanel() {
		setVisible(true);
		try {
			image = ImageIO.read(new File("resources/fans.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		border = new TitledBorder(new LineBorder(Color.WHITE, 0), "");
		setBorder(border);
	}

	public void setPlayer(Player p) {
		player = p;
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		arg0.setColor(Color.WHITE);
		if (player != null) {
			setVisible(true);
			arg0.drawImage(image, 0, 0, this);
			border.setTitle(player.getNumberOfActiveFans() + "");
			border.setTitleColor(Color.WHITE);
		}
	}
}
