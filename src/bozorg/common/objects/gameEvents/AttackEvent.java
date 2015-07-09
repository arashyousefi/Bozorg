package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.EventHandler;
import bozorg.common.objects.Person;
import bozorg.common.objects.Player;
import bozorg.common.objects.Position;

@SuppressWarnings("serial")
public class AttackEvent extends Event {
	private int dir;

	public AttackEvent(EventHandler eh, Player player, int dir) {
		super(eh, player);
		this.dir = dir;
		setTime();
	}

	public int getDir() {
		return dir;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		Position pos = player.getBlock().getPos().move(dir);
		int size = player.getWorld().getMap().at(pos).getPeople().size();
		Person[] deadPeople = new Person[size];
		player.setCanAttack(false);
		for (Person p : player.getWorld().getMap().at(pos).getPeople()) {
			p.recieveDamage(player);
			if (p.getInfo(Constants.IS_ALIVE) == Constants.DEAD)
				deadPeople[--size] = p;
		}

		eh.addEvent(new DieEvent(eh, deadPeople));
	}

	@Override
	public boolean isValid() {
		if (!(((1 << dir) & (player.getBlock().getWallType())) == 0))
			return false;
		if (player.hasPowerUp(Constants.STUNNED))
			return false;
		return player.canAttack();
	}

	@Override
	public boolean destroy() {
		player.setCanAttack(true);
		return true;
	}

	@Override
	public void setTime() {
		time = Constants.FPS / player.getInfo(Constants.SPEED);
	}
}
