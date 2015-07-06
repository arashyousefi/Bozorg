package network;

import java.awt.event.KeyEvent;

import bozorg.common.GameObjectID;
import bozorg.common.exceptions.BozorgExceptionBase;
import bozorg.common.objects.Constants;
import gameController.GameController;

public class ClientController extends GameController {
	private Client client;
	private int player;

	public ClientController(Client client, int player) {
		this.client = client;
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", players[player], Constants.RIGHT)));

		if (e.getKeyCode() == KeyEvent.VK_UP)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", players[player], Constants.UP)));

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", players[player], Constants.LEFT)));
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("move", players[player], Constants.DOWN)));

		if (e.getKeyCode() == KeyEvent.VK_W)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", players[player], Constants.UP)));

		if (e.getKeyCode() == KeyEvent.VK_A)
			client.sendToServer(new BozorgMessage(
					"controller",
					new BozorgMessage("attack", players[player], Constants.LEFT)));

		if (e.getKeyCode() == KeyEvent.VK_S)
			client.sendToServer(new BozorgMessage(
					"controller",
					new BozorgMessage("attack", players[player], Constants.DOWN)));

		if (e.getKeyCode() == KeyEvent.VK_D)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("attack", players[player],
							Constants.RIGHT)));

		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			client.sendToServer(new BozorgMessage(
					"controller",
					new BozorgMessage("attack", players[player], Constants.NONE)));

		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
			client.sendToServer(new BozorgMessage("controller",
					new BozorgMessage("throwfan", players[player])));

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
