package gamePanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class CellPanel extends JPanel {
	private Judge engine;
	private int row, col;

	public CellPanel(Judge engine, int row, int col) {
		this.engine = engine;
		this.row = row;
		this.col = col;
		setBorders();
	}

	private void setBorders() {
		int type = engine.getMapCellType(col, row);
		this.setBorder(BorderFactory.createMatteBorder(type / 8 % 2,
				type / 4 % 2, type / 2 % 2, type % 2, Color.BLACK));
	}

	@Override
	public void paintComponents(Graphics g) {
		g.setColor(Color.BLACK);
		super.paintComponents(g);
	}
}
