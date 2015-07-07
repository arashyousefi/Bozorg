package bozorg.common.objects;

import java.util.ArrayList;

public class Order {
	private int type, dir, speed, time;

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int NONE = 4;

	public static final int MOVE = 0;
	public static final int ATTACK = 1;
	public static final int WARD = 2;
	public static final int ABSORB = 3;

	public Order(int type, int dir, int speed) {
		this.type = type;
		this.dir = dir;
		this.speed = speed;
		this.time = 20 / speed;
	}

	public void timeDecrement() {
		time--;
	}

	public int getTime() {
		return this.time;
	}

	public int getType() {
		return this.type;
	}

	public int getDir() {
		return this.dir;
	}

}
