package anaofind.lib.anafx.message;

import anaofind.lib.anafx.util.UtilFX;

/**
 * classe permettant de reprenter un erreur
 * @author anaofind
 *
 */
public class Error extends Message{
	
	/**
	 * constructeur
	 */
	public Error() {
		super("Error");
		// TODO Auto-generated constructor stub
	}

	/**
	 * methode permettant de recuperer l'erreur
	 * @param plaintMessage le message brut
	 * @return l'erreur
	 */
	public static Error get(String plaintMessage) {
		InfoMessage im = InfoMessage.parseMessage(plaintMessage);
		if (im != null) {
			Error erreur = new Error();
			erreur.title = im.getType();
			erreur.information = im.getMessage();
			return erreur;	
		}
		return null;
	}
	
	@Override
	public int show() {
		UtilFX.showError(title, information);
		return 0;
	}

	
	
}
