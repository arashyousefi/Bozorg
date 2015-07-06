package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Event;
import bozorg.common.objects.Person;
import bozorg.common.objects.Player;

public class DieEvent extends Event {
	private Person person;

	public DieEvent(Player p) {
		super(p);
	}

	public DieEvent(Player player, Person person) {
		super(player);
		this.person = person;
		setTime();
	}

	@Override
	public void setTime() {
		time = 1;

	}

	@Override
	public void execute() throws BozorgExceptionBase {
		person.die();
	}

	@Override
	public boolean destroy() throws BozorgExceptionBase {
		return true;
	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

}
