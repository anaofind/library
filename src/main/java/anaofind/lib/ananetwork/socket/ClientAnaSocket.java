package anaofind.lib.ananetwork.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.exception.ConnectionInfo;

/**
 * ana socket client
 * @author anaofind
 *
 */
public class ClientAnaSocket extends AnaSocketImpl{

	/**
	 * the socket
	 */
	private Socket socket;

	/**
	 * the address of server
	 */
	private String address;

	/**
	 * epoch last message
	 */
	private long epochLastMessage = System.currentTimeMillis();

	/**
	 * construct
	 * @param addressServer the address of server
	 * @param port the port
	 */
	public ClientAnaSocket(String addressServer, int port) {
		super(port);
		this.address = addressServer;
	}

	/**
	 * private construct
	 * @param socket the socket
	 */
	private ClientAnaSocket(Socket socket) {
		super(socket.getPort());
		this.address = socket.getInetAddress().getHostAddress();	
		this.socket = socket;
	}

	/**
	 * create ana socket client with socket
	 * @param socket the socket
	 * @return the ana socket client
	 */
	public static ClientAnaSocket create(Socket socket) {
		return new ClientAnaSocket(socket);
	}

	@Override
	public void disconnect() throws ConnectionException {
		try {
			if (this.socket == null) {
				throw new ConnectionException(ConnectionInfo.NO_PROBLEM);
			}
			this.socket.close();
			this.socket = null;
		} catch (IOException e) {
			throw new ConnectionException(ConnectionInfo.NO_PROBLEM);
		}
	}

	@Override
	public void connect() throws ConnectionException {
		try {
			this.socket = new Socket();
			this.socket.setKeepAlive(true);
			this.socket.connect(new InetSocketAddress(this.address(), this.port()), this.timeOutConnexion());
		} catch (UnknownHostException e) {
			throw new ConnectionException(ConnectionInfo.HOST_NOT_FOUND);
		} catch (SocketException e) {
			throw new ConnectionException(ConnectionInfo.CANNOT_CONNECT);
		} catch (IOException e) {
			throw new ConnectionException(ConnectionInfo.CANNOT_CONNECT);
		}
	}

	@Override
	public boolean isConnected() {
		return this.socket != null && !this.socket.isClosed() && this.socket.isConnected();
	}

	/**
	 * get address of server
	 * @return the address of server
	 */
	public String address() {
		return this.address;
	}

	/**
	 * get reader of socket
	 * @param socket the socket
	 * @return the reader of socket
	 * @throws IOException
	 */
	private BufferedReader reader() throws ConnectionException {
		try {
			InputStream in = this.socket.getInputStream();
			return new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			if (this.isConnected()) {
				throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);	
			}
			throw new ConnectionException(ConnectionInfo.NO_PROBLEM);
		}
	}

	/**
	 * get printer of socket
	 * @return the printer of socket
	 * @throws IOException
	 */
	private PrintWriter printer() throws ConnectionException {
		try {
			OutputStream out = this.socket.getOutputStream();
			return new PrintWriter(new OutputStreamWriter(out));
		} catch (IOException e) {
			throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
		}
	}

	/**
	 * read message
	 * @return the message
	 */
	public List<String> readMessage() throws ConnectionException {
		List<String> messages = new ArrayList<String>();
		try {
			BufferedReader reader = this.reader();
			long epochRead = System.currentTimeMillis();
			while (! reader.ready() && (System.currentTimeMillis() - epochRead < this.timeOutMessage())) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}
			}
			while (reader.ready()) {
				String message = reader.readLine();
				if (message != null) {
					messages.add(message);
					this.epochLastMessage = System.currentTimeMillis();	
				} else {
					throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
				}
			}
		}
		catch (java.io.InterruptedIOException e){}  
		catch (SocketException e) {
			throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
		} catch (IOException e) {
			throw new ConnectionException(ConnectionInfo.CONNECTION_BROKEN);
		}
		return Collections.unmodifiableList(messages);
	}

	/**
	 * send message
	 * @param the message
	 * @return the message
	 */
	public void sendMessage(String message) throws ConnectionException{
		PrintWriter printer = this.printer();
		printer.println(message);
		printer.flush();
	}

	@Override
	public boolean isAlive() {
		return this.isConnected() && (System.currentTimeMillis() - this.epochLastMessage < this.timeOutAlive());
	}

	@Override
	public String toString() {
		return "ClientAnaSocket [socket=" + socket + "]";
	}


}
