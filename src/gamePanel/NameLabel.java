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

	public NameLabel(Player player) {
		this.player = player;
		initNames();
		setSize(150, 20);
		setHorizontalAlignment(CENTER);
		setFont(new Font(this.getName(), Font.PLAIN, 30));
		setForeground(Color.white);
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
		setText(names[player.getName()]);
		super.paintComponent(arg0);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
