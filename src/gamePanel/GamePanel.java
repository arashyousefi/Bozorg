package gamePanel;

import gameController.GameController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
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
		try {
			setIconImage(ImageIO.read(new File("resources/icon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
	}

	public GamePanel(Player p) {
		this();
	}

	public void init(Judge engine, GameController controller) {
		// TODO
		this.engine = engine;
		addKeyListener(controller);
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
		mapPanel = new MapPanel(engine.getMapHeight(), engine.getMapWidth());
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

	public void setEngine(Judge engine, GameObjectID id) {
		this.engine = engine;
		try {
			System.out.println(engine.getInfo(id).get(Constants.FANS));
		} catch (BozorgExceptionBase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initStatusPanel();
		initMapPanel();
		initCells();
		setPlayer(engine.IDToPlayer(id));
	}
}
