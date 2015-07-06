package network;

import gamePanel.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
		controller = new ClientController(this, this.player);
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
		try {
			System.out.println("connecting");
			Client client = new Client(IP, port);
			System.out.println("connected!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();

	}

	public void handle(BozorgMessage m) {
		System.out.println(m.getType());
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
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());

			Thread read = new Thread() {
				public void run() {
					while (true) {
						try {
							Object obj = in.readObject();
							handle((BozorgMessage) obj);
						} catch (Exception e) {
							// e.printStackTrace();
						}
					}
				}
			};

//			read.setDaemon(true);
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
