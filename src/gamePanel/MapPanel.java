package gamePanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {

	public MapPanel(int width, int height) {
		setLayout(new GridLayout(width, height));

		setVisible(true);
	}

}
