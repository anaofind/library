package anaofind.lib.ananetwork.test;

import java.util.NoSuchElementException;
import java.util.Scanner;

import anaofind.lib.ananetwork.client.AnaClient;
import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.listener.NetworkListener;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

/**
 * test client date
 * @author leo_r
 *
 */
public class EchoClient extends AnaClient{

	public static Scanner SC = new Scanner(System.in);
	
	public EchoClient() {
		super("127.0.0.1");
		this.addListener(new NetworkListener() {

			@Override
			public void starting() {
				System.out.print("premier message -> ");
				String line = null;
				while (line == null) {
					try { 
						line = SC.nextLine();
					} catch (NoSuchElementException e) {}
				}
				try {
					sendMessage(line);
				} catch (ConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void closing() {
				System.out.println("CLIENT CLOSE");
			}

			@Override
			public void looping() {
			}

			@Override
			public void connectionBroken() {
				System.out.println("CONNEXION BROKEN");
			}

			@Override
			public void hostNotFound() {
				System.out.println("HOST NOT FOUND");
			}

			@Override
			public void cannotConnect() {
				System.out.println("CANNOT CONNECT");
			}
		});
	}

	@Override
	public void processMessage(ClientAnaSocket socket, String message) {
		System.out.println("Reponse : " + message);
		System.out.print("message suivant -> ");
		String line = null;
		while (line == null) {
			try { 
				line = SC.nextLine();
			} catch (NoSuchElementException e) {}
			// System.out.println("LOOP");
		}
		try {
			// System.out.println("MESSAGE TO SEND : " + line);
			this.sendMessage(line);
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int timeout() {
		return 1000;
	}

	@Override
	public int timeMaxWaitMessage() {
		return 50000;
	}

	@Override
	public int portServer() {
		return 8888;
	}
	
	
	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		client.start();
	}
}

