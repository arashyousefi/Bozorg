package bozorg.common.objects;

import java.util.ArrayList;

public class Block {

	private Position pos;
	private int cellType;
	private ID id;
	private int wall;
	private ArrayList<Person> people = new ArrayList<Person>();

	public Block(int row, int col, int cellType, int wallType) {

		pos = new Position(col, row);
		this.cellType = cellType;
		wall = wallType;
	}

	public Position getPos() {
		return pos;
	}

	public ID getId() {
		return id;
	}

	public int getWall() {
		return wall;
	}

	public void addPerson(Person person) {
		this.people.add(person);
		person.setBlock(this);
	}

	public void removePerson(Person person) {
		person.setBlock(null);
		this.people.remove(person);
	}

	public boolean hasFan(Player player) {
		for (Person p : this.people)
			if (p.getClass() == Fan.class) {
				Fan fan = (Fan) p;
				if (fan.getOwner().equals(player))
					return true;
			}
		return false;
	}

	public boolean isSeenBy(Player player) {
		if (player == null)
			return true;
		if (player.hasPowerUp(Constants.SIGHT))
			return true;
		if (Position.distance(pos, player.getBlock().getPos()) <= player
				.getInfo(Constants.VISION))
			return true;
		return this.hasFan(player);
	}

	public int getCellType(Player player) {
		if (isSeenBy(player)) {
			if (cellType == Constants.JJ_CELL)
				return (World.getJJVisible() ? Constants.JJ_CELL
						: Constants.NONE_CELL);
			return cellType > 3 ? Constants.BONUS_CELL : cellType;
		}
		return Constants.DARK_CELL;
	}

	public int getCellType() {
		return cellType;
	}

	public int getWallType(Player player) {
		if (this.isSeenBy(player))
			return wall;
		return Constants.XXXX_WALL;
	}

	public int getWallType() {
		return wall;
	}

	public ArrayList<Person> getPeople() {
		return this.people;
	}

	public void putFan(Fan fan) {
		this.people.add(fan);
	}

	public int playerCount() {
		int count = 0;
		for (Person p : people) {
			if (p.getClass() == Player.class)
				++count;
		}
		return count;
	}

	@Override
	public String toString() {
		return (pos.getY() + ", " + pos.getX());
	}
}