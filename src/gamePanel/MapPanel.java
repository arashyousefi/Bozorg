package gamePanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private static Player player;

	public MapPanel(int width, int height) {
		setLayout(new GridLayout(width, height));

		setVisible(true);
	}

	public static void setPlayer(Player p) {
		player = p;
	}

	public static Player getPlayer() {
		return player;
	}

}
