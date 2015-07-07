package gamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bozorg.common.objects.Block;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Fan;
import bozorg.common.objects.Person;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class CellPanel extends JPanel {
	private static int[] CELL_COORDSX = { 0, 8, 15, 23 };
	private static int[] CELL_COORDSY = { 0, 8, 15, 23 };
	private static int CELL_COORDSW = 7;
	private static int CELL_COORDSH = 7;
	Color[] COLOR = { Color.blue, Color.red, Color.yellow, Color.green };

	private Block block;
	private int wallType;
	private Player player;

	public CellPanel(Block block) {
		this.setPreferredSize(new Dimension(30, 30));
		this.block = block;
		wallType = block.getWallType();
		setBorders();
	}

	private void setBorders() {
		wallType = block.getWallType();
		int borderSize = 2;
		int top = ((wallType & 1) != 0 ? borderSize : 0);
		int right = ((wallType & 8) != 0 ? borderSize : 0);
		int bot = ((wallType & 4) != 0 ? borderSize : 0);
		int left = ((wallType & 2) != 0 ? borderSize : 0);
		this.setBorder(BorderFactory.createMatteBorder(top, right, bot, left,
				Color.black));
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintPlayers(g);
		player = MapPanel.getPlayer();
		if (!block.isSeenBy(player)) {
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			return;
		}
		paintFans(g);
		if (block.getCellType(player) == Constants.BONUS_CELL) {
			g.setColor(Color.pink);
			g.fillRect(0, 15, 30, 7);
		}
		if (block.getCellType(player) == Constants.JJ_CELL
				&& block.getWorld().isJJVisible()) {
			g.setColor(Color.magenta);
			g.fillRect(0, 23, 30, 7);
		}
	}

	public void paintFans(Graphics g) {
		for (Person p : block.getPeople())
			if (p.getClass() == Fan.class) {
				int i = ((Fan) p).getOwner().getInfo(Constants.NAME);
				Color color = COLOR[i];
				g.setColor(color);
				g.fillRect(CELL_COORDSX[i], CELL_COORDSY[1], CELL_COORDSW,
						CELL_COORDSH);
			}

	}

	public void paintPlayers(Graphics g) {
		for (Person p : block.getPeople())
			if (p.getClass() == Player.class) {
				int i = ((Player) p).getInfo(Constants.NAME);
				Color color = COLOR[i];
				g.setColor(color);
				g.fillRect(CELL_COORDSX[i], CELL_COORDSY[0], CELL_COORDSW,
						CELL_COORDSH);
			}

	}

}
