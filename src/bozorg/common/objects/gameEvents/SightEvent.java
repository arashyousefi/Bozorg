package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.EventHandler;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class SightEvent extends Event {

	public SightEvent(EventHandler eh, Player player) {
		super(eh, player);
		setTime();
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		player.changePowerUp(Constants.SIGHT, 1);

	}

	@Override
	public boolean destroy() throws BozorgExceptionBase {
		player.changePowerUp(Constants.SIGHT, -1);
		if (player.hasPowerUp(Constants.SIGHT))
			return false;
		return true;

	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

	@Override
	public void setTime() {
		time = Constants.BONUS_INFO[Constants.SIGHT] * Constants.FPS;

	}

}
