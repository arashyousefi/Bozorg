package mapCreator;

public class Wall {
	private int row, col, dir;

	public Wall(int row, int col, int dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getDir() {
		return dir;
	}
}
