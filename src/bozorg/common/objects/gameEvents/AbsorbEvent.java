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
		Block block = player.getBlock();
		int type = block.getCellType();
		switch (type) {

		case Constants.SPEEDUP_CELL:
			EventHandler.addEvent(new HasteEvent(player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.JUMP_CELL:
			EventHandler.addEvent(new PhaseEvent(player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.RADAR_CELL:
			EventHandler.addEvent(new SightEvent(player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.STONE_CELL:
			EventHandler.addEvent(new StunnedEvent(player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.HOSPITAL_CELL:
			EventHandler.addEvent(new HealEvent(player));
			block.setType(Constants.NONE_CELL);
			break;
		case Constants.FAN_CELL:
			EventHandler.addEvent(new FanEvent(player));
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
