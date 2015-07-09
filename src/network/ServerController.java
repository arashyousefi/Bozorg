package network;

import gameController.GameController;
import gamePanel.GamePanel;

import java.awt.event.KeyEvent;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import bozorg.judge.Judge;

public class ServerController extends GameController {
	private Server server;
	private boolean state;

	public ServerController(Server server) {
		this.server = server;
		state = true;
	}

	@Override
	public void init(Judge engine, GamePanel panel) {
		this.engine = engine;
		this.panel = panel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void handle(BozorgMessage m) {
		if (state == false)
			return;

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

	@Override
	public void start() {
		Thread gameLoop = new Thread(new Runnable() {

			@Override
			public void run() {
				running = true;
				while (running) {
					if (state)
						updateServer();
					try {
						Thread.sleep(1000 / Constants.FPS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private void updateServer() {
				gameUpdate();
				gameRender();
				server.sendToAll(new BozorgMessage("controller",
						new BozorgMessage("update")));

			}

		});
		gameLoop.start();

	}

	public void toggleState() {
		state = !state;
	}
}
