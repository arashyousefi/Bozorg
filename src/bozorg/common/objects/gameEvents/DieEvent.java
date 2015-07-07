package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Event;
import bozorg.common.objects.Person;

public class DieEvent extends Event {
	private Person[] people;

	public DieEvent(Person[] deadPeople) {
		super(null);
		people = deadPeople;
		setTime();
	}

	@Override
	public void setTime() {
		time = 1;

	}

	@Override
	public void execute() throws BozorgExceptionBase {
		for (int i = 0; i < people.length; ++i)
			if (people[i] != null)
				people[i].die();

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
