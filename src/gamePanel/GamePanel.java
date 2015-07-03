package gamePanel;

import gameController.GameController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import bozorg.common.objects.Player;
import bozorg.judge.Judge;

@SuppressWarnings("serial")
public class GamePanel extends JFrame {
	private Judge engine;
	private MapPanel mapPanel;
	private StatusPanel statPanel;
	private int width, height;
	private CellPanel[][] cells;
	public static final int CELL_SIZE = 30;
	public static final int STATUS_WIDTH = 200;

	public GamePanel() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
	}

	public void init(Judge engine, GameController controller) {
		// TODO
		this.engine = engine;
		setSize();
		initStatusPanel();
		initMapPanel();
		initCells();
	}

	private void setSize() {
		width = engine.getMapWidth() * CELL_SIZE + STATUS_WIDTH;
		height = engine.getMapHeight() * CELL_SIZE;
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
		cells = new CellPanel[engine.getMapHeight()][engine.getMapWidth()];
		for (int i = 0; i < engine.getMapHeight(); ++i)
			for (int j = 0; j < engine.getMapWidth(); ++j) {
				cells[i][j] = new CellPanel(engine.getBlock(i, j));
				mapPanel.add(cells[i][j]);
			}
	}

	public void setPlayer(Player p) {
		statPanel.setPlayer(p);
		MapPanel.setPlayer(p);
	}
}
