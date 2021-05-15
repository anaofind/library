package anaofind.lib.ananetwork.server;

import java.util.List;

import anaofind.lib.ananetwork.Ping;
import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;



/**
 * simple ana server
 * @author anaofind
 */
public abstract class SimpleAnaServer extends AnaServer{


	/**
	 * process of client
	 * @param client the client
	 */
	@Override
	public void processClient(ClientAnaSocket client) {
		System.out.println(String.format("LOGIN : %s", client.address()));
		try {
			while (this.isRunned() && client.isAlive()) {
				List<String> messages = this.readMessage(client);
				for (String message : messages) {
					if (message != null && ! message.isEmpty()) {
						switch (message) {
						case Ping.PING :
							this.sendMessage(client, Ping.PONG);
							break;
						case Ping.PONG : 
							break;
						default :
							this.processMessage(client, message);
							break;
						}
					}	
				}
			}
		}
		catch (ConnectionException e) {}
		System.out.println(String.format("LOGOUT : %s", client.address()));
	}

}
