package anaofind.lib.ananetwork.message;

import java.util.ArrayList;

/**
 * classe permettant de stocker des messages
 * @author anaofind
 *
 */
public class ListMessages {

	/**
	 * la liste de message
	 */
	private ArrayList<String> messages = new ArrayList<String>();
	
	/**
	 * methode permettant d'ajouter un message
	 * @param message le message
	 */
	public void add(String message) {
		this.messages.add(message);
	}
	
	/**
	 * methode permettant de recuperer un message
	 * @param indice l'indice du message
	 * @return le message
	 */
	public String get(int indice) {
		if (indice >= 0 && indice < messages.size()) {
			return messages.get(indice);
		}
		return null;
	}
	
	/**
	 * methode permettant de supprimer un message
	 * @param indice l'indice du message
	 */
	public void remove(int indice) {
		if (indice >= 0 && indice < messages.size()) {
			messages.remove(indice);
		}
	}
}
