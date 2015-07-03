import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

import gamePanel.BozorgMenuBar;
import gamePanel.GamePanel;
import bozorg.judge.Judge;

public class Test {
	static int[][] cellTypes, wallTypes;
	static int[] players;

	public static void main(String[] args) {
		readMap();
		Judge judge = new Judge();
		GamePanel panel = new GamePanel();
		judge.loadMap(cellTypes, wallTypes, players);
		panel.init(judge, null);
		panel.setJMenuBar(new BozorgMenuBar(judge, panel));
		panel.pack();
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);
	}

	public static void readMap() {
		File map = new File("resources/map.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(map);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = scanner.nextInt();
		int height = scanner.nextInt();
		cellTypes = new int[height][width];
		wallTypes = new int[height][width];
		for (int i = 0; i < height; ++i)
			for (int j = 0; j < width; ++j)
				wallTypes[i][j] = scanner.nextInt();
		for (int i = 0; i < height; ++i)
			for (int j = 0; j < width; ++j)
				cellTypes[i][j] = scanner.nextInt();
		int playercnt = scanner.nextInt();
		players = new int[playercnt];
		for (int i = 0; i < playercnt; ++i)
			players[i] = scanner.nextInt();
	}
}
