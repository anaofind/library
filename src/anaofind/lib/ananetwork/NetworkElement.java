package anaofind.lib.ananetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

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
	 * le charset des données
	 */
	public static String charset = "UTF-8";
	
	/**
	 * is active or not
	 */
	private boolean active = false;
	
	/**
	 * méthode permettant de creer un reader
	 * @param socket la socket
	 * @param charset le type de données
	 * @return le reader
	 * @throws IOException l'exception
	 */
	protected static BufferedReader createReader(Socket socket) throws IOException  {
		InputStream in = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(in, charset));
	}

	/**
	 * méthode permettant de creer un printer
	 * @param socket la socket
	 * @param charset le type de donnée
	 * @return le printer créé
	 * @throws IOException l'exception
	 */
	protected static PrintWriter createPrinter(Socket socket) throws IOException{
		OutputStream out = socket.getOutputStream();
		return new PrintWriter(new OutputStreamWriter(out, charset));
	}

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
		Network.getInstance().addNetworkElement(this);
	}

	/**
	 * get role of network element
	 * @return the role of network element
	 */
	public abstract String getRole();
	
	/**
	 * is active or not
	 * @return boolean
	 */
	public boolean isActive() {
		return this.active;
	}
	
	/**
	 * disable
	 */
	protected void disable() {
		this.active = false;
	}
	
	/**
	 * enable
	 */
	protected void enable() {
		this.active = true;
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
	private void addMessage(int code, String message) {
		if (code >= 0 && code < bufferMessage.length) {
			this.bufferMessage[code].add(message);
		}
	}

	/**
	 * methode permettant de recuperer le premier message
	 * @param code le code du message
	 * @return le premier message
	 */
	private String getFirstMessage(int code) {
		if (code >= 0 && code < bufferMessage.length) {
			String message =  this.bufferMessage[code].get(0);
			this.bufferMessage[code].remove(0);
			return message;
		}
		return null;
	}
	
	/**
	 * add message information
	 * @param information the message information
	 */
	public void addMessageInformation(String information) {
		Objects.requireNonNull(information);
		this.addMessage(CODE_INFORMATION, information);
	}
	
	/**
	 * add message warning
	 * @param warning the message warning
	 */
	public void addMessageWarning(String warning) {
		Objects.requireNonNull(warning);
		this.addMessage(CODE_WARNING, warning);
	}
	
	/**
	 * add message error
	 * @param error the message error
	 */
	public void addMessageError(String error) {
		Objects.requireNonNull(error);
		this.addMessage(CODE_ERROR, error);
	}
	
	/**
	 * add message question
	 * @param question the message question
	 */
	public void addMessageQuestion(String question) {
		Objects.requireNonNull(question);
		this.addMessage(CODE_QUESTION, question);
	}
	
	/**
	 * add message answer question
	 * @param question the message answer question
	 */
	public void addMessageAnswerQuestion(String answerQuestion) {
		Objects.requireNonNull(answerQuestion);
		this.addMessage(CODE_ANSWER_QUESTION, answerQuestion);
	}
	
	/**
	 * get first message information and remove of the list
	 * @return the first message information
	 */
	public String firstMessageInformation() {
		return this.getFirstMessage(CODE_INFORMATION);
	}
	
	/**
	 * get first message warning and remove of the list
	 * @return the first message warning
	 */
	public String firstMessageWarning() {
		return this.getFirstMessage(CODE_WARNING);
	}
	
	/**
	 * get first message error and remove of the list
	 * @return the first message error
	 */
	public String firstMessageError() {
		return this.getFirstMessage(CODE_ERROR);
	}
	
	/**
	 * get first message question and remove of the list
	 * @return the first message question
	 */
	public String firstMessageQuestion() {
		return this.getFirstMessage(CODE_QUESTION);
	}
	
	/**
	 * get first message answer question and remove of the list
	 * @return the first message answer question
	 */
	public String firstMessageAnswerQuestion() {
		return this.getFirstMessage(CODE_ANSWER_QUESTION);
	}

}
