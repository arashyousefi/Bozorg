package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import gamePanel.GamePanel;
import bozorg.judge.Judge;
import mapCreator.MapCreator;

public class Server {
	private MapCreator mapCreator;
	private Judge engine;
	private ServerController controller;
	private GamePanel panel;
	private int[] players;
	private ServerSocket serverSoket;
	private ClientConnection[] clientConnections;

	public void start(int mapWidth, int mapHeihgt, int numberOFPlayers) {
		mapCreator = new MapCreator(mapWidth, mapHeihgt, numberOFPlayers);
		sendToAll(new BozorgMessage("init", mapCreator, players));
		engine = new Judge();
		engine.loadMap(mapCreator.getCellTypes(), mapCreator.getWallTypes(),
				players);
		panel = new GamePanel();
		controller = new ServerController();
		controller.init(engine, panel);
		panel.init(engine, controller);
		panel.pack();
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);
		panel.repaint();
		controller.start();
	}

	public static void main(String[] args) {
		System.out.println("Enter map width and hieght");
		Scanner scanner = new Scanner(System.in);
		int w, h;
		w = scanner.nextInt();
		h = scanner.nextInt();
		System.out.println("Enter number of players");
		int n = scanner.nextInt();
		System.out.println("Enter port");
		int port = scanner.nextInt();
		Server server = new Server();
		try {
			server.serverSoket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Faild to create server");
			e.printStackTrace();
			scanner.close();
			return;
		}
		server.players = new int[n];
		server.clientConnections = (ClientConnection[]) new Object[n];
		for (int i = 0; i < n; ++i) {
			Socket socket = null;
			try {
				socket = server.serverSoket.accept();
				server.clientConnections[i] = server.new ClientConnection(
						socket);
				server.players[i] = i;
				System.out.println((i + 1) + " players conncted out of " + n);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("sending initial information");
		server.start(w, h, n);
		scanner.close();
	}

	class ClientConnection {
		ObjectInputStream in;
		ObjectOutputStream out;
		Socket socket;

		public ClientConnection(Socket s) throws IOException {
			socket = s;
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());

			Thread read = new Thread() {
				public void run() {
					while (true) {
						try {
							Object obj = in.readObject();
							// TODO handle the obj
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};

			read.setDaemon(true); // terminate when main ends
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

	public void sendToOne(int index, Object message)
			throws IndexOutOfBoundsException {
		clientConnections[index].write(message);
	}

	public void sendToAll(Object message) {
		for (ClientConnection client : clientConnections)
			client.write(message);
	}

}
