package anaofind.lib.ananetwork;

import java.io.IOException;
import java.net.*;

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
	 * class to get message in threading
	 * @author anaofind
	 */
	private class GetMessage implements Runnable {
		@Override
		public void run() {
			while (starting) {
				try {
					String message = UtilNetwork.readMessage(socket);
					if (! message.equals("PING")) {
						processMessage(socket, message);	
					}
				} catch (IOException e) {
					close();
					connexionBroken();
				}	
			}
		}
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
				new Thread(new GetMessage()).start();			
			} catch (UnknownHostException e) {
				this.hostNotFound();
			} catch (IOException e) {
				this.cannotConnect();
			}
		}
	}

	@Override
	public void close() {
		try {
			this.starting = false;
			if (this.socket != null) {
				this.socket.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
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
}
