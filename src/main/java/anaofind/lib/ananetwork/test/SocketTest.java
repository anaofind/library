package anaofind.lib.ananetwork.test;

import java.util.NoSuchElementException;
import java.util.Scanner;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.socket.*;
import anaofind.lib.async.Async;

public class SocketTest {

	public static void main(String[] args) throws ConnectionException {
		Async.call(() -> startSocketServer()).then((value) -> System.out.println("END SERVER"));
		Async.call(() -> startSocketClient()).then((value) -> System.out.println("END CLIENT"));
	}


	public static Void startSocketServer() {
		System.out.println("START SERVER");
		ServerAnaSocket server = new ServerAnaSocket(8888);
		try {
			server.connect();
			ClientAnaSocket client = server.findClient();
			while (client == null) {
				client = server.findClient();
			}
			client.setTimeOutAlive(10000);
			while (client.isAlive()) {
				String message = client.readMessage().get(0);
				if (message != null) {
					System.out.println("SERVER Message -> " + message);
					client.sendMessage(message);
				}
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}


	public static Void startSocketClient() {
		System.out.println("START ClIENT");
		ClientAnaSocket client = new ClientAnaSocket("127.0.0.1", 8888);
		client.setTimeOutAlive(10000);
		try {
			client.connect();
			Scanner sc = new Scanner(System.in);
			while (client.isAlive()) {
				System.out.print("message suivant -> ");
				String line = null;
				while (line == null) {
					try { 
						line = sc.nextLine();
					} catch (NoSuchElementException e) {}	
				}
				client.sendMessage(line);
				System.out.println("CLIENT Reponse : " + client.readMessage().get(0) + "\n");
			}
			sc.close();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}

}
