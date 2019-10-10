package anaofind.lib.ananetwork;

import anaofind.lib.analistener.Listenable;
import anaofind.lib.ananetwork.message.ListMessages;

/**
 * classe perettant de représenter un element du reseau
 * @author anaofind
 *
 */
public abstract class NetworkElement extends Listenable{	

	/**
	 * code info
	 */
	public static final int CODE_INFORMATION = 0;

	/**
	 * code alerte
	 */
	public static final int CODE_WARNING = 1;

	/**
	 * code erreur
	 */
	public static final int CODE_ERROR = 2;

	/**
	 * code question
	 */
	public static final int CODE_QUESTION = 3;

	/**
	 * code de reponse de question
	 */
	public static final int CODE_ANSWER_QUESTION = 4;

	/**
	 * memoire tampon de messages
	 */
	private ListMessages[] bufferMessage = new ListMessages[5];


	/**
	 * constructeur
	 */
	public NetworkElement() {
		for (int i = 0; i<bufferMessage.length; i++) {
			bufferMessage[i] = new ListMessages();
		}
		this.addListener(Network.getInstance());
	}

	/**
	 * methode permettant d'ajouter le serveur au reseau
	 */
	public void add() {
		try {
			this.addAction(0);
			this.updateListenable();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * methode permettant de preparer le serveur
	 */
	public abstract void starting();

	/**
	 * methode permettant de finir le serveur
	 */
	public abstract void finishing();	

	/**
	 * methode permettant d'ajouter un message au tampon
	 * @param code le code du message
	 * @param message le message
	 */
	public void addMessage(int code, String message) {
		if (code >= 0 && code < bufferMessage.length) {
			this.bufferMessage[code].add(message);
		}
	}

	/**
	 * methode permettant de recuperer le premier message
	 * @param code le code du message
	 * @return le premier message
	 */
	public String getFirstMessage(int code) {
		if (code >= 0 && code < bufferMessage.length) {
			String message =  this.bufferMessage[code].get(0);
			this.bufferMessage[code].remove(0);
			return message;
		}
		return null;
	}

}
