package anaofind.lib.analistener;

/**
 * interface ecouteur
 * @author anaofind
 *
 */
public interface Listener {

	/**
	 * methode permettant de realiser les actions sur un �coutable
	 * @param listenable l'�coutable
	 * @param code le code de l'action
	 */
	public void listen(Listenable listenable, int code);
	
}
