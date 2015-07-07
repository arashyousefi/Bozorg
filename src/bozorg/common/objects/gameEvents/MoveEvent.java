package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.*;

@SuppressWarnings("serial")
public class MoveEvent extends Event {

	private int dir;

	public MoveEvent(EventHandler eh, Player player, int dir) {
		super(eh, player);
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
		player.setBlock(player.getWorld().getMap().at(p));
		player.getBlock().addPerson(player);
		player.setCanMove(false);

		// for (Person person : player.getBlock().getPeople()) {
		// if (person.getClass() == Fan.class) {
		// Fan fan = (Fan) person;
		// fan.die(player);
		// }
		// }

		if (player.getBlock().getPlayers().size() == 1) {
			if (player.getWorld().getMap().at(p).getCellType() == Constants.JJ_CELL
					&& player.getWorld().isJJVisible()) {
				player.getWorld().win(player);
			}
			if (player.getBlock().getCellType(player) == Constants.BONUS_CELL)
				eh.addEvent(new AbsorbEvent(eh, player));
		}
	}

	@Override
	public boolean isValid() {
		if (player.getWorld().gameEnded())
			return false;
		Block block = player.getBlock();
		if (!block.getPos().move(dir).isValid())
			return false;
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
