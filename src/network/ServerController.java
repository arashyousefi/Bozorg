package network;

import gameController.GameController;
import gamePanel.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.common.objects.World;
import bozorg.judge.Judge;

public class ServerController extends GameController {
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
					server.sendToAll(new BozorgMessage("controller",
							new BozorgMessage("update")));
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

	public void handle(BozorgMessage m) {
		if (m.getType().equals("move")) {
			System.out.println(engine.IDToPlayer((GameObjectID) m.getArgs()[0])
					.getName());
		}
		if (m.getType().equals("move")) {
			try {
				engine.movePlayer((GameObjectID) m.getArgs()[0],
						(int) m.getArgs()[1]);
			} catch (BozorgExceptionBase e) {
				e.printStackTrace();
			}
			return;
		}

		if (m.getType().equals("attack")) {
			try {
				engine.attack((GameObjectID) m.getArgs()[0],
						(int) m.getArgs()[1]);
			} catch (BozorgExceptionBase e) {
				e.printStackTrace();
			}
			return;
		}

		if (m.getType().equals("throwfan")) {
			try {
				engine.throwFan((GameObjectID) m.getArgs()[0]);
			} catch (BozorgExceptionBase e) {
				e.printStackTrace();
			}
			return;
		}

	}
}
