package anaofind.lib.anafx.message;

import anaofind.lib.anafx.util.UtilFX;

/**
 * classe permettant de representer une alerte
 * @author anaofind
 *
 */
public class Warning extends Message{

	/**
	 * constructeur
	 */
	public Warning() {
		super("Warning");
	}

	/**
	 * methode permettant de recuperer l'alerte
	 * @param plainMessage le message brut
	 * @return l'alerte
	 */
	public static Warning get(String plainMessage) {
		InfoMessage im = InfoMessage.parserMessage(plainMessage);
		if (im != null) {
			Warning alerte = new Warning();
			alerte.title = im.getType();
			alerte.information = im.getMessage();
			return alerte;	
		}
		return null;
	}
	
	@Override
	public int show() {
		UtilFX.showWarning(title, information);
		return 0;
	}

}
