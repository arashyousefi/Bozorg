package bozorg.common.objects;

import java.io.Serializable;

public class Position implements Serializable {
	private int x;
	private int y;
	private World world;

	public Position(int x, int y, World world) {
		this.world = world;
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

	public boolean isValid() {
		if (x < 0 || x >= world.getMapWidth())
			return false;
		if (y < 0 || y >= world.getMapHeight())
			return false;
		return true;
	}

	public int distance(Position p) {
		return Math.max(Math.abs(p.getX() - x), Math.abs(p.getY() - y));
	}

	public Position move(int dir) {
		Position p = new Position(x, y, world);
		p.x += Constants.DX[dir];
		p.y += Constants.DY[dir];
		return p;
	}

	@Override
	public String toString() {
		return ("X : " + this.x + ", Y :" + this.y);
	}

}
