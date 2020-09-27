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
		super("127.0.0.1");
	}

	@Override
	public void processMessage(Socket socket, String message) {
		System.out.println(String.format("DATE = %s", message));
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

	@Override
	public void actionStart() {
	}

	@Override
	public void actionClose() {
	}

	@Override
	public int timeout() {
		return 100;
	}

	@Override
	public int timeWaitPong() {
		return 1000;
	}

	@Override
	public int portServer() {
		return 8888;
	}
}

