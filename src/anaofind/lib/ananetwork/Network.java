package anaofind.lib.ananetwork;

import anaofind.lib.analistener.Listenable;
import anaofind.lib.analistener.Listener;
import anaofind.lib.ananetwork.client.Client;
import anaofind.lib.ananetwork.server.Server;

/**
 * classe permettent de gerer un reseau
 * @author anaofind
 *
 */
public class Network implements Listener {

	/**
	 * singleton reseau
	 */
	private static Network network;
	
	/**
	 * le serveur
	 */
	private Server server;
	
	/**
	 * le client
	 */
	private Client client;
	
	/**
	 * constructeur prive
	 */
	private Network() {
	}
	
	
	/**
	 * getter du singleton 
	 * @return le singleton
	 */
	public static Network getInstance() {
		if (network == null) {
			network = new Network();
		}
		return network;
	}

	/**
	 * getter du serveur
	 * @return le serveur
	 */
	public static Server getServer() {
		if (network != null) {
			return network.server;	
		}
		return null;
	}
	
	/**
	 * getter du client
	 * @return le client
	 */
	public static Client getClient() {
		if (network != null) {
			return network.client;	
		}
		return null;
	}


	@Override
	public void listen(Listenable listenable, int code) {
		if (listenable instanceof Server && network.server == null) {
			network.server = (Server)listenable;
		} else {
			if (listenable instanceof Client && network.client == null) {
				network.client = (Client)listenable;
			}
		}
		listenable.removeListener(this);
	}	
	
}
