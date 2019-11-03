package anaofind.lib.ananetwork.client;

import anaofind.lib.ananetwork.Network;
import anaofind.lib.ananetwork.client.Client;

/**
 * m�thode permettant de recevoir des requetes en thread
 * @author anaofind
 *
 */
public class GetQuery implements Runnable{

	
	/**
	 * m�thode permettant au client de recevoir une requete
	 */
	@Override
	public void run() {
		Client client = Network.getInstance().getClient();
		while (client.isActive()){
			client.processQuery();			
		}
	}

}
