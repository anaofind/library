package anaofind.lib.ananetwork.test;

import java.net.Socket;

import anaofind.lib.ananetwork.AnaClient;

/**
 * test client date
 * @author leo_r
 *
 */
public class ClientDate extends AnaClient{

	public ClientDate() {
		super("127.0.0.1", 8888);
	}

	@Override
	public void processMessage(Socket socket, String message) {
		if (! message.equals("PING")) {
			System.out.println(String.format("DATE = %s", message));
		}
	}
	
	@Override
	public void connexionBroken() {
		System.out.println("CONNEXION BROKEN");
		this.start();
	}

	@Override
	public void hostNotFound() {
		System.out.println("HOST NOT FOUND");
	}

	@Override
	public void cannotConnect() {
		System.out.println("TRY TO CONNECT");
		this.start();
	}
}

