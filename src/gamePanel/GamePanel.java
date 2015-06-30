package gamePanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class GamePanel extends JFrame {
	private Judge engine;
	private MapPanel mapPanel;
	private StatusPanel statPanel;
	private int width, height;
	private CellPanel[][] cells;
	public static final double RATIO = 0.2;
	public static final int CELL_SIZE = 5;

	public GamePanel() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void init(Judge engine) {
		this.engine = engine;
		setSize();
		initStatusPanel();
		initMapPanel();
		initCells();
	}

	private void setSize() {
		width = (int) (engine.getMapWidth() * 5 * (1 / (1 - RATIO)));
		height = engine.getMapHeight() * 5;
		setSize(width, height);

	}

	private void initMapPanel() {
		mapPanel = new MapPanel(engine.getMapWidth(), engine.getMapHeight());
		getContentPane().add(mapPanel, BorderLayout.LINE_END);
	}

	private void initStatusPanel() {
		statPanel = new StatusPanel(height);
		getContentPane().add(statPanel, BorderLayout.LINE_START);
	}

	public Judge getEngine() {
		return this.engine;
	}

	private void initCells() {
		cells = new CellPanel[engine.getMapWidth()][engine.getMapHeight()];
		for (int i = 0; i < engine.getMapWidth(); ++i)
			for (int j = 0; j < engine.getMapHeight(); ++j) {
				cells[i][j] = new CellPanel(engine, i, j);
				mapPanel.add(cells[i][j]);
			}
	}
}
