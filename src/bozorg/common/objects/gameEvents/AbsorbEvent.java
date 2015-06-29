package bozorg.common.objects.gameEvents;

import bozorg.common.objects.*;
import bozorg.common.exceptions.BozorgExceptionBase;

public class AbsorbEvent extends Event {

	public AbsorbEvent(Player player) {
		super(player);
		time = 1;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		int type = player.getBlock().getCellType();
		switch (type) {

		case Constants.SPEEDUP_CELL:
			EventHandler.addEvent(new HasteEvent(player));
			break;
		case Constants.JUMP_CELL:
			EventHandler.addEvent(new PhaseEvent(player));
			break;
		case Constants.RADAR_CELL:
			EventHandler.addEvent(new SightEvent(player));
			break;
		case Constants.STONE_CELL:
			EventHandler.addEvent(new StunnedEvent(player));
			break;
		case Constants.HOSPITAL_CELL:
			EventHandler.addEvent(new HealEvent(player));
			break;
		case Constants.FAN_CELL:
			EventHandler.addEvent(new FanEvent(player));
			break;
		}
	}

	@Override
	public boolean isValid() {
		if (player.getBlock().getCellType(player) != Constants.BONUS_CELL)
			return false;
		if (player.getBlock().playerCount() > 1)
			return false;
		if (player.hasPowerUp(Constants.STUNNED))
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
