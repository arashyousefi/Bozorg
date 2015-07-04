package bozorg.common.objects;

import bozorg.common.GameObjectID;

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

	public void setIsAlive(Integer isAlive) {
		info.put(Constants.IS_ALIVE, isAlive);
	}

	@Override
	public void recieveDamage(Player player) {
		if (player == this.getOwner())
			return;
		try {
			updateInfo(Constants.IS_ALIVE, Constants.DEAD);
			if (block != null)
				block.removePerson(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void die() {
		try {
			updateInfo(Constants.IS_ALIVE, Constants.DEAD);
		} catch (Exception e) {
			// TODO
		}
		if (block != null)
			block.removePerson(this);
	}

	public void die(Player player) {
		if (owner != player)
			die();
	}

}
