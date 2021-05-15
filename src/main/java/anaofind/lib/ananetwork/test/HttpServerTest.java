package anaofind.lib.ananetwork.test;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.http.HttpAnaServer;
import anaofind.lib.ananetwork.http.RequestHttp;
import anaofind.lib.ananetwork.server.AnaServer;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

public class HttpServerTest extends HttpAnaServer{

	public static void main(String[] args) {
		AnaServer server = new HttpServerTest();
		server.start();
	}

	@Override
	public int timeout() {
		return 5000;
	}

	@Override
	public int portServer() {
		return 8888;
	}

	@Override
	public void processMessageHttp(ClientAnaSocket client, RequestHttp request) throws ConnectionException {
		this.sendMessage(client, "<p1>bienvenu !</p1");
	}

	@Override
	public int maxConnexions() {
		return 10;
	}

	@Override
	public int timeMaxWaitMessage() {
		return 5000;
	}
}
