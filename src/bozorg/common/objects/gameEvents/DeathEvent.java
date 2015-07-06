package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.Player;

public class DeathEvent extends Event {

	public DeathEvent(Player player) {
		super(player);
		setTime();
	}

	@Override
	public void setTime() {
		time = Constants.RESPAWN_TIME * Constants.FPS;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
	}

	@Override
	public boolean destroy() throws BozorgExceptionBase {
		player.respawn();
		return true;
	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

}
