package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.Fan;
import bozorg.common.objects.Player;

public class FanEvent extends Event {

	public FanEvent(Player player) {
		super(player);
		time = 1;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		int fans = player.getInfo(Constants.FANS);
		player.updateInfo(Constants.FANS, fans + 3);
		for (int i = 0; i < 3; ++i)
			player.addFan(new Fan(player,player.getWorld()));

	}

	@Override
	public boolean destroy()  {
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
