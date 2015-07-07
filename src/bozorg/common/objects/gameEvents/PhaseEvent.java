package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.EventHandler;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class PhaseEvent extends Event {

	public PhaseEvent(EventHandler eh, Player player) {
		super(eh, player);
		setTime();
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		player.changePowerUp(Constants.PHASE, 1);
	}

	@Override
	public boolean destroy() {
		player.changePowerUp(Constants.PHASE, -1);
		if (player.hasPowerUp(Constants.PHASE))
			return false;
		return true;
	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

	@Override
	public void setTime() {
		time = Constants.BONUS_INFO[Constants.PHASE] * Constants.FPS;
	}

}
