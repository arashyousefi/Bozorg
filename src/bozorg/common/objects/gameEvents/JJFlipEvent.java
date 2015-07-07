package bozorg.common.objects.gameEvents;

import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Event;
import bozorg.common.objects.Player;
import bozorg.common.objects.World;

public class JJFlipEvent extends Event {
	private World world;

	public JJFlipEvent(Player player, World world) {
		super(player);
		this.world = world;
		setTime();
	}

	@Override
	public void setTime() {
		time = Constants.JJ_FLIP_TIME * Constants.FPS;
	}

	@Override
	public void execute() throws BozorgExceptionBase {
		world.flipJJ();
	}

	@Override
	public boolean destroy() throws BozorgExceptionBase {
		world.flipJJ();
		return false;
	}

	@Override
	public boolean isValid() throws BozorgExceptionBase {
		return true;
	}

}
