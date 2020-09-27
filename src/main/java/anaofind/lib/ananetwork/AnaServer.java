package anaofind.lib.ananetwork;

import java.io.IOException;
import java.net.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

import anaofind.lib.ananetwork.util.UtilNetwork;


/**
 * ana server
 * @author anaofind
 *
 */
public abstract class AnaServer implements NetworkElement{

	/**
	 * class of processing client
	 * @author anaofind
	 *
	 */
	private class ProcessingClient implements Runnable{
		/**
		 * the socket of client
		 */
		private Socket client;

		/**
		 * construct
		 * @param client the socket of client
		 */
		public ProcessingClient(Socket client) {
			Objects.requireNonNull(client);
			this.client = client;
		}

		@Override
		public void run() {
			processClient(client);
		}
	}

	/**
	 * the list of client
	 */
	private List<Socket> listClient  = new ArrayList<Socket>(); 

	/**
	 * the socket server
	 */
	private ServerSocket socket;

	/**
	 * time of last ping
	 */
	private long timeLastPing = 0;

	/**
	 * the nb of max connexions
	 */
	private int maxConnexions;

	/**
	 * boolean indicate if server is close or not
	 */
	private boolean starting = false;

	/**
	 * construct
	 * @param port the port of server
	 * @param timeout the timeout
	 * @param maxConnexions the nb max of Connexions
	 */
	public AnaServer(int maxConnexions){
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
			if (maxConnexions < 1) {
				throw new Exception(String.format("the nb of max connexions must superior to 1 : %d", maxConnexions));
			}

			this.maxConnexions = maxConnexions;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * construct
	 */
	public AnaServer() {
		this(10);
	}

	@Override
	public void start() {
		// start the server if is not already started
		if ( this.socket == null || this.socket.isClosed()) {
			System.out.println("SERVER START");

			// initialisation of server socket
			try {
				this.socket = new ServerSocket(this.portServer());
				this.socket.setSoTimeout(this.timeout());

				// starting is true only if socket create success
				this.starting = true;

			} catch (IOException e) {
				e.printStackTrace();
			}

			if (this.starting) {
				// action start
				this.actionStart();

				// limit of connexions sockets
				ExecutorService service = Executors.newFixedThreadPool(this.maxConnexions);	

				// main loop
				while (this.starting) {					
					try {			
						// accept new client
						Socket socketClient = this.socket.accept();
						socketClient.setSoTimeout(this.timeout());
						this.listClient.add(socketClient);

						// start processing client
						service.submit(new ProcessingClient(socketClient));
					} 
					// continue if not client during loop time
					catch (java.io.InterruptedIOException e){} 
					catch (IOException e) {
						System.out.println(e.getMessage());
					}
					// if server close : disconnect all clients
					if (! this.starting) {
						System.out.println("CLOSE");

						// action close
						this.actionClose();

						for (Socket client : this.clients()) {
							this.disconnect(client);
						}
					}					
					// action loop
					this.actionLoop();
				}
				// close service of management threads
				service.shutdown();

				// close the server socket
				try {
					this.socket.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			System.out.println("ALREADY START"); 
		}
		System.out.println("SERVER END");
	}

	/**
	 * start server in thread
	 */
	public void startThread() {
		new Thread(( () -> start() )).start();	
	}

	@Override
	public void close() {
		this.starting = false;
	}

	/**
	 * the action of loop
	 */
	public abstract void actionLoop();

	/**
	 * the action where start
	 */
	public abstract void actionStart();

	/**
	 * the action where close
	 */
	public abstract void actionClose();

	/**
	 * disconnect a client
	 * @param client the socket of client
	 */
	public void disconnect(Socket client) {
		try {
			if (client != null && ! client.isClosed()) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * process of client
	 * @param client the client
	 */
	public void processClient(Socket client) {
		System.out.println(String.format("LOGIN : %s", client.getInetAddress()));
		try { 
			while (client != null && ! client.isClosed()) {
				String[] messages = UtilNetwork.readMessage(client, 10);
				if (messages.length > 0) {
					for (String message : messages) {
						this.timeLastPing = 0;
						switch (message) {
						case Ping.PING :
							UtilNetwork.sendMessage(client, Ping.PONG);
							break;
						case Ping.PONG : 
							break;
						default :
							this.processMessage(client, message);
						}
					}
				} else {
					this.checkClient(client);	
				}
			}	
		} catch (ConnectionException e) {
			this.disconnect(client);
		}
		this.listClient.remove(client);
		System.out.println(String.format("LOGOUT : %s", client.getInetAddress()));
	}

	/**
	 * check connexion of client
	 * @param client the socket of client
	 * @throws ConnectionException 
	 */
	public void checkClient(Socket client) throws ConnectionException {
		long currentSecond = Instant.now().getEpochSecond();
		if (this.timeLastPing == 0) {
			UtilNetwork.sendMessage(client, Ping.PING);
			this.timeLastPing = currentSecond;
		} else {
			if (currentSecond - this.timeLastPing > this.timeWaitPong() / 1000) {
				throw new ConnectionException();
			}
		}
	}

	/**
	 * send message
	 * @param client the client
	 * @param message the message to send
	 * @throws IOException 
	 */
	public void sendMessage(Socket client, String message) throws ConnectionException {
		if (this.starting) {
			UtilNetwork.sendMessage(client, message);
		}
	}

	/**
	 * get nb of clients
	 * @return the nb of clients
	 */
	public int nbClients() {
		return this.listClient.size();
	}
	
	/**
	 * get clients
	 * @return the list of client (unmodifiable)
	 */
	public Socket[] clients() {
		return this.listClient.toArray(new Socket[this.listClient.size()]);
	}
}
