package anaofind.lib.async;

/**
 * future object
 * @author anaofind
 * @param <T>
 */
public class Future<T> {

	/**
	 * the element
	 */
	private T element;
	
	/**
	 * the callable to execute when element will be available
	 */
	private Callable<T> callable;
	
	/**
	 * true if element is available
	 */
	private boolean available = false;
	
	/**
	 * set the element
	 * @param element the element
	 */
	public void set(T element) {
		this.element = element;
		this.available = true;
		if (this.callable != null) {
			this.callable.call(element);
		}
	}
	
	/**
	 * get the element
	 * @return the element
	 */
	public T get() {
		return this.element;
	}
	
	/**
	 * element is available
	 * @return true if element is available | false else
	 */
	public boolean isAvailable() {
		return this.available;
	}
	
	/**
	 * execute callable where value is available
	 * @param callable the callable
	 */
	public void then(Callable<T> callable) {
		this.callable = callable;
		if (this.isAvailable()) {
			this.callable.call(element);
		}
	}
	
	/**
	 * wait to element is available
	 */
	public T await() {
		while (! this.isAvailable()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		return this.element;
	}
	
}
