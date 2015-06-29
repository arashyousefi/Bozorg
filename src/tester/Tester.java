package tester;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Block;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Player;
import bozorg.judge.Judge;

public class Tester {

	static int[][] wallsType = { { 9, 13, 5, 3 }, { 8, 3, 9, 6 },
			{ 10, 12, 6, 9 }, { 12, 5, 5, 6 } };
	static int[][] cellsType = { { 0, 3, 0, 4 }, { 0, 0, 0, 0 },
			{ 6, 0, 3, 1 }, { 0, 0, 0, 0 } };
	static int[] players = { 0, 2 };
	static Judge judge = new Judge();
	static ArrayList<GameObjectID> everyThing;
	static ArrayList<Player> playersAL = new ArrayList<>();
	Player p1 = playersAL.get(0);
	GameObjectID id1 = p1.getId();
	Player p2 = playersAL.get(1);
	GameObjectID id2 = p2.getId();

	@BeforeClass
	public static void pre() {
		// ssss
		everyThing = judge.loadMap(cellsType, wallsType, players);
		playersAL = judge.getp();
	}

	public void waitFor(int times) {
		for (int i = 0; i < times; ++i)
			judge.next50milis();
	}

	@Test
	public void testGetMapCellTypeIntInt() {
		assertEquals(judge.getMapCellType(2, 3), 0);
		assertEquals(judge.getMapCellType(3, 0), 4);
		assertEquals(judge.getMapCellType(3, 2), 1);
		assertEquals(judge.getMapCellType(0, 2), 6);
		assertEquals(judge.getMapCellType(1, 0), 3);
	}

	@Test
	public void testGetMapCellTypeIntIntGameObjectID() {
		assertEquals(Constants.BONUS_CELL, judge.getMapCellType(3, 0, id1));
		assertEquals(Constants.NONE_CELL, judge.getMapCellType(1, 3, id1));
	}

	@Test
	public void testGetMapWallTypeIntInt() {
		assertEquals(Constants.TFFT_WALL, judge.getMapWallType(0, 0));
		assertEquals(Constants.TTFF_WALL, judge.getMapWallType(1, 2));
		assertEquals(5, judge.getMapWallType(2, 3));
		assertEquals(3, judge.getMapWallType(3, 0));
	}

	@Test
	public void testGetMapWallTypeIntIntGameObjectID() {
		assertEquals(Constants.TFFT_WALL, judge.getMapWallType(0, 0, id1));
		assertEquals(Constants.TTFF_WALL, judge.getMapWallType(1, 2, id1));
		assertEquals(5, judge.getMapWallType(2, 3, id1));
		assertEquals(3, judge.getMapWallType(3, 0, id1));
	}

	@Test
	public void testMovePlayer() {
		try {
			judge.next50milis();
			judge.movePlayer(id1, Constants.RIGHT);
			assertEquals(0, p1.getInfo(Constants.ROW));
			assertEquals(2, p1.getInfo(Constants.COL));
			waitFor(10);
			judge.movePlayer(id1, Constants.LEFT);
			assertEquals(0, p1.getInfo(Constants.ROW));
			assertEquals(1, p1.getInfo(Constants.COL));

		} catch (Exception e) {
			System.out.println("fail");
			fail("fail dg");
		}
		try {
			judge.movePlayer(id1, Constants.UP);
			fail("exception expected");
		} catch (Exception e) {
			assertEquals(BozorgExceptionBase.class, e.getClass());
		}

	}

	@Test
	public void testAttack() {

		try {
			judge.movePlayer(id1, Constants.RIGHT);
			judge.movePlayer(id2, Constants.UP);
			waitFor(10);
			judge.movePlayer(id1, Constants.RIGHT);
			judge.movePlayer(id2, Constants.RIGHT);
			judge.attack(id1, Constants.DOWN);
			judge.attack(id2, Constants.UP);
			assertEquals(100 - p2.getInfo(Constants.POWER),
					p1.getInfo(Constants.HEALTH));
			assertEquals(100 - p1.getInfo(Constants.POWER),
					p2.getInfo(Constants.HEALTH));
			waitFor(10);
			judge.movePlayer(id1, Constants.LEFT);
			judge.movePlayer(id2, Constants.LEFT);
			waitFor(10);
			judge.movePlayer(id1, Constants.LEFT);
			judge.movePlayer(id2, Constants.DOWN);
			waitFor(10);

		} catch (Exception e) {
			fail();
		}
		try {
			judge.attack(id1, Constants.DOWN);
			fail();
		} catch (Exception e) {
			assertEquals(BozorgExceptionBase.class, e.getClass());

		}
	}

	@Test
	public void testThrowFan() {
		try {
			GameObjectID id3 = judge.throwFan(id1);
			assertNotNull(id3);
			assertEquals((int) p1.getInfo(Constants.ROW),
					(int) judge.getInfo(id3).get(Constants.ROW));
			assertEquals((int) p1.getInfo(Constants.COL),
					(int) judge.getInfo(id3).get(Constants.COL));
			Block b = p1.getBlock();
			assertTrue(b.hasFan(p1));

		} catch (Exception e) {

		}
		try {
			judge.throwFan(id2);
			fail();
		} catch (Exception e) {
			assertEquals(BozorgExceptionBase.class, e.getClass());

		}
	}

	@Test
	public void testGetGift() {
		try {
			judge.movePlayer(id1, Constants.RIGHT);
			waitFor(10);
			judge.movePlayer(id1, Constants.RIGHT);
			judge.getGift(id1);
			assertTrue(p1.hasPowerUp(Constants.HASTE));
			waitFor(10);
			judge.movePlayer(id1, Constants.LEFT);
			waitFor(10);
			judge.movePlayer(id1, Constants.LEFT);
			waitFor(10);

		} catch (Exception e) {

		}
	}

	@Test
	public void testGetVision() {
		try {
			ArrayList<String> temp = judge.getVision(id1);
			System.out.println(temp);
		} catch (Exception e) {

		}
	}
}
