package bozorg.common.objects;

import java.io.Serializable;

public class MapSize implements Serializable {
	private int width;
	private int height;

	public MapSize(int height, int width) {
		this.width = width;
		this.height = height;
		// check valid size and ERROR :|
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
