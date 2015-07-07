package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.Player;

public class HealEvent extends Event {

	public HealEvent(Player player) {
		super(player);
		this.player = player;
		time = 1;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		int hp = player.getInfo(Constants.HEALTH);
		hp = Math.min(hp + 20, 100);
		player.updateInfo(Constants.HEALTH, hp);

	}

	@Override
	public boolean destroy() {
		return true;
	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

	@Override
	public void setTime() {

	}

}
