package network;

import gameController.GameController;

import java.awt.event.KeyEvent;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;

public class ClientController extends GameController {
	private Client client;
	private GameObjectID ID;

	public ClientController(Client client, GameObjectID ID) {
		this.client = client;
		this.ID = ID;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", ID, Constants.RIGHT)));

		if (e.getKeyCode() == KeyEvent.VK_UP)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", ID, Constants.UP)));

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", ID, Constants.LEFT)));
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", ID, Constants.DOWN)));

		if (e.getKeyCode() == KeyEvent.VK_W)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", ID, Constants.UP)));

		if (e.getKeyCode() == KeyEvent.VK_A)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", ID, Constants.LEFT)));

		if (e.getKeyCode() == KeyEvent.VK_S)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", ID, Constants.DOWN)));

		if (e.getKeyCode() == KeyEvent.VK_D)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", ID, Constants.RIGHT)));

		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", ID, Constants.NONE)));

		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("throwfan", ID)));

		if (e.getKeyCode() == KeyEvent.VK_F9)
			client.sendToServer(new BozorgMessage("toggleState"));

	}

	public void handle(BozorgMessage m) {

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
		if (m.getType().equals("update")) {

			gameUpdate();
			gameRender();
		}

	}
}
