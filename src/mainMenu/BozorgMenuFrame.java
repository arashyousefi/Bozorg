package mainMenu;

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
		this.add(panel);
		this.setVisible(true);

	}

	public void changeToOnlineMode() {
		// TODO
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
}
