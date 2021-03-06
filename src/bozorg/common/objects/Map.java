package bozorg.common.objects;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Map implements Serializable {
	private MapSize mapSize;
	private Block[][] blocks;
	private World world;

	public Map(File file) {
		loadMapFromFile(file);
	}

	public Map(World world, int[][] cellsType, int[][] wallsType, int[] players) {
		this.world = world;
		int row = cellsType.length;
		int col = cellsType[0].length;
		mapSize = new MapSize(row, col);
		blocks = new Block[row][col];
		for (int i = 0; i < row; ++i)
			for (int j = 0; j < col; ++j)
				blocks[i][j] = new Block(i, j, cellsType[i][j],
						wallsType[i][j], world);
	}

	private static void loadMapFromFile(File file) {
		// TODO
	}

	public Block at(Position pos) {
		return blocks[pos.getY()][pos.getX()];
	}

	public Block at(int row, int col) {
		return blocks[row][col];
	}

	public MapSize getMapSize() {
		return mapSize;
	}

	public int getRows() {
		return mapSize.getHeight();
	}

	public int getCols() {
		return mapSize.getWidth();
	}
}
