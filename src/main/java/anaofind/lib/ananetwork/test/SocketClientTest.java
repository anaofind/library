package anaofind.lib.ananetwork.test;

import java.util.Scanner;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

public class SocketClientTest {

	public static void main(String[] args) {
		System.out.println("START ClIENT");
		ClientAnaSocket client = new ClientAnaSocket("127.0.0.1", 8888);
		try {
			client.connect();
			while (client.isAlive()) {
				System.out.print("message suivant -> ");
				Scanner sc = new Scanner(System.in);
				String line = sc.nextLine();
				sc.close();
				client.sendMessage(line);
				System.out.println("Reponse : " + client.readMessage().get(0) + "\n");
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
