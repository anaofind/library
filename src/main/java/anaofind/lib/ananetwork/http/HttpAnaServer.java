package anaofind.lib.ananetwork.http;

import anaofind.lib.ananetwork.exception.ConnectionException;
import anaofind.lib.ananetwork.protocol.Protocol;
import anaofind.lib.ananetwork.server.AnaServer;
import anaofind.lib.ananetwork.socket.ClientAnaSocket;

/**
 * server http
 * @author anaofind
 */
public abstract class HttpAnaServer extends AnaServer {

	@Override
	public void processMessage(ClientAnaSocket socket, String message) {
		String request = new String(message);
		System.out.println(request);
		RequestHttp requestHttp = this.convertToRequestHttp(request);
		try {
			if (requestHttp != null) {
				this.processMessageHttp(socket, requestHttp);
			} else {
				this.sendMessage(socket, "<h1>Erreur Http</h1>");
			}
		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * process message http
	 * @param client the client
	 * @param request
	 * @throws ConnectionException
	 */
	public abstract void processMessageHttp(ClientAnaSocket client, RequestHttp request) throws ConnectionException;

	/**
	 * convert string to request http
	 * @param request the request string
	 * @return the request http
	 */
	private RequestHttp convertToRequestHttp(String request) {
		return null;
	}
	
	@Override
	public Protocol protocol() {
		return new HttpServerProtocol();
	}

	@Override
	public void processClient(ClientAnaSocket client) {
		try {
			this.sendMessage(client, "Salut");
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}

