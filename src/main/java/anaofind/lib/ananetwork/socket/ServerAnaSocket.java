package anaofind.lib.ananetwork.socket;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.exception.ConnectionInfo;

/**
 * ana socket server
 * @author anaofind
 */
public class ServerAnaSocket extends AnaSocketImpl{

	/**
	 * the socket
	 */
	private ServerSocket socket;

	/**
	 * construct
	 * @param port the port
	 */
	public ServerAnaSocket(int port) {
		super(port);
	}

	@Override
	public void disconnect() throws ConnectionException {
		try {
			this.socket.close();
			this.socket = null;
		} catch (IOException e) {
			throw new ConnectionException(ConnectionInfo.NO_PROBLEM);
		}
	}

	@Override
	public void connect() throws ConnectionException {
		try {
			this.socket = new ServerSocket(this.port());
			this.socket.setSoTimeout(this.timeOutConnexion());
		} catch (SocketException e) {
			throw new ConnectionException(ConnectionInfo.CANNOT_CONNECT);
		} catch (IOException e) {
			throw new ConnectionException(ConnectionInfo.CANNOT_CONNECT);
		}
	}

	@Override
	public boolean isConnected() {
		return this.socket != null && ! this.socket.isClosed();
	}

	/**
	 * find client
	 * @return the next socket client 
	 * @throws ConnectionException 
	 * @throws IOException
	 */
	public ClientAnaSocket findClient() throws ConnectionException {
		if (this.isConnected()) {
			try {
				Socket clientSocket = this.socket.accept();
				
				ClientAnaSocket client = ClientAnaSocket.create(clientSocket);
				client.setTimeOutConnexion(this.timeOutConnexion());
				client.setTimeOutMessage(this.timeOutMessage());
				return client;
			} 
			catch (InterruptedIOException e) {} 
			catch (IOException e) {
				throw new ConnectionException(ConnectionInfo.NO_PROBLEM);
			} 
		}
		return null;
	}

	@Override
	public boolean isAlive() {
		return this.isConnected();
	}
}
