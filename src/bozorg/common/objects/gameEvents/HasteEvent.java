package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.EventHandler;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class HasteEvent extends Event {

	public HasteEvent(EventHandler eh, Player player) {
		super(eh, player);
		setTime();
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		player.changePowerUp(Constants.HASTE, 1);
		player.setSpeed(10);

	}

	@Override
	public boolean destroy() {
		player.changePowerUp(Constants.HASTE, -1);
		if (player.hasPowerUp(Constants.HASTE))
			return false;
		player.resetSpeed();
		return true;

	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

	@Override
	public void setTime() {
		time = Constants.BONUS_INFO[Constants.HASTE] * Constants.FPS;

	}

}
