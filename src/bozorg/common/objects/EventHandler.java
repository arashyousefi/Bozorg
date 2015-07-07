package bozorg.common.objects;

import java.io.Serializable;
import java.util.ArrayList;

import bozorg.common.exceptions.BozorgExceptionBase;

@SuppressWarnings("serial")
public class EventHandler implements Serializable {
	private ArrayList<Event> events = new ArrayList<>();

	public EventHandler() {

	}

	public void addEvent(Event event) throws BozorgExceptionBase {
		if (!event.isValid())
			throw new BozorgExceptionBase();
		events.add(event);
		event.execute();
	}

	public void handle() {
		ArrayList<Event> temp = new ArrayList<>();
		for (Event event : events) {
			event.timeDecrement();
			if (event.getTime() <= 0)
				try {
					if (!event.destroy()) {
						event.setTime();
						temp.add(event);
					}
				} catch (BozorgExceptionBase e) {
				}
			else
				temp.add(event);
		}
		events = temp;
	}
}
