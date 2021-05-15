package anaofind.lib.async;

/**
 * function 
 * @author anaofind
 *
 */
public interface Command<T> {

	/**
	 * call function
	 */
	public T call();
}
