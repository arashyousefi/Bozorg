package network;

import gameController.GameController;
import gamePanel.BozorgMenuBar;
import gamePanel.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import mapCreator.MapCreator;
import bozorg.judge.Judge;

public class Client {
	private ConnectionToServer server;
	private Socket socket;
	private Judge engine;
	private ClientController controller;
	private GamePanel panel;
	private int player;

	public Client(String IP, int port) {
		try {
			socket = new Socket(IP, port);
			server = new ConnectionToServer(socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(MapCreator mapCreator, int[] players, int player) {
		this.player = player;
		engine = new Judge();
		engine.loadMap(mapCreator.getCellTypes(), mapCreator.getWallTypes(),
				players);
		panel = new GamePanel();
		controller = new ClientController(this, player);
		controller.init(engine, panel);
		panel.init(engine, controller);
		panel.pack();
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);
		panel.repaint();
		// controller.start();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter host IP");
		String IP = scanner.next();
		System.out.println("Enter host port");
		int port = scanner.nextInt();
		Client client = new Client(IP, port);

		scanner.close();

	}

	public void handle(BozorgMessage m) {
		if (m.getType().equals("init")) {
			init((MapCreator) m.getArgs()[0], (int[]) m.getArgs()[1],
					(int) m.getArgs()[2]);
			return;
		}
		if (m.getType().equals("controller")) {
			controller.handle((BozorgMessage) m.getArgs()[0]);
		}

	}

	public void sendToServer(Object obj) {
		server.write(obj);
	}

	public class ConnectionToServer {
		ObjectInputStream in;
		ObjectOutputStream out;
		Socket socket;

		ConnectionToServer(Socket socket) throws IOException {
			this.socket = socket;
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());

			Thread read = new Thread() {
				public void run() {
					while (true) {
						try {
							Object obj = in.readObject();
							handle((BozorgMessage) obj);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			};

			read.setDaemon(true);
			read.start();
		}

		public void write(Object obj) {
			try {
				out.writeObject(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
