package anaofind.lib.anafx.message;

/**
 * classe permettant de representer un message
 * @author anaofind
 *
 */
public abstract class Message{
		
	/**
	 * la nature du message
	 */
	protected String type;
	
	/**
	 * le titre
	 */
	protected String title;
	
	/**
	 * l'info
	 */
	protected String information;
	
	/**
	 * constrcteur
	 * @param type la nature de l'exception
	 */
	public Message(String type) {
		this.type = type;
	}	
	
	/**
	 * méthode permettant d'afficher l'exception
	 * @return le code de retour de l'affichage (utile pour les questions)
	 */
	public abstract int show();
			
	/**
	 * methode toString
	 */
	@Override
	public String toString() {
		return this.type + " -> " + "(" + this.title + ") " + this.information;
	}
}
