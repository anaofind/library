package anaofind.lib.ananetwork;

import java.io.IOException;
import java.net.*;
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
	 * port of server
	 */
	private int port;

	/**
	 * the time loop
	 */
	private int timeLoop;
	
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
	 * @param timeLoop the time of loop
	 * @param maxConnexions the nb max of Connexions
	 */
	public AnaServer(int port, int timeLoop, int maxConnexions){
		try {
			if (port < 1 ) {
				throw new Exception(String.format("the port must superior to 1 : %d", port));
			}
			if (timeLoop < 1) {
				throw new Exception(String.format("the time loop must superior to 1 : %d", timeLoop));
			}
			if (maxConnexions < 1) {
				throw new Exception(String.format("the nb of max connexions must superior to 1 : %d", timeLoop));
			}
			this.port = port;
			this.timeLoop = timeLoop;
			this.maxConnexions = maxConnexions;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * construct
	 * @param port the port of server
	 * @param timeLoop the time of loop
	 */
	public AnaServer(int port, int timeLoop) {
		this(port, timeLoop, 10);
	}
	
	/**
	 * construct
	 * @param port the port of server
	 */
	public AnaServer(int port) {
		this(port, 1000, 10);
	}
	
	/**
	 * construct
	 */
	public AnaServer() {
		this(8888, 1000, 10);
	}

	@Override
	public void start() {
		// start the server if is not already started
		if ( ! this.starting) {
			System.out.println("SERVER START");
			
			// initialisation of server socket
			try {
				this.socket = new ServerSocket(this.port);
				this.socket.setSoTimeout(this.timeLoop);
				
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
						socketClient.setSoTimeout(this.socket.getSoTimeout());
						this.listClient.add(socketClient);
						
						// start processing client
						service.execute(new ProcessingClient(socketClient));
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
					// remove and disconnect client with problem connexion
					this.checkClients();
					
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
	 * check the good connexion of clients. if connexion not good, 
	 * we remove the client
	 */
	private void checkClients() {
		for (Socket client : this.clients()) {
			try {
				UtilNetwork.sendMessage(client, "PING");
			} catch (IOException e) {
				this.disconnect(client);
			}
		}
	}

	/**
	 * disconnect a client
	 * @param client the socket of client
	 */
	public void disconnect(Socket client) {
		try {
			if (client != null && ! client.isClosed()) {
				client.close();	
			}
			this.listClient.remove(client);
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
		while (client != null && ! client.isClosed()) {
			String message = UtilNetwork.readMessage(client);
			if (message  != null) {
				new Thread(() -> this.processMessage(client, message)).start();	
			} else {
				this.disconnect(client);
			}
		}
		System.out.println(String.format("LOGOUT : %s", client.getInetAddress()));
	}

	/**
	 * send message
	 * @param client the client
	 * @param message the message to send
	 * @throws IOException 
	 */
	public void sendMessage(Socket client, String message) throws IOException {
		if (this.starting) {
			UtilNetwork.sendMessage(client, message);
		}
	}
	
	/**
	 * get clients
	 * @return the list of client (unmodifiable)
	 */
	public Socket[] clients() {
		return this.listClient.toArray(new Socket[this.listClient.size()]);
	}
}
