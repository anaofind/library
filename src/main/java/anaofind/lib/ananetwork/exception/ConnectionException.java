package anaofind.lib.ananetwork.exception;

/**
 * connexion exception
 * @author anaofing
 *
 */
public class ConnectionException extends Exception{

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * the info
	 */
	private ConnectionInfo info;
	
	/**
	 * construct
	 * @param info the info
	 */
	public ConnectionException(ConnectionInfo info) {
		super();
		this.info = info;
	}
	
	/**
	 * get info
	 * @return the info
	 */
	public ConnectionInfo getInfo() {
		return this.info;
	}
}
