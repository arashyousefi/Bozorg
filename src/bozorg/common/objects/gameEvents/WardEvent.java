package bozorg.common.objects.gameEvents;

import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.EventHandler;
import bozorg.common.objects.Fan;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class WardEvent extends Event {
	public WardEvent(EventHandler eh, Player player) {
		super(eh, player);
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
