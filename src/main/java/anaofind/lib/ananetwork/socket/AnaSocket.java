package anaofind.lib.ananetwork.socket;

import java.io.IOException;

import anaofind.lib.ananetwork.exception.ConnectionException;

/**
 * ana socket
 * @author anaofind
 */
public interface AnaSocket {

	/**
	 * get port
	 * @return the port
	 */
	public abstract int port();	
	
	/**
	 * get timeout connexion
	 * @return the time out of connexion
	 */
	public int timeOutConnexion();
	
	/**
	 * get timeout message
	 * @return the time out of message
	 */
	public int timeOutMessage();
	
	/**
	 * get timeout alive
	 * @return int timeout alive
	 */
	public int timeOutAlive();
	
	/**
	 * is alive
	 * @return true if socket is alive
	 */
	public boolean isAlive();
	
	/**
	 * disconnect
	 * @throws IOException 
	 */
	public void disconnect() throws ConnectionException;
	
	/**
	 * connect
	 * @throws IOException 
	 */
	public abstract void connect() throws ConnectionException;
	
	/**
	 * is connect
	 * @return boolean : true if is connect | false else
	 */
	public boolean isConnected();
}
