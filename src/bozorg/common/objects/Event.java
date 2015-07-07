package bozorg.common.objects;

import java.io.Serializable;

import bozorg.common.exceptions.BozorgExceptionBase;

@SuppressWarnings("serial")
public abstract class Event implements Serializable {
	protected int time;
	protected Player player;
	protected EventHandler eh;

	public Event(EventHandler eh, Player player) {
		this.eh = eh;
		this.player = player;
	}

	public void timeDecrement() {
		time--;
	}

	public int getTime() {
		return this.time;
	}

	public abstract void setTime();

	public Player getPlayer() {
		return this.player;
	}

	public abstract void execute() throws BozorgExceptionBase;

	public abstract boolean destroy() throws BozorgExceptionBase;

	public abstract boolean isValid() throws BozorgExceptionBase;
}
