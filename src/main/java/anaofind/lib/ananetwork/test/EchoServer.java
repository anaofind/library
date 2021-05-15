package anaofind.lib.ananetwork.test;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.listener.NetworkListener;
import anaofind.lib.ananetwork.protocol.NoProtocol;
import anaofind.lib.ananetwork.protocol.Protocol;
import anaofind.lib.ananetwork.server.SimpleAnaServer;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

public class EchoServer extends SimpleAnaServer{

	public EchoServer() {
		super();
		this.addListener(new NetworkListener() {

			@Override
			public void starting() {
				System.out.println("SERVER START");
			}

			@Override
			public void closing() {
				System.out.println("SERVER CLOSE");
			}

			@Override
			public void looping() {
			}

			@Override
			public void connectionBroken() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void hostNotFound() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void cannotConnect() {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public void processMessage(ClientAnaSocket socket, String message) {
		System.out.println("Message : " + message);
		try {
			this.sendMessage(socket, message);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int timeout() {
		return 1000;
	}

	@Override
	public int portServer() {
		return 8888;
	}

	@Override
	public Protocol protocol() {
		return new NoProtocol();
	}
	
	@Override
	public int maxConnexions() {
		return 10;
	}
	
	public static void main(String[] args) {
		EchoServer server = new EchoServer();
		server.start();
	}

	@Override
	public int timeMaxWaitMessage() {
		// TODO Auto-generated method stub
		return 5000;
	}
}
