package bozorg.common.objects;

import java.util.HashMap;

import bozorg.common.GameObjectID;
//import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.exceptions.BozorgExceptionBase;

public abstract class Person {

	protected Block block;
	protected HashMap<String, Integer> info;
	protected GameObjectID id;
	protected World world;

	public Person(World world) {
		this.world = world;
		this.block = null;
		this.info = new HashMap<String, Integer>();
		this.info.put(Constants.ROW, -1);
		this.info.put(Constants.COL, -1);
		this.info.put(Constants.IS_ALIVE, Constants.ALIVE);
	}

	public void setBlock(Block block) {
		this.block = block;
		if (block != null) {
			this.info.put(Constants.ROW, this.block.getPos().getY());
			this.info.put(Constants.COL, this.block.getPos().getX());
		}
	}

	public Block getBlock() {
		return this.block;
	}

	public HashMap<String, Integer> getInfo() {
		return info;
	}

	public void setInfo(HashMap<String, Integer> info) {
		this.info = info;
	}

	public GameObjectID getId() {
		return id;
	}

	public void updateInfo(String infoKey, Integer infoValue)
			throws BozorgExceptionBase {
		if (!this.info.containsKey(infoKey))
			throw new BozorgExceptionBase();
		this.info.put(infoKey, infoValue);
	}

	public int getInfo(String key) {
		return info.get(key);
	}

	public abstract void recieveDamage(Player player);

	public abstract void die();

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;
		Person temp = (Person) object;
		return id.equals(temp.id);
	}

}
