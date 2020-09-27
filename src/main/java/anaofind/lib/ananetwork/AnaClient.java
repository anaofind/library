package anaofind.lib.ananetwork;

import java.io.IOException;
import java.net.*;
import java.time.Instant;

import anaofind.lib.ananetwork.util.UtilNetwork;

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
	private boolean starting = false;

	/**
	 * during since last ping
	 */
	private long timeLastPing;

	/**
	 * the socket of server
	 */ 
	private Socket server;

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
		this.addressServer = addressServer;
		try {
			if (this.portServer() < 1 ) {
				throw new Exception(String.format("the port must superior to 1 : %d", this.portServer()));
			}
			if (this.timeout() < 1) {
				throw new Exception(String.format("the time loop must superior to 1 : %d", this.timeout()));
			}
			if (this.timeWaitPong() < 1) {
				throw new Exception(String.format("the time wait for pong must superior to 1 : %d", this.timeWaitPong()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * choice address of server and port of server
	 * @param addressServer the address of server
	 */
	public void bind(String addressServer) {
		this.addressServer = addressServer;
	}

	/**
	 * action where client is started
	 */
	public abstract void actionStart();

	/**
	 * action where client is closed
	 */
	public abstract void actionClose();

	/**
	 * process messages
	 * @throws ConnectionException 
	 */
	private synchronized void processMessages() throws ConnectionException {
		this.timeLastPing = 0;
		this.actionStart();		
		while (this.server != null && !this.server.isClosed()) {
			String[] messages = UtilNetwork.readMessage(this.server, 10);
			if (messages.length > 0) {
				this.timeLastPing = 0;
				for (String message : messages) {
					switch (message) {
					case Ping.PING :
						UtilNetwork.sendMessage(this.server, Ping.PONG);
						break;
					case Ping.PONG : 
						break;
					default :
						this.processMessage(this.server, message);
					}	
				}
			} else {
				this.checkConnection();
			}	
		}	
	}

	/**
	 * check connection
	 * @throws ConnectionException 
	 */
	public void checkConnection() throws ConnectionException {
		long currentSecond = Instant.now().getEpochSecond();
		if (this.timeLastPing == 0) {
			UtilNetwork.sendMessage(this.server, Ping.PING);
			this.timeLastPing = currentSecond;
		} else {
			if (currentSecond - this.timeLastPing > this.timeWaitPong() / 1000) {
				throw new ConnectionException();
			}
		}
	}

	@Override
	public void start() {
		if (! this.starting) {
			try {				
				this.starting = true;
				this.server = new Socket();
				this.server.setSoTimeout(this.timeout());
				this.server.connect(new InetSocketAddress(this.addressServer, this.portServer()), this.timeout());	
				
				this.processMessages();
				this.close();

			} catch (UnknownHostException e) {
				this.close();
				this.hostNotFound();
			} catch (IOException e) {
				this.close();
				this.cannotConnect();
			} catch (ConnectionException e) {
				this.close();
				this.connexionBroken();
			}
		} else {
			System.out.println("ALREADY START");
		}
	}

	/**
	 * start client in thread
	 */
	public void startThread() {
		new Thread(( () -> start() )).start();	
	}

	@Override
	public void close() {
		try {
			if (this.server != null) {
				this.actionClose();
				this.server.close();
				this.server = null;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		this.starting = false;
	}

	/**
	 * send message
	 * @param message the message to send
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws ConnectionException {
		if (this.starting) {
			UtilNetwork.sendMessage(this.server, message);
		}
	}

	/**
	 * action where connexion broken
	 */
	public abstract void connexionBroken();

	/**
	 * action where host not found
	 */
	public abstract void hostNotFound();

	/**
	 * action when cannot connect
	 */
	public abstract void cannotConnect();

	/**
	 * get address of server
	 * @return the address of server
	 */
	public String addressServer() {
		return this.addressServer;
	}
}