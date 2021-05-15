package anaofind.lib.ananetwork.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import anaofind.lib.ananetwork.NetworkElement;
import anaofind.lib.ananetwork.Ping;
import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.exception.ConnectionInfo;
import anaofind.lib.ananetwork.listener.NetworkListener;
import anaofind.lib.ananetwork.protocol.NoProtocol;
import anaofind.lib.ananetwork.protocol.Protocol;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;
import anaofind.lib.ananetwork.util.UtilNetwork;
import anaofind.lib.async.Async;

/**
 * ana client
 * @author anaofind
 *
 */
public abstract class AnaClient implements NetworkElement{

	/**
	 * the address of server
	 */
	private String addressServer;

	/**
	 * boolean indicate if client is start or not
	 */
	private boolean runned = false;

	/**
	 * the socket
	 */ 
	private ClientAnaSocket socket;

	/**
	 * the protocol used
	 */
	private Protocol protocol;

	/**
	 * network listener
	 */
	private List<NetworkListener> listeners = new ArrayList<NetworkListener>();

	/**
	 * construct by default
	 */
	public AnaClient() {
		this("127.0.0.1");
	}

	/**
	 * construct
	 * @param addressServer the address of server
	 * @param portServer the port of server
	 * @param timeout the timeout of server
	 */
	public AnaClient(String addressServer) {
		this(addressServer, new NoProtocol());
	}

	/**
	 * construct
	 * @param addressServer the address of server
	 * @param portServer the port of server
	 * @param timeout the timeout of server
	 */
	public AnaClient(String addressServer, Protocol protocol) {
		Objects.requireNonNull(addressServer);
		Objects.requireNonNull(protocol);
		this.protocol = protocol;
		this.addressServer = addressServer;
		try {
			if (this.portServer() < 1 ) {
				throw new Exception(String.format("the port must superior to 1 : %d", this.portServer()));
			}
			if (this.timeout() < 1) {
				throw new Exception(String.format("the time loop must superior to 1 : %d", this.timeout()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * set address server
	 * @param addressServer the address server
	 */
	public void setAddress(String addressServer) {
		this.addressServer = addressServer;
	}

	/**
	 * process messages
	 * @throws ConnectionException 
	 */
	private void processMessages() throws ConnectionException {
		this.sendMessage(Ping.PING);
		for (NetworkListener listener : this.listeners) {
			listener.starting();
		}
		while (this.isRunned()) {
			for (NetworkListener listener : this.listeners) {
				listener.looping();
			}
			List<String> messages = this.readMessage();
			for (String message : messages) {
				if (message != null && ! message.isEmpty()) {
					switch (message) {
					case Ping.PING :
						break;
					case Ping.PONG :
						this.sendMessage(Ping.PING);
						break;
					default :
						this.processMessage(this.socket, message);
						break;
					}	
				}	
			}
			this.checkConnection();
		}
		if (this.socket != null) {
			this.socket.disconnect();	
		}
	}

	/**
	 * check connection
	 * @throws ConnectionException 
	 */
	public void checkConnection() throws ConnectionException {
		if (! this.socket.isAlive()) {
			throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
		}	
	}

	/**
	 * run ana client
	 * @return the connection info
	 */
	private ConnectionInfo run() {
		ConnectionInfo info = ConnectionInfo.NO_PROBLEM;
		if (! this.isRunned()) {
			this.runned = true;
			try {
				this.socket = new ClientAnaSocket(this.addressServer(), this.portServer());
				this.socket.setTimeOutConnexion(this.timeout());
				this.socket.setTimeOutMessage(10);
				this.socket.setTimeOutAlive(this.timeMaxWaitMessage());
				this.socket.connect();

				this.processMessages();
			} catch (ConnectionException e) {
				this.runned = false;
				info = e.getInfo();	
			}
			this.socket = null;
		}
		return info;
	}

	/**
	 * start client in thread
	 */
	@Override
	public void start() {
		Async.call(() -> this.run()).then((info) -> {
			switch (info) {
			case HOST_NOT_FOUND : 
				for (NetworkListener listener : listeners) {
					listener.hostNotFound();
				}
				break;
			case CANNOT_CONNECT : 
				for (NetworkListener listener : listeners) {
					listener.cannotConnect();
				}
				break;
			case CONNECTION_BROKEN : 
				for (NetworkListener listener : listeners) {
					listener.connectionBroken();
				}
				break;
			case NO_PROBLEM:
				for (NetworkListener listener : listeners) {
					listener.closing();
				};
			default:
				break;
			}
		});
	}

	@Override
	public void close() {
		this.runned = false;
	}

	/**
	 * send message
	 * @param message the message to send
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws ConnectionException {
		if (this.isRunned()) {
			UtilNetwork.sendMessage(this.socket, this.protocol, message);
		}
	}

	/**
	 * read message
	 * @return the message
	 * @throws ConnectionException
	 */
	public List<String> readMessage() throws ConnectionException {
		if (this.isRunned()) {
			List<String> messages =  UtilNetwork.readMessage(this.socket, this.protocol());
			return messages;
		}
		return null;
	}

	/**
	 * get address of server
	 * @return the address of server
	 */
	public String addressServer() {
		return this.addressServer;
	}

	@Override
	public Protocol protocol() {
		return this.protocol;
	}

	@Override
	public void addListener(NetworkListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public boolean isRunned() {
		return this.runned;
	}
}