package anaofind.lib.ananetwork.test;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;
import anaofind.lib.ananetwork.socket.ServerAnaSocket;

public class SocketServerTest {

	public static void main(String[] args) throws ConnectionException {
		ServerAnaSocket server = new ServerAnaSocket(8888);
		server.connect();		
		ClientAnaSocket client = server.findClient();
		while (client == null) {
			client = server.findClient();
		}
		
		while (client.isAlive()) {
			String message = client.readMessage().get(0);
			if (message != null && message.length() > 0) {
				System.out.println("Message -> " + message);
				client.sendMessage(message);
			}
		}
		
	}
	
}
