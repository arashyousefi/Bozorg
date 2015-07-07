package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.*;

public class AttackEvent extends Event {
	private int dir;

	public AttackEvent(Player player, int dir) {
		super(player);
		this.dir = dir;
		setTime();
	}

	public int getDir() {
		return dir;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		Position pos = player.getBlock().getPos().move(dir);
		int size = World.getMap().at(pos).getPeople().size();
		Person[] deadPeople = new Person[size];
		player.setCanAttack(false);
		for (Person p : World.getMap().at(pos).getPeople())
			p.recieveDamage(player);
		for (Person p : World.getMap().at(pos).getPeople())
			if (p.getInfo(Constants.IS_ALIVE) == Constants.DEAD)
				deadPeople[--size] = p;
		EventHandler.addEvent(new DieEvent(deadPeople));
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
