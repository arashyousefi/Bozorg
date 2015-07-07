package bozorg.common.objects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bonus implements Serializable {
	public static final int HASTE = 0;
	public static final int SIGHT = 1;
	public static final int STUNNED = 2;
	public static final int PHASE = 3;
	public static final int FAN = 4;
	public static final int HEAL = 5;

	private int[] bonusInfo = { 5, 3, 3, 2, 0, 0 };
	private int type, duration;

	public Bonus(int type) {
		this.type = type;
		this.duration = bonusInfo[type];
	}

	public int getType() {
		return type;
	}

	public int getDuration() {
		return duration * 20;
	}

}
