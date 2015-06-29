package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.*;

public class MoveEvent extends Event {

	private int dir;

	public MoveEvent(Player player, int dir) {
		super(player);
		setTime();
		this.dir = dir;
	}

	public int getDir() {
		return dir;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		Position p = player.getBlock().getPos().move(dir);
		player.getBlock().removePerson(player);
		player.setBlock(World.getMap().at(p));
		player.getBlock().addPerson(player);
		player.setCanMove(false);
		if (World.getMap().at(p).getCellType() == Constants.JJ_CELL && World.getJJVisible()) {
			World.win(player);
		}
	}

	@Override
	public boolean isValid() {
		Block block = player.getBlock();
		if (!(((1 << dir) & (block.getWallType())) == 0)
				&& !player.hasPowerUp(Constants.PHASE))
			return false;
		if (player.hasPowerUp(Constants.STUNNED))
			return false;
		return player.canMove();
	}

	@Override()
	public boolean destroy() {
		player.setCanMove(true);
		return true;
	}

	@Override
	public void setTime() {
		time = Constants.FPS / player.getInfo(Constants.SPEED);
	}
}
