package bozorg.common.objects.gameEvents;

import bozorg.common.objects.*;
import bozorg.common.exceptions.BozorgExceptionBase;

@SuppressWarnings("serial")
public class AbsorbEvent extends Event {

	public AbsorbEvent(EventHandler eh, Player player) {
		super(eh, player);
		time = 1;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		Block block = player.getBlock();
		int type = block.getCellType();
		switch (type) {

		case Constants.SPEEDUP_CELL:
			eh.addEvent(new HasteEvent(eh, player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.JUMP_CELL:
			eh.addEvent(new PhaseEvent(eh, player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.RADAR_CELL:
			eh.addEvent(new SightEvent(eh, player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.STONE_CELL:
			eh.addEvent(new StunnedEvent(eh, player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.HOSPITAL_CELL:
			eh.addEvent(new HealEvent(eh, player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.FAN_CELL:
			eh.addEvent(new FanEvent(eh, player));
			block.setType(Constants.NONE_CELL);
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
