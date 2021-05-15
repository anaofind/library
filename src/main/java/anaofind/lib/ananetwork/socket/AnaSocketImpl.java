package anaofind.lib.ananetwork.socket;

/**
 * ana server implements
 * @author anaofind
 *
 */
public abstract class AnaSocketImpl implements AnaSocket{
	
	/**
	 * the port
	 */
	private int port;
	
	/**
	 * the time out of connexion
	 */
	private int timeOutConnexion = 1000;
	
	/**
	 * the time out of message
	 */
	private int timeOutMessage = 5000;

	/**
	 * the time out of alive
	 */
	private int timeOutAlive = 10000;
	
	/**
	 * construct
	 * @param port
	 */
	public AnaSocketImpl(int port) {
		this.port = port;
	}
	
	/**
	 * set the time out of connexion
	 * @param timeOutConnexion the time out of connexion
	 */
	public void setTimeOutConnexion(int timeOutConnexion) {
		this.timeOutConnexion = timeOutConnexion;
	}
	
	/**
	 * set the time out of message
	 * @param timeOutConnexion the time out of message
	 */
	public void setTimeOutMessage(int timeOutMessage) {
		this.timeOutMessage = timeOutMessage;
	}
	
	/**
	 * set the time out of alive
	 * @param timeOutConnexion the time out of alive
	 */
	public void setTimeOutAlive(int timeOutAlive) {
		this.timeOutAlive = timeOutAlive;
	}
		
	@Override
	public int port() {
		return this.port;
	}

	@Override
	public int timeOutConnexion() {
		return this.timeOutConnexion;
	}
	
	@Override
	public int timeOutMessage() {
		return this.timeOutMessage;
	}
	
	@Override
	public int timeOutAlive() {
		return this.timeOutAlive;
	}
}
