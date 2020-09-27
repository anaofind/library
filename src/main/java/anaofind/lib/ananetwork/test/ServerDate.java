package anaofind.lib.ananetwork.test;

import java.net.Socket;
import java.util.Date;

import anaofind.lib.ananetwork.AnaServer;
import anaofind.lib.ananetwork.ConnectionException;
import anaofind.lib.ananetwork.util.UtilNetwork;

public class ServerDate extends AnaServer{

	public ServerDate() {
		super();
	}

	@Override
	public void processMessage(Socket socket, String message) {
		System.out.println(String.format("MESSAGE : %s", message));
	}

	@Override
	public void actionLoop() {
		System.out.println("NB CLIENT : " + this.clients().length);
		for (Socket client : this.clients()) {
			try {
				UtilNetwork.sendMessage(client, new Date().toString());
			} catch (ConnectionException e) {
				System.err.println("SERVER : " + e.getMessage());
			}
		}
	}

	@Override
	public void actionClose() {
	}

	@Override
	public void actionStart() {
	}

	@Override
	public int timeout() {
		return 100;
	}

	@Override
	public int portServer() {
		return 8888;
	}

	@Override
	public int timeWaitPong() {
		return 1000;
	}
}