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
	private long timeLastPing = 0;
	
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
	 */
	private synchronized void processMessages() {
		this.actionStart();
		while (this.server != null && !this.server.isClosed()) {
			try {
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
			} catch (IOException e) {
				this.close();
				this.connexionBroken();
			}
		}
	}

	/**
	 * check connection
	 */
	public void checkConnection() {
		try {
			long currentSecond = Instant.now().getEpochSecond();
			if (this.timeLastPing == 0) {
				UtilNetwork.sendMessage(this.server, Ping.PING);
				this.timeLastPing = currentSecond;
			} else {
				if (currentSecond - this.timeLastPing > this.timeWaitPong() / 1000) {
					this.close();
					this.connexionBroken();
				}
			}
		} catch (IOException e) {
			this.close();
			this.connexionBroken();
		}
	}

	@Override
	public void start() {
		if (this.server == null || this.server.isClosed()) {
			try {				
				this.starting = true;
				this.server = new Socket();
				this.server.setSoTimeout(this.timeout());
				this.server.connect(new InetSocketAddress(this.addressServer, this.portServer()), this.timeout());	

				this.processMessages();

			} catch (UnknownHostException e) {
				this.close();
				this.hostNotFound();
			} catch (IOException e) {
				this.close();
				this.cannotConnect();
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
		this.starting = false;
		try {
			if (this.server != null) {
				this.actionClose();
				this.server.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * send message
	 * @param message the message to send
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws IOException {
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