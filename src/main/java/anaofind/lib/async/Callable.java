package anaofind.lib.async;

/**
 * callable
 * @author anaofind
 *
 * @param <T> the type of parameter
 */
public interface Callable<T> {
	
	/**
	 * run lambda expression
	 * @param element the element
	 */
	public void call(T element);
	
}
