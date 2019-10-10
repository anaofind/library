package anaofind.lib.anafx.message;

/**
 * classe permettant de représenter les informations d'un message
 * @author anaofind
 *
 */
public class InfoMessage {
	
	
	/**
	 * le separateur
	 */
	public static final String SEPARATOR= "SEPARATOR_INFO_MESSAGE"; 
	
	/**
	 * l'auteur du message
	 */
	private String author;
		
	/**
	 * le type du message
	 */
	private String type;
	
	/**
	 * le message
	 */
	private String message;
	
	/**
	 * constructeur 
	 * @param author l'auteur
	 * @param type le type
	 * @param message le message
	 */
	public InfoMessage(String author, String type, String message) {
		this.author = author;
		this.type = type;
		this.message = message;
	}
	
	
	/**
	 * methode permettant de parser un message
	 * @param plainMessage le message brut
	 * @return le message parser
	 */
	public static InfoMessage parserMessage(String plainMessage) {
		String[] m = plainMessage.split(SEPARATOR);
		if (m.length == 3) {
			String auteur = m[0];
			String type = m[1];
			String message = m[2];
			
			return new InfoMessage(auteur, type, message);
		}
		return new InfoMessage(null, null, plainMessage);
	}
	
	/**
	 * methode permettant de recuperer le message brut du message
	 * @param author l'auteur du message
	 * @param type le type du message
	 * @param message le message
	 * @return le message brut correspondant au message
	 */
	public static String getPlainMessage(String author, String type, String message) {
		return author + SEPARATOR + type + SEPARATOR + message;
	}

	/**
	 * getter auteur
	 * @return l'auteur
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * getter code
	 * @return le code du message
	 */
	public String getType() {
		return type;
	}

	/**
	 * getter message
	 * @return le message
	 */
	public String getMessage() {
		return message;
	}
	
	
}

