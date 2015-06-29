package bozorg.common.objects;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static int distance(Position p1, Position p2) {
		return p1.distance(p2);
	}

	public int distance(Position p) {
		return Math.max(Math.abs(p.getX() - x) , Math.abs(p.getY() - y));
	}

	public Position move(int dir) {
		Position p = new Position(x, y);
		p.x += Constants.DX[dir];
		p.y += Constants.DY[dir];
		return p;
	}

	@Override
	public String toString() {
		return ("X : " + this.x + ", Y :" + this.y);
	}

}
