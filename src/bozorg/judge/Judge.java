package bozorg.judge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import bozorg.common.AI;
import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Block;
import bozorg.common.objects.Person;
import bozorg.common.objects.Player;
import bozorg.common.objects.World;

@SuppressWarnings("serial")
public class Judge extends JudgeAbstract implements Serializable {

	private World world = new World();
	private AI ai = new AI();

	@Override
	public ArrayList<GameObjectID> loadMap(int[][] cellsType,
			int[][] wallsType, int[] players) {
		return world.newGame(cellsType, wallsType, players);
	}

	@Override
	public int getMapWidth() {
		return world.getCols();
	}

	@Override
	public int getMapHeight() {
		return world.getRows();
	}

	@Override
	public int getMapCellType(int col, int row) {
		return world.getBlock(row, col).getCellType();
	}

	@Override
	public int getMapCellType(int col, int row, GameObjectID player) {
		return world.getBlock(row, col).getCellType(world.getPlayer(player));
	}

	@Override
	public int getMapWallType(int col, int row) {
		return world.getBlock(row, col).getWallType();
	}

	@Override
	public int getMapWallType(int col, int row, GameObjectID player) {
		return world.getBlock(row, col).getWallType(world.getPlayer(player));
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePlayer(GameObjectID player, int direction)
			throws BozorgExceptionBase {
		Player p = world.getPlayer(player);
		world.movePlayer(p, direction);
	}

	@Override
	public void attack(GameObjectID attacker, int direction)
			throws BozorgExceptionBase {
		Player p = world.getPlayer(attacker);
		world.attack(p, direction);
	}

	@Override
	public GameObjectID throwFan(GameObjectID player)
			throws BozorgExceptionBase {
		Player p = world.getPlayer(player);
		return world.throwFan(p);
	}

	@Override
	public void getGift(GameObjectID player) throws BozorgExceptionBase {
		Player p = world.getPlayer(player);
		world.getGift(p);
	}

	@Override
	public void AIByStudents(GameObjectID player) {
		int dir = ai.doTurn(world, player);
		try {
			if (dir != -1)
				movePlayer(player, dir);
		} catch (Exception e) {

		}
	}

	@Override
	public ArrayList<GameObjectID> getEveryThing() {
		return world.getEveryThing();
	}

	@Override
	public ArrayList<String> getVision(GameObjectID player)
			throws BozorgExceptionBase {
		Player p = world.getPlayer(player);
		return world.getVision(p);
	}

	@Override
	public ArrayList<GameObjectID> getPlayersInVision(GameObjectID player) {
		Player p = world.getPlayer(player);
		return world.getPlayersInVision(p);
	}

	@Override
	public ArrayList<GameObjectID> getFans(GameObjectID player)
			throws BozorgExceptionBase {
		Player p = world.getPlayer(player);
		return p.getFans();
	}

	@Override
	public HashMap<String, Integer> getInfo(GameObjectID id)
			throws BozorgExceptionBase {
		Person p = world.getPerson(id);
		return p.getInfo();
	}

	@Override
	public void next50milis() {
		world.next50ms();

	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pauseTimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public float getTime() {
		return world.getTime();
	}

	@Override
	public void updateInfo(GameObjectID id, String infoKey, Integer infoValue)
			throws BozorgExceptionBase {
		Person p = world.getPerson(id);
		p.updateInfo(infoKey, infoValue);
	}

	public GameObjectID[] getPlayers() {
		return world.getPlayers();
	}

	public ArrayList<Player> getp() {
		return world.getp();
	}

	public Block getBlock(int row, int col) {
		return world.getBlock(row, col);
	}

	public Player IDToPlayer(GameObjectID player) {
		return world.getPlayer(player);
	}

	public World getWorld() {
		return world;
	}


}
