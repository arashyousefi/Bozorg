package network;

import gamePanel.BozorgMenuBar;
import gamePanel.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import mapCreator.MapCreator;
import bozorg.common.GameObjectID;
import bozorg.judge.Judge;

public class Server {
	private MapCreator mapCreator;
	private Judge engine;
	private ServerController controller;
	private GamePanel panel;
	private int[] players;
	private GameObjectID playerID[];
	private ServerSocket serverSocket;
	private ClientConnection[] clientConnections;

	public Server() {

	}

	public Server(int port, int numberOfPlayers, int mapWidth, int mapHeight)
			throws IOException {
		serverSocket = new ServerSocket(port);

		System.out.println("waiting for connections");
		players = new int[numberOfPlayers];
		clientConnections = new ClientConnection[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; ++i) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				clientConnections[i] = new ClientConnection(socket);
				players[i] = i;
				System.out.println((i + 1) + " players conncted out of "
						+ numberOfPlayers);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		start(mapWidth, mapHeight, numberOfPlayers);

	}

	public void start(int mapWidth, int mapHeihgt, int numberOFPlayers) {
		mapCreator = new MapCreator(mapWidth, mapHeihgt, numberOFPlayers);
		engine = new Judge();
		engine.loadMap(mapCreator.getCellTypes(), mapCreator.getWallTypes(),
				players);
		playerID = engine.getPlayers();

		for (int i = 0; i < clientConnections.length; ++i)
			clientConnections[i].write(new BozorgMessage("init", mapCreator,
					players, playerID[i]));

		panel = new GamePanel();
		controller = new ServerController(this);
		controller.init(engine, panel);
		panel.init(engine, controller);
		panel.setJMenuBar(new BozorgMenuBar(engine, panel));
		panel.pack();
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);
		panel.repaint();
		controller.start();
	}

	public static void main(String[] args) {
		// System.out.println("Enter map width and hieght");
		Scanner scanner = new Scanner(System.in);
		int w, h;
		w = 10;
		h = 20;
		// w = scanner.nextInt();
		// h = scanner.nextInt();
		System.out.println("Enter number of players");
		int n = scanner.nextInt();
		// System.out.println("Enter port");
		int port = 1111;// scanner.nextInt();
		Server server = new Server();
		try {
			server.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Faild to create server");
			e.printStackTrace();
			scanner.close();
			return;
		}
		System.out.println("waiting for connections");
		server.players = new int[n];
		server.clientConnections = new ClientConnection[n];
		for (int i = 0; i < n; ++i) {
			Socket socket = null;
			try {
				socket = server.serverSocket.accept();
				server.clientConnections[i] = server.new ClientConnection(
						socket);
				server.players[i] = i;
				System.out.println((i + 1) + " players conncted out of " + n);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		server.start(w, h, n);
		scanner.close();
	}

	public void handle(BozorgMessage m) {
		if (m.getType().equals("controller")) {
			sendToAll(m);
			controller.handle((BozorgMessage) m.getArgs()[0]);
		}
		if (m.getType().equals("toggleState")) {
			controller.toggleState();
		}
	}

	class ClientConnection {
		ObjectInputStream in;
		ObjectOutputStream out;
		Socket socket;
		boolean connected;

		public ClientConnection(Socket socket) throws IOException {
			this.socket = socket;
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			connected = true;

			Thread read = new Thread() {
				@Override
				public void run() {
					while (connected) {
						try {
							connected = socket.isConnected();
							Object obj = in.readUnshared();

							handle((BozorgMessage) obj);

						} catch (Exception e) {
						}
					}
				}
			};

			read.setDaemon(true);
			read.start();

		}

		public void write(Object obj) {
			if (connected)
				try {
					out.writeUnshared(obj);
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			else {
				System.out.println("disconnected");
			}
		}

	}

	public void sendToAll(Object message) {
		for (ClientConnection client : clientConnections)
			client.write(message);
	}

}
