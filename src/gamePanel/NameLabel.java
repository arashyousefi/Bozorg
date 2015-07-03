package gamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class NameLabel extends JLabel {
	private Player player;
	private String[] names;

	public NameLabel() {
		initNames();
		setBounds(25, 110, 150, 30);
		setHorizontalAlignment(CENTER);
		setFont(new Font(this.getName(), Font.PLAIN, 30));
		setForeground(Color.BLACK);
	}

	private void initNames() {
		names = new String[4];
		names[0] = "Saman";
		names[1] = "Jafar";
		names[2] = "Reza";
		names[3] = "Hasin";
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		if (player != null)
			setText(names[player.getName()]);
		else
			setText("");
		super.paintComponent(arg0);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
