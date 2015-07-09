package bozorg.common;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import bozorg.common.objects.Block;
import bozorg.common.objects.Constants;
import bozorg.common.objects.Player;
import bozorg.common.objects.Position;
import bozorg.common.objects.World;

@SuppressWarnings("serial")
public class AI implements Serializable {
	boolean edge_matrix[][] = new boolean[1500][1500];

	public int doTurn(World world, GameObjectID player) {
		int z;
		Player p = (Player) world.getPerson(player);
		makeGraph(world, p);
		Block block = world.getPerson(player).getBlock();
		z = bfsTo(world, block, Constants.JJ_CELL);
		if (z == -1) {
			z = bfsTo(world, block, Constants.BONUS_CELL);
			if (z == -1)
				z = bfsTo(world, block, Constants.DARK_CELL);
		}
		if (p.canMove())
			return z;
		else
			return -1;

	}

	private int bfsTo(World world, Block block, int target) {
		Queue<Integer> xq = new LinkedList<Integer>(), yq = new LinkedList<Integer>();
		int width = world.getCols();
		int height = world.getRows();
		boolean mark[][] = new boolean[50][50];
		for (int i = 0; i < 50; ++i)
			for (int j = 0; j < 50; ++j)
				mark[i][j] = false;
		Position p, p1;
		int x1 = block.getPos().getX();
		int y1 = block.getPos().getY();
		int s, t;

		xq.add(x1 * height + y1);

		s = xq.peek();
		p1 = world.getMap().at(s / height, s % height).getPos();
		xq.poll();
		for (int j = 0; j < 4; j++) {
			p = p1.move(j);
			if ((p.getX() >= 0) && (p.getX() < width) && (p.getY() >= 0)
					&& (p.getY() < height)
					&& (edge_matrix[s][p.getX() * height + p.getY()] == true)
					&& (!mark[p.getX()][p.getY()])) {
				mark[p.getX()][p.getY()] = true;
				xq.add(p.getX() * height + p.getY());
				yq.add(j);
				if (world.getMap().at(p).getCellType() == target) {
					return j;
				}
			}
		}

		int con = 0;
		while ((!xq.isEmpty()) && (con < 1500)) {
			con++;
			s = xq.peek();
			t = yq.peek();
			p1 = world.getMap().at(s / height, s % height).getPos();
			xq.poll();
			yq.poll();
			for (int j = 0; j < 4; j++) {
				p = p1.move(j);
				if ((p.getX() >= 0)
						&& (p.getX() < width)
						&& (p.getY() >= 0)
						&& (p.getY() < height)
						&& (edge_matrix[s][p.getX() * height + p.getY()] == true)
						&& (!mark[p.getX()][p.getY()])) {
					mark[p.getX()][p.getY()] = true;
					xq.add(p.getX() * height + p.getY());
					yq.add(t);
					if (world.getMap().at(p).getCellType() == target)
						return t;
				}
			}
		}
		return -1;
	}

	private void makeGraph(World world, Player player) {
		for (int i = 0; i < 1500; i++)
			for (int j = 0; j < 1500; j++)
				edge_matrix[i][j] = false;
		int width = world.getCols();
		int height = world.getRows();
		int x1;
		int y1;
		int jj;
		int ss;
		Block b1;
		Position p1, p;

		for (int i = 0; i < width * height; ++i) {
			x1 = i / height;
			y1 = i % height;
			b1 = world.getMap().at(x1, y1);
			p1 = b1.getPos();
			jj = 1;
			if (b1.isSeenBy(player))
				for (int j = 0; j < 4; ++j) {
					p = p1.move(j);
					jj *= 2;
					ss = (15 - b1.getWall()) & jj;
					if (p.getX() >= 0 && p.getX() < width && p.getY() >= 0
							&& p.getY() < height && (ss == jj)) {
						edge_matrix[i][p1.getX() * height + p1.getY()] = true;
						edge_matrix[p1.getX() * height + p1.getY()][i] = true;
					}
				}
		}

	}
}
