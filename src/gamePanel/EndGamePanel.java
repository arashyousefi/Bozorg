package gamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EndGamePanel extends JPanel {
	private static BufferedImage[] images = new BufferedImage[4];
	private int type;

	public EndGamePanel(int type) {
		setVisible(true);
		setPreferredSize(new Dimension(400, 500));
		this.type = type;
		initImages();
	}

	private static void initImages() {
		try {
			images[0] = ImageIO.read(new File("resources/1_win.jpg"));
			images[1] = ImageIO.read(new File("resources/2_win.jpg"));
			images[2] = ImageIO.read(new File("resources/3_win.jpg"));
			images[3] = ImageIO.read(new File("resources/4_win.jpg"));
		} catch (Exception e) {

		}
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		arg0.drawImage(images[type], 0, 0, this);
	}

}
