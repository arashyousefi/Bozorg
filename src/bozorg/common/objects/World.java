package bozorg.common.objects;

import java.util.*;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.gameEvents.AbsorbEvent;
import bozorg.common.objects.gameEvents.AttackEvent;
import bozorg.common.objects.gameEvents.MoveEvent;
import bozorg.common.objects.gameEvents.WardEvent;

public class World {
	private static Map map;
	private EventHandler eh = new EventHandler();
	private int gameTime;
	private static HashMap<GameObjectID, Person> gameObjects = new HashMap<GameObjectID, Person>();
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static boolean JJVisible;
	private static boolean gameEnded = false;

	public ArrayList<GameObjectID> newGame(int[][] cellsType,
			int[][] wallsType, int[] playersList) {
		map = new Map(cellsType, wallsType, playersList);

		int k = 0;
		for (int i = 0; i < map.getRows(); ++i)
			for (int j = 0; j < map.getCols(); ++j)
				if (cellsType[i][j] == Constants.START_CELL) {
					Player p = new Player(playersList[k++], map.at(i, j));
					World.addPlayer(p);
					p.setBlock(map.at(i, j));
					map.at(i, j).addPerson(p);
				}
		JJVisible = true;
		return this.getEveryThing();
	}

	public static void win(Player player) {
		for (Player p : players)
			try {
				if (p.equals(player))
					p.updateInfo(Constants.IS_WINNER, Constants.WINS);
				else
					p.updateInfo(Constants.IS_WINNER, Constants.LOST);
			} catch (Exception e) {
				// Nothing to do :)
			}
		gameEnded = true;
	}

	public static boolean gameEnded() {
		return gameEnded;
	}

	public static void addPerson(Person p) {
		gameObjects.put(p.getId(), p);
	}

	public int getCols() {
		return map.getCols();
	}

	public int getRows() {
		return map.getRows();
	}

	public Block getBlock(int row, int col) {
		return map.at(row, col);
	}

	public Player getPlayer(GameObjectID player) {
		return (Player) gameObjects.get(player);
	}

	public ArrayList<GameObjectID> getEveryThing() {
		ArrayList<GameObjectID> ret = new ArrayList<GameObjectID>(
				World.gameObjects.keySet());
		return ret;
	}

	public ArrayList<String> getVision(Player p) throws BozorgExceptionBase {
		if (p.getInfo(Constants.IS_ALIVE) == Constants.DEAD)
			throw new BozorgExceptionBase();
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < map.getRows(); ++i)
			for (int j = 0; j < map.getCols(); ++j)
				if (map.at(i, j).isSeenBy(p))
					ret.add(map.at(i, j).toString());
		return ret;
	}

	public ArrayList<GameObjectID> getPlayersInVision(Player p) {
		ArrayList<GameObjectID> ret = new ArrayList<GameObjectID>();
		for (Player p2 : players)
			if (!p.equals(p2) && p2.getBlock().isSeenBy(p))
				ret.add(p2.getId());
		return ret;
	}

	public Person getPerson(GameObjectID id) {
		return gameObjects.get(id);
	}

	public float getTime() {
		return (float) gameTime / Constants.FPS;
	}

	public static void addPlayer(Player p) {
		World.players.add(p);
	}

	public static Map getMap() {
		return map;
	}

	public void next50ms() {
		++gameTime;
		eh.handle();
	}

	public void movePlayer(Player p, int dir) throws BozorgExceptionBase {
		EventHandler.addEvent(new MoveEvent(p, dir));
	}

	public void attack(Player attacker, int dir) throws BozorgExceptionBase {
		EventHandler.addEvent(new AttackEvent(attacker, dir));

	}

	public GameObjectID throwFan(Player player) throws BozorgExceptionBase {
		GameObjectID ret = player.nextFan();
		EventHandler.addEvent(new WardEvent(player));
		return ret;
	}

	public void getGift(Player p) throws BozorgExceptionBase {
		EventHandler.addEvent(new AbsorbEvent(p));

	}

	public static boolean isJJVisible() {
		return JJVisible;
	}

	public static void flipJJ() {
		JJVisible = !JJVisible;
	}

	public GameObjectID[] getPlayers() {
		ArrayList<GameObjectID> allPlayers = new ArrayList<>();
		for (GameObjectID object : gameObjects.keySet())
			if (object.getType() == Player.class)
				allPlayers.add(object);
		GameObjectID[] ret = new GameObjectID[allPlayers.size()];
		return allPlayers.toArray(ret);
	}

	public ArrayList<Player> getp() {
		return players;
	}
}
