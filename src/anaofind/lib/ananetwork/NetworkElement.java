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
	 * get timeout
	 * @return the timeout
	 */
	public int timeout();
	
	/**
	 * get port
	 * @return the port
	 */
	public int portServer();
	
	/**
	 * get the time of wait for pong
	 * @return the time of wait for pong
	 */
	public int timeWaitPong();
	
	/**
	 * process a message
	 * @param socket the author of message
	 * @param message the message
	 */
	public void processMessage(Socket socket, String message);
}
