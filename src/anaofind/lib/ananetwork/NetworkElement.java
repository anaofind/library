package anaofind.lib.ananetwork;

import java.net.Socket;

/**
 * network element
 * @author anaofind
 */
public interface NetworkElement {

	/**
	 * start
	 */
	public void start();
	
	/**
	 * close
	 */
	public void close();	
	
	/**
	 * process a message
	 * @param socket the author of message
	 * @param message the message
	 */
	public void processMessage(Socket socket, String message);
}
