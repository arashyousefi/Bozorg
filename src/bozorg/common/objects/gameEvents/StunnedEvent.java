package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.*;

public class StunnedEvent extends Event {

	public StunnedEvent(Player player) {
		super(player);
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
