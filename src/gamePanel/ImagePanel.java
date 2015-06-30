package gamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private Player player;
	private BufferedImage[] images = new BufferedImage[4];

	public ImagePanel() {

		setBounds(25, 25, 150, 85);
		initBufferedImages();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	private void initBufferedImages() {
		try {
			images[0] = ImageIO.read(new File("resources/1.png"));
			images[1] = ImageIO.read(new File("resources/2.png"));
			images[2] = ImageIO.read(new File("resources/3.png"));
			images[3] = ImageIO.read(new File("resources/4.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		if (player != null)
			arg0.drawImage(images[player.getName()], 0, 0, this);
	}
}
