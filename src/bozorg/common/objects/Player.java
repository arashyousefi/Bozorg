package bozorg.common.objects;

import java.util.ArrayList;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.gameEvents.DeathEvent;

public class Player extends Person {

	private int[] powerUps = new int[5];
	private ArrayList<Fan> fans = new ArrayList<Fan>();
	private ArrayList<Fan> activeFans = new ArrayList<Fan>();
	private boolean canMove = true;
	private boolean canAttack = true;
	private int name;
	private Block startingBlock;

	public Player(int name, Block startingBlock) {
		super();
		this.name = name;
		this.id = GameObjectID.create(Player.class);
		this.startingBlock = startingBlock;
		World.addPerson(this);

		initializeConstants();
		initializeFans();

	}

	private void initializeFans() {
		for (int i = 0; i < info.get(Constants.FANS); ++i) {
			Fan fan = new Fan(this);
			this.fans.add(fan);
			this.activeFans.add(fan);
		}

	}

	public int getName() {
		return name;
	}

	public boolean hasPowerUp(int type) {
		if (powerUps[type] > 0)
			return true;
		return false;
	}

	public ArrayList<GameObjectID> getFans() {
		ArrayList<GameObjectID> ret = new ArrayList<GameObjectID>();
		for (Fan f : this.fans)
			ret.add(f.getId());
		return ret;
	}

	public GameObjectID nextFan() {
		if (!hasActiveFan())
			return null;
		Fan fan = activeFans.get(0);
		return fan.getId();
	}

	public Fan dropFan() {
		Fan fan = activeFans.get(0);
		activeFans.remove(0);
		return fan;
	}

	public boolean hasActiveFan() {
		return !activeFans.isEmpty();
	}

	public int getNumberOfActiveFans() {
		return activeFans.size();
	}

	public boolean canAttack() {
		return canAttack;
	}

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	public void changePowerUp(int type, int amount) {
		powerUps[type] += amount;
	}

	public void addFan(Fan fan) {
		fans.add(fan);
		activeFans.add(fan);
	}

	@Override
	public void recieveDamage(Player player) {
		if (player.equals(this))
			return;
		int health = this.getInfo(Constants.HEALTH);
		try {
			this.updateInfo(Constants.HEALTH,
					health - player.getInfo(Constants.POWER));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.getInfo(Constants.HEALTH) <= 0)
			try {
				updateInfo(Constants.IS_ALIVE, Constants.DEAD);

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void die() {
		Fan[] dyingFans = fans.toArray(new Fan[fans.size()]);
		for (Fan fan : dyingFans)
			fan.die();
		if (block != null)
			block.removePerson(this);
		for (int i = 0; i < powerUps.length; ++i)
			powerUps[i] = 0;
		fans.clear();
		activeFans.clear();
		try {
			EventHandler.addEvent(new DeathEvent(this));
		} catch (BozorgExceptionBase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setSpeed(int speed) {
		try {
			updateInfo(Constants.SPEED, speed);
		} catch (BozorgExceptionBase e) {
			e.printStackTrace();
		}
	}

	public void resetSpeed() {
		try {
			updateInfo(Constants.SPEED, Constants.PLAYER_INFO[name][1]);
		} catch (BozorgExceptionBase e) {
			e.printStackTrace();
		}

	}

	public void respawn() {
		canMove = canAttack = true;
		this.info.put(Constants.FANS, Constants.PLAYER_INFO[name][0]);
		this.info.put(Constants.HEALTH, 100);
		this.info.put(Constants.IS_ALIVE, Constants.ALIVE);
		initializeFans();
		setBlock(startingBlock);
		startingBlock.addPerson(this);
		// XXX
	}

	private void initializeConstants() {
		this.info.put(Constants.FANS, Constants.PLAYER_INFO[name][0]);
		this.info.put(Constants.SPEED, Constants.PLAYER_INFO[name][1]);
		this.info.put(Constants.VISION, Constants.PLAYER_INFO[name][2]);
		this.info.put(Constants.POWER, Constants.PLAYER_INFO[name][3]);
		this.info.put(Constants.HEALTH, 100);
		this.info.put(Constants.IS_WINNER, Constants.NOT_FINISHED);
		this.info.put(Constants.NAME, name);
	}

	public boolean isDead() {
		return (getInfo(Constants.IS_ALIVE) == Constants.DEAD);
	}

	@Override
	public boolean equals(Object object) {
		Player player;
		try {
			player = (Player) object;
		} catch (Exception e) {
			return false;
		}
		if (player.getName() == this.name)
			return true;
		return false;
	}
}
