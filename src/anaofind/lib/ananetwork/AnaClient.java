package anaofind.lib.ananetwork;

import java.io.IOException;
import java.net.*;

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
	 * the port of server
	 */
	private int portServer;

	/**
	 * the socket
	 */
	private Socket socket;

	/**
	 * boolean indicate if client is start or not
	 */
	private boolean starting = false;

	/**
	 * boolean indicate if client is already work in thread
	 */
	private boolean workedThread = false;

	/**
	 * class to get message in threading
	 * @author anaofind
	 */
	private class GetMessage implements Runnable {
		@Override
		public void run() {
			workedThread = true;
			while (starting) {
				String message = UtilNetwork.readMessage(socket);
				if (message != null) {
					if (! message.equals("PING")) {
						new Thread(() -> processMessage(socket, message)).start();
					}	
				} else {
					if (starting) {
						close();
						connexionBroken();
					}
				}
			}
			workedThread = false;
		}
	}

	/**
	 * construct by default
	 */
	public AnaClient() {
		this("127.0.0.1", 8888);
	}

	/**
	 * choice address of server and port of server
	 * @param addressServer the address of server
	 * @param port the port of server
	 */
	public void bind(String addressServer, int portServer) {
		this.addressServer = addressServer;
		this.portServer = portServer;
	}

	/**
	 * construct
	 * @param addressServer the address of server
	 * @param portServer the port of server
	 */
	public AnaClient(String addressServer, int portServer) {
		this.addressServer = addressServer;
		this.portServer = portServer;
	}

	@Override
	public void start() {
		if (!this.starting) {
			try {
				this.socket = new Socket(addressServer, portServer);
				this.starting = true;
				if (! this.workedThread) {
					new Thread(new GetMessage()).start();	
				}			
			} catch (UnknownHostException e) {
				this.close();
				this.hostNotFound();
			} catch (IOException e) {
				this.close();
				this.cannotConnect();
			}
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
			if (this.socket != null) {
				this.socket.close();	
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
			UtilNetwork.sendMessage(this.socket, message);
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
		return addressServer;
	}

	/**
	 * get port of server
	 * @return the port of server
	 */
	public int portServer() {
		return portServer;
	}


}
