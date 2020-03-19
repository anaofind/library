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
					processMessage(socket, message);
				} catch (IOException e) {
					close();
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("ALREADY START");
		}
	}

	@Override
	public void close() {
		this.starting = false;
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
