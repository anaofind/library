package anaofind.lib.ananetwork.util;

import java.io.IOException;
import java.util.List;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.exception.ConnectionInfo;
import anaofind.lib.ananetwork.protocol.Protocol;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

/**
 * util network
 * anaofind
 */
public class UtilNetwork {		

	/**
	 * send message 
	 * @param socket the socket
	 * @param message the message to send
	 */
	public static void sendMessage(ClientAnaSocket socket, Protocol protocol, String message) throws ConnectionException {
		if (! socket.isAlive()) {
			throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
		}
		String messageEncoding = protocol.encodingMessage(message);
		socket.sendMessage(messageEncoding);
	}

	/**
	 * read messages
	 * @param socket the socket
	 * @param protocol the procol
	 * @return the message
	 * @throws IOException 
	 */
	public static List<String> readMessage(ClientAnaSocket socket, Protocol protocol) throws ConnectionException {
		if (! socket.isAlive()) {
			throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
		}
		List<String> messages = socket.readMessage();
		for (String message : messages) {
			if (message != null && ! message.isEmpty()) {
				message = protocol.decodinMessage(message);
			}	
		}
		return messages;
	}
}
