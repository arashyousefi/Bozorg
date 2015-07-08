package bozorg.common.objects;

import java.io.Serializable;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;

public class Fan extends Person implements Serializable {
	private Player owner;

	public Fan(Player owner, World world) {
		super(world);
		this.owner = owner;
		id = GameObjectID.create(Fan.class);
		world.addPerson(this);
		info.put(Constants.OWNER, owner.getName());
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setRow(Integer row) {
		info.put(Constants.ROW, row);
	}

	public void setCol(Integer col) {
		info.put(Constants.COL, col);
	}

	@Override
	public void recieveDamage(Player player) {

		if (player.equals(this.getOwner()))
			return;
		try {

			updateInfo(Constants.IS_ALIVE, Constants.DEAD);
		} catch (BozorgExceptionBase e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	@Override
	public void die() {
		if (block != null)
			block.removePerson(this);
	}

}
