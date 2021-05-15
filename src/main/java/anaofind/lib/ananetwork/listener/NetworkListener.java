package anaofind.lib.ananetwork.listener;

/**
 * network listener
 * @author anaofind
 *
 */
public interface NetworkListener {

	/**
	 * action where starting
	 */
	public void starting();
	
	/**
	 * action where closing closing
	 */
	public void closing();
	
	/**
	 * action to each loop
	 */
	public void looping();
	
	/**
	 * action where connexion broken
	 */
	public void connectionBroken();

	/**
	 * action where host not found
	 */
	public void hostNotFound();

	/**
	 * action when cannot connect
	 */
	public void cannotConnect();
	
}
