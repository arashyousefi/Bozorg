package gamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import bozorg.common.objects.Constants;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private Player player;
	private static BufferedImage[] images = new BufferedImage[4];
	private static BufferedImage[] deadImages = new BufferedImage[4];

	public ImagePanel() {

		setBounds(25, 25, 150, 85);
		initBufferedImages();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	private static void initBufferedImages() {
		try {
			images[0] = ImageIO.read(new File("resources/1.png"));
			images[1] = ImageIO.read(new File("resources/2.png"));
			images[2] = ImageIO.read(new File("resources/3.png"));
			images[3] = ImageIO.read(new File("resources/4.png"));
			deadImages[0] = ImageIO.read(new File("resources/dead_1.png"));
			deadImages[1] = ImageIO.read(new File("resources/dead_2.png"));
			deadImages[2] = ImageIO.read(new File("resources/dead_3.png"));
			deadImages[3] = ImageIO.read(new File("resources/dead_4.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		if (player != null)
			if (player.getInfo(Constants.IS_ALIVE) == Constants.ALIVE)
				arg0.drawImage(images[player.getName()], 0, 0, this);
			else
				arg0.drawImage(deadImages[player.getName()], 0, 0, this);

	}
}
