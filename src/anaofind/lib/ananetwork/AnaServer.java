package anaofind.lib.ananetwork;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;


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
	protected List<Socket> listClient  = new ArrayList<Socket>(); 

	/**
	 * the socket server
	 */
	private ServerSocket socket;

	/**
	 * boolean indicate if server is close or not
	 */
	private boolean starting = false;

	/**
	 * construct
	 * @param port the port of server
	 * @param timeLoop the time of loop
	 */
	public AnaServer(int port, int timeLoop) {
		try {
			this.socket = new ServerSocket(port);
			this.socket.setSoTimeout(timeLoop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * construct
	 */
	public AnaServer() {
		this(8888, 1000);
	}
	
	@Override
	public void start() {
		if ( ! this.starting) {
			this.starting = true;
			System.out.println("SERVER START");
			Executor service = Executors.newFixedThreadPool(10);
			while (this.starting) {	
				try {
					Socket socketClient = this.socket.accept();
					this.listClient.add(socketClient);
					service.execute(new ProcessingClient(socketClient));
				} 
				catch (java.io.InterruptedIOException e ){
					if (! this.starting) {
						this.close();
						for (Socket client : this.listClient) {
							this.disconnect(client);
						}
						System.out.println("CLOSE");
					}
				} 
				catch (IOException e) {
					System.out.println(e.getMessage());
				}
				finally {
					this.checkClients();
					this.actionLoop();
					System.out.println("NB CLIENT : " + this.listClient.size());
				}
			}	
		} else {
			System.out.println("ALREADY START"); 
		}
	}

	@Override
	public void close() {
		this.starting = false;
	}

	/**
	 * disconnect a client
	 * @param client the socket of client
	 */
	public void disconnect(Socket client) {
		this.listClient.remove(client);
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * the action of loop
	 */
	public abstract void actionLoop();

	/**
	 * process of client
	 * @param client the client
	 */
	public void processClient(Socket client) {
		System.out.println("ip : "  + client.getInetAddress());
		while (this.listClient.contains(client)) {					
			try {
				String message = UtilNetwork.readMessage(client);
				this.processMessage(client, message);
			} catch (IOException e) {
				this.disconnect(client);
			}
		}
	}

	/**
	 * check the good connexion of clients. if connexion not good, 
	 * we remove the client
	 */
	public void checkClients() {
		for (Socket client : this.listClient) {
			try {
				UtilNetwork.sendMessage(client, "PING");
			} catch (IOException e) {
				this.disconnect(client);
			}
		}
	}

}
