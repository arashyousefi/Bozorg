package bozorg.common.objects.gameEvents;

import bozorg.common.objects.Constants;
import bozorg.common.objects.*;

public class WardEvent extends Event {
	public WardEvent(Player player) {
		super(player);
		time = 1;
	}

	@Override
	public void execute() {
		Fan fan = player.dropFan();
		player.getBlock().addPerson(fan);
	}

	@Override
	public boolean isValid() {
		if (player.hasPowerUp(Constants.STUNNED))
			return false;
		if (!player.hasActiveFan())
			return false;
		return true;
	}

	@Override
	public boolean destroy() {
		return true;
	}

	@Override
	public void setTime() {

	}
}
