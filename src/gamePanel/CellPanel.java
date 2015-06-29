package gamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bozorg.common.objects.Block;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Fan;
import bozorg.common.objects.Person;
import bozorg.common.objects.Player;
import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class CellPanel extends JPanel {
	private static int[] CELL_COORDSX = { 0, 8, 15, 23 };
	private static int[] CELL_COORDSY = { 0, 8, 15, 23 };
	private static int CELL_COORDSW = 7;
	private static int CELL_COORDSH = 7;
	Color[] COLOR = { Color.blue, Color.red, Color.yellow, Color.green };

	private Block block;
	private int wallType;

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
		super.paintComponent(g);
		paintPlayers(g);
		paintFans(g);
		if (block.getCellType() == Constants.BONUS_CELL) {
			g.setColor(Color.pink);
			g.fillRect(0, 15, 30, 7);
		}
		if (block.getCellType() == Constants.JJ_CELL) {
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

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(2, 2));
		Block b1 = new Block(0, 0, Constants.BONUS_CELL, 11);
		Block b2 = new Block(0, 1, Constants.JJ_CELL, 11);
		Block b3 = new Block(1, 0, Constants.NONE_CELL, 12);
		Block b4 = new Block(1, 1, Constants.BONUS_CELL, 6);
		CellPanel c1 = new CellPanel(b1);
		CellPanel c2 = new CellPanel(b2);
		CellPanel c3 = new CellPanel(b3);
		CellPanel c4 = new CellPanel(b4);
		Player reza = new Player(Constants.REZA, b1);
		Player saman = new Player(Constants.SAMAN, b1);
		Player jafar = new Player(Constants.JAFAR, b1);
		Player hasin = new Player(Constants.HASIN, b1);
		b1.addPerson(reza);
		b1.addPerson(new Fan(reza));
		b1.addPerson(new Fan(hasin));
		b2.addPerson(saman);
		b2.addPerson(new Fan(jafar));
		b3.addPerson(jafar);
		b4.addPerson(new Fan(saman));
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		frame.add(c4);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
