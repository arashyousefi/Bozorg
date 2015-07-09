package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.EventHandler;
import bozorg.common.objects.Player;

@SuppressWarnings("serial")
public class StunnedEvent extends Event {

	public StunnedEvent(EventHandler eh, Player player) {
		super(eh, player);
		setTime();
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		player.changePowerUp(Constants.STUNNED, 1);
	}

	@Override
	public boolean destroy() {
		player.changePowerUp(Constants.STUNNED, -1);
		return true;
	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

	@Override
	public void setTime() {
		time = Constants.BONUS_INFO[Constants.STUNNED] * Constants.FPS;
	}

}
