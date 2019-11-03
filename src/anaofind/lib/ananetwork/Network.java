package anaofind.lib.ananetwork;

import java.util.*;

import anaofind.lib.ananetwork.client.Client;
import anaofind.lib.ananetwork.server.Server;

/**
 * classe permettent de gerer un reseau
 * @author anaofind
 *
 */
public class Network {

	/**
	 * singleton reseau
	 */
	private static Network network;
	
	/**
	 * map of network element
	 */
	private Map<String, NetworkElement> networkElements = new HashMap<String, NetworkElement>();
	
	/**
	 * constructeur prive
	 */
	private Network() {
	}
	
	/**
	 * getter du singleton 
	 * @return le singleton
	 */
	public static synchronized Network getInstance() {
		if (network == null) {
			network = new Network();
		}
		return network;
	}

	/**
	 * getter du serveur
	 * @return le serveur
	 */
	public Server getServer() {
		if (this.networkElements.containsKey("server")) {
			return (Server) this.networkElements.get("server");
		}
		return null;
	}
	
	/**
	 * getter du client
	 * @return le client
	 */
	public Client getClient() {
		if (this.networkElements.containsKey("client")) {
			return (Client) this.networkElements.get("client");
		}
		return null;
	}
	
	/**
	 * add a network element 
	 * @param networkElement the network element
	 */
	public void addNetworkElement(NetworkElement networkElement) {
		Objects.requireNonNull(networkElement);
		this.networkElements.put(networkElement.getRole(), networkElement);
	}
	
}
