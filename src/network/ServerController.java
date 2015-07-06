package network;

import gameController.GameController;
import gamePanel.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bozorg.common.GameObjectID;
import bozorg.common.objects.Constants;
import bozorg.common.objects.World;
import bozorg.judge.Judge;

public class ServerController extends GameController implements KeyListener {
	private Judge engine;
	private GamePanel panel;
	private boolean running = false;
	private Server server;

	public ServerController(Server server) {
		this.server = server;
	}

	public void init(Judge engine, GamePanel panel) {
		this.engine = engine;
		this.panel = panel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
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
