package mapCreator;

import java.util.*;

import bozorg.common.objects.Constants;

public class MapCreator {
	public static final int Down = 0, Right = 1;
	private int Height, Width, WallCount;
	private boolean[][][] matrix;
	private ArrayList<Wall> wallList = new ArrayList<Wall>();
	private int[][] walltypes, celltypes;

	private void printMap() {

		for (int i = 0; i < Height; ++i) {
			for (int j = 0; j < Width; ++j)
				System.out.print(walltypes[i][j] + " ");
			System.out.println("");
		}
		for (int i = 0; i < Height; ++i) {
			for (int j = 0; j < Width; ++j)
				System.out.print("0 ");
			System.out.println("");
		}
	}

	private void initializeMatrix() {
		matrix = new boolean[Height][Width][2];

		for (int i = 0; i < Width; ++i) {
			matrix[Height - 1][i][Down] = true;
		}

		for (int i = 0; i < Height; ++i) {
			matrix[i][Width - 1][Right] = true;
		}
	}

	private void initializeArrayList() {
		for (int i = 0; i < Height; ++i)
			for (int j = 0; j < Width; ++j)
				for (int d = 0; d < 2; ++d)
					wallList.add(new Wall(i, j, d));
	}

	private void setNewCell(int value) {
		Random rand = new Random();
		int x, y;
		do {
			x = rand.nextInt(Height);
			y = rand.nextInt(Width);
		} while (celltypes[x][y] != 0);
		celltypes[x][y] = value;
	}

	public MapCreator(int width, int height, int players) {
		Width = width;
		Height = height;
		WallCount = Width * Height;
		initializeMatrix();
		initializeArrayList();
		int wallCount = wallList.size(); /* NEW */
		for (int wallNumber = 0; wallNumber < wallCount; ++wallNumber) {
			int randomIndex;
			Random randomGenerator = new Random();
			randomIndex = randomGenerator.nextInt(wallList.size());
			Wall randomWall = wallList.get(randomIndex);
			wallList.remove(randomIndex);
			matrix[randomWall.getRow()][randomWall.getCol()][randomWall
					.getDir()] = true;
			if (!DFS.isConnected(matrix)) {
				matrix[randomWall.getRow()][randomWall.getCol()][randomWall
						.getDir()] = false;
			}
		}
		walltypes = new int[Height + 1][Width + 1];
		celltypes = new int[Height + 1][Width + 1];
		for (int i = 0; i <= Width; ++i) {
			walltypes[0][i] = 1;
			walltypes[Height - 1][i] = 4;
		}
		for (int i = 0; i <= Height; ++i) {
			walltypes[i][0] |= 8;
			walltypes[i][Width - 1] |= 2;
		}
		for (int i = 0; i < Height; ++i) {
			for (int j = 0; j < Width; ++j) {
				if (matrix[i][j][Down] == true) {
					walltypes[i][j] |= 4;
					walltypes[i + 1][j] |= 1;
				}
				if (matrix[i][j][Right] == true) {
					walltypes[i][j] |= 2;
					walltypes[i][j + 1] |= 8;

				}
			}
		}

		// seting cell types
		setNewCell(Constants.JJ_CELL);
		for (int i = 0; i < players; ++i)
			setNewCell(Constants.START_CELL);
		for (int i = 0; i < Constants.BONUS_CELL_RATIO * Width * Height; ++i)
			setNewCell(getRandomBonus());
	}

	private int getRandomBonus() { // returns from [4,9]
		Random rand = new Random();
		return 4 + rand.nextInt(6);
	}

	public int[][] getWallTypes() {
		int[][] ret = new int[Height][Width];
		for (int i = 0; i < Height; ++i)
			for (int j = 0; j < Width; ++j)
				ret[i][j] = walltypes[i][j];
		return ret;
	}

	public int[][] getCellTypes() {
		int[][] ret = new int[Height][Width];
		for (int i = 0; i < Height; ++i)
			for (int j = 0; j < Width; ++j)
				ret[i][j] = celltypes[i][j];
		return ret;
	}
}
