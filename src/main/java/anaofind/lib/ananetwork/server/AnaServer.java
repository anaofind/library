package anaofind.lib.ananetwork.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import anaofind.lib.ananetwork.NetworkElement;
import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.exception.ConnectionInfo;
import anaofind.lib.ananetwork.listener.NetworkListener;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;
import anaofind.lib.ananetwork.socket.ServerAnaSocket;
import anaofind.lib.ananetwork.util.UtilNetwork;
import anaofind.lib.async.Async;

/**
 * ana server
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
		private ClientAnaSocket client;

		/**
		 * construct
		 * @param client the socket of client
		 */
		public ProcessingClient(ClientAnaSocket client) {
			Objects.requireNonNull(client);
			this.client = client;
		}

		@Override
		public void run() {
			processClient(this.client);
			try {
				this.client.disconnect();
			} catch (ConnectionException e) {}
		}
	}

	/**
	 * process client
	 * @param client the client
	 */
	public abstract void processClient(ClientAnaSocket client);

	/**
	 * get max connexion
	 * @return the max connnexion
	 */
	public abstract int maxConnexions();

	/**
	 * the list of client
	 */
	private List<ClientAnaSocket> listClient  = new ArrayList<ClientAnaSocket>(); 

	/**
	 * the socket server
	 */
	private ServerAnaSocket socket;


	/**
	 * boolean indicate if server is runned
	 */
	private boolean runned = false;

	/**
	 * the listeners
	 */
	private List<NetworkListener> listeners = new ArrayList<NetworkListener>();


	public AnaServer() {
		try {
			if (this.protocol() == null) {
				throw new Exception(String.format("the protocol must not be null"));
			}
			if (this.portServer() < 1 ) {
				throw new Exception(String.format("the port must superior to 1 : %d", this.portServer()));
			}
			if (this.timeout() < 1) {
				throw new Exception(String.format("the time loop must superior to 1 : %d", this.timeout()));
			}
			if (this.maxConnexions() < 1) {
				throw new Exception(String.format("the nb of max connexions must superior to 1 : %d", this.maxConnexions()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ConnectionInfo run() {
		// start the server if is not already started
		if (! this.isRunned()) {
			try {
				// initialisation of server socket
				this.socket = new ServerAnaSocket(this.portServer());
				this.socket.setTimeOutConnexion(this.timeout());
				this.socket.setTimeOutMessage(10);
				this.socket.setTimeOutAlive(this.timeMaxWaitMessage());

				for (NetworkListener listener : this.listeners) {
					listener.starting();
				}

				// limit of connexions sockets
				ExecutorService service = Executors.newFixedThreadPool(this.maxConnexions());	
				this.socket.connect();
				
				// starting is true only if socket create success
				this.runned = true;
				
				// main loop
				while (this.isRunned()) {
					try {
						// find client
						ClientAnaSocket socketClient = this.socket.findClient();
						
						if (socketClient != null) {
							this.listClient.add(socketClient);

							service.submit(new ProcessingClient(socketClient));
						}

						this.checkClients();

						for (NetworkListener listener : this.listeners) {
							listener.looping();
						}
					} catch (ConnectionException e) {
						this.close();
					}
				}
				this.listClient.forEach(client -> {
					try {
						client.disconnect();
					} catch (ConnectionException e1) {}
				});
				this.listClient.clear();
				
				// close service of management threads
				service.shutdown();

				// close the server socket
				this.socket.disconnect();
			} 
			catch (ConnectionException e) {}
			this.close();
		}
		return ConnectionInfo.NO_PROBLEM;
	}

	/**
	 * send message
	 * @param socket the client socket
	 * @param message the message
	 * @throws ConnectionException 
	 */
	public void sendMessage(ClientAnaSocket socket, String message) throws ConnectionException {
		if (this.isRunned()) {
			UtilNetwork.sendMessage(socket, this.protocol(), message);
		}
	}
	
	/**
	 * read message
	 * @param socket the client socket
	 * @return the message
	 * @throws ConnectionException 
	 */
	public List<String> readMessage(ClientAnaSocket socket) throws ConnectionException {
		if (this.isRunned()) {
			List<String> message = UtilNetwork.readMessage(socket, this.protocol());
			return message;
		}
		return null;
	}
	
	/**
	 * check client
	 */
	public void checkClients() {
		for (int i = this.listClient.size() -1; i >= 0; i--) {
			if (! this.listClient.get(i).isAlive()) {
				this.listClient.remove(i);
			}
		}
	}

	/**
	 * get nb of clients
	 * @return the nb of clients
	 */
	public int nbClients() {
		return this.clients().size();
	}

	/**
	 * get clients
	 * @return the list of client (unmodifiable)
	 */
	public List<ClientAnaSocket> clients() {
		return Collections.unmodifiableList(this.listClient);
	}

	@Override
	public void addListener(NetworkListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * start server in thread
	 */
	@Override
	public void start() {
		Async.call(() -> this.run()).then((info) -> {
			for (NetworkListener listener : this.listeners) {
				listener.closing();
			}
		});
	}

	@Override
	public void close() {
		this.runned = false;
	}

	@Override
	public boolean isRunned() {
		return this.runned;
	}

}
