package anaofind.lib.analistener;

/**
 * interface ecouteur
 * @author anaofind
 *
 */
public interface Listener {

	/**
	 * methode permettant de realiser les actions sur un écoutable
	 * @param listenable l'écoutable
	 * @param code le code de l'action
	 */
	public void listen(Listenable listenable, int code);
	
}
