package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	ConnectionToServer server;
	Socket socket;

	public Client(String IP, int port) {
		try {
			socket = new Socket(IP, port);
			server = new ConnectionToServer(socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
							// TODO
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
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
