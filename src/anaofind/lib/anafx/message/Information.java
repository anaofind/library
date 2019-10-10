package anaofind.lib.anafx.message;

import anaofind.lib.anafx.util.UtilFX;

/**
 * classe permettant de representer une information
 * @author anaofind
 *
 */
public class Information extends Message{
	
	/**
	 * constructeur
	 */
	public Information() {
		super("Information");
	}

	/**
	 * methode permettant de recuperer l'information
	 * @param plainMessage le message brut
	 * @return l'information
	 */
	public static Information get(String plainMessage) {
		InfoMessage im = InfoMessage.parserMessage(plainMessage);
		if (im != null) {
			Information information = new Information();
			information.title = im.getType();
			information.information = im.getMessage();
			return information;	
		}
		return null;
	}
	
	@Override
	public int show() {
		UtilFX.showInformation(title, information);
		return 0;
	}
	
}
