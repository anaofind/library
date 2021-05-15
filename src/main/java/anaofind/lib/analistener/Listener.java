package anaofind.lib.analistener;

/**
 * interface listener
 * @author anaofind
 *
 */
public interface Listener {

	/**
	 * listen
	 * @param listenable the listenable
	 * @param code the code of action
	 */
	public void listen(Listenable listenable, int code);
	
}
