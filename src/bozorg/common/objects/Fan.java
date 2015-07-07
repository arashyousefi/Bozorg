package bozorg.common.objects;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;

public class Fan extends Person {
	private Player owner;

	public Fan(Player owner) {
		super();
		this.owner = owner;
		id = GameObjectID.create(Fan.class);
		World.addPerson(this);
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
