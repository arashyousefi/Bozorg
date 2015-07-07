package mainMenu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BozorgMenuFrame extends JFrame {
	private JPanel panel;

	public BozorgMenuFrame() {
		this.setSize(500, 500);
		this.setTitle("Bozorg");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new MainMenuPanel(this);
		try {
			this.setIconImage(ImageIO.read(new File("resources/icon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(panel);
		this.setVisible(true);

	}

	public void changeToOnlineMode() {
		this.remove(panel);
		panel = new OnlineModePanel(this);
		this.add(panel);
		this.repaint();
	}

	public void changeToOfflineMode() {
		this.remove(panel);
		panel = new OfflineModePanel(this);
		this.add(panel);
		this.repaint();
	}

	public void changeToMainMenu() {
		this.remove(panel);
		panel = new MainMenuPanel(this);
		this.add(panel);
		this.repaint();
	}

	public void changeServerPanel() {
		this.remove(panel);
		panel = new ServerPanel(this);
		this.add(panel);
		this.repaint();

	}

	public void changePlayerPanel() {
		this.remove(panel);
		panel = new PlayerPanel(this);
		this.add(panel);
		this.repaint();

	}
}
