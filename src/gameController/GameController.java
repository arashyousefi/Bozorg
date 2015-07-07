package gameController;

import gamePanel.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bozorg.common.GameObjectID;
import bozorg.common.objects.Constants;
import bozorg.common.objects.World;
import bozorg.judge.Judge;

public class GameController implements KeyListener {
	protected Judge engine;
	protected GameObjectID[] players;
	protected GamePanel panel;
	protected boolean running = false;

	public void init(Judge engine, GamePanel panel) {
		this.engine = engine;
		this.panel = panel;
		players = engine.getPlayers();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			try {
				engine.movePlayer(players[0], Constants.RIGHT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			try {
				engine.movePlayer(players[0], Constants.LEFT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_UP)
			try {
				engine.movePlayer(players[0], Constants.UP);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			try {
				engine.movePlayer(players[0], Constants.DOWN);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_D)
			try {
				engine.movePlayer(players[1], Constants.RIGHT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_W)
			try {
				engine.movePlayer(players[1], Constants.UP);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_S)
			try {
				engine.movePlayer(players[1], Constants.DOWN);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_A)
			try {
				engine.movePlayer(players[1], Constants.LEFT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD8)
			try {
				engine.attack(players[0], Constants.UP);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD4)
			try {
				engine.attack(players[0], Constants.LEFT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD5)
			try {
				engine.attack(players[0], Constants.NONE);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD6)
			try {
				engine.attack(players[0], Constants.RIGHT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD2)
			try {
				engine.attack(players[0], Constants.DOWN);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_T)
			try {
				engine.attack(players[1], Constants.UP);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_F)
			try {
				engine.attack(players[1], Constants.LEFT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_G)
			try {
				engine.attack(players[1], Constants.NONE);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_H)
			try {
				engine.attack(players[1], Constants.RIGHT);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_B)
			try {
				engine.attack(players[1], Constants.DOWN);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			try {
				engine.throwFan(players[1]);
			} catch (Exception exception) {

			}
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			try {
				engine.throwFan(players[0]);
			} catch (Exception exception) {

			}
		panel.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void start() {
		Thread gameLoop = new Thread(new Runnable() {

			@Override
			public void run() {
				running = true;
				while (running) {

					gameUpdate();
					gameRender();
					try {
						Thread.sleep(1000 / Constants.FPS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		gameLoop.start();

	}

	public void gameRender() {
		panel.repaint();
	}

	public void gameUpdate() {
		engine.next50milis();
		if (World.gameEnded())
			running = false;
	}

}
