package anaofind.lib.anafx.message;

import anaofind.lib.anafx.util.UtilFX;

/**
 * classer permettant de representer une question
 * @author anaofind
 *
 */
public class Question extends Message {
	
	/**
	 * constructeur
	 */
	public Question() {
		super("Question");
		// TODO Auto-generated constructor stub
	}

	/**
	 * methode permettant de recuperer la question
	 * @param plainMessage le message brut
	 * @return la question
	 */
	public static Question get(String plainMessage) {
		InfoMessage im = InfoMessage.parseMessage(plainMessage);
		if (im != null) {
			Question question = new Question();
			question.title = im.getType();
			question.information = im.getMessage();
			return question;
		}
		return null;
	}
	
	@Override
	public int show() {
		 return UtilFX.showQuestion(title, information);
	}	
}
