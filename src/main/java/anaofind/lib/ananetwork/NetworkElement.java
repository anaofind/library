package anaofind.lib.ananetwork;

import anaofind.lib.ananetwork.listener.NetworkListener;
import anaofind.lib.ananetwork.protocol.Protocol;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

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
	 * the time max to wait pong response
	 * @return the time max to wait pong response
	 */
	public int timeMaxWaitMessage();
	
	/**
	 * get port
	 * @return the port
	 */
	public int portServer();
	
	/**
	 * get protocol
	 * @return the protocol
	 */
	public Protocol protocol();
	
	/**
	 * is started
	 * @return boolean : true if is runned | false else
	 */
	public boolean isRunned();
		
	/**
	 * process a message
	 * @param socket the author of message
	 * @param message the message
	 */
	public void processMessage(ClientAnaSocket socket, String message);
	
	/**
	 * add network listener
	 * @param listener the listener to add
	 */
	public void addListener(NetworkListener listener);
}
