package anaofind.lib.async;

import java.util.Objects;

/**
 * async class
 * @author anaofind
 */
public class Async<T> {

	
	/**
	 * the function
	 */
	private Command<T> command;
	
	/**
	 * private construct
	 * @param command the command
	 */
	private Async(Command<T> command) {
		Objects.requireNonNull(command);
		this.command = command;
	}
	
	/**
	 * execute function in async
	 * @return the future result of function
	 */
	public Future<T> execute() {
		Future<T> future = new Future<T>();
		new Thread(() -> {
			T object = command.call();
			future.set(object);
		}).start();
		return future;
	}
	
	/**
	 * call instance of method in async
	 * @param command the instance of function
	 * @return the future of instance function result
	 */
	public static <T> Future<T> call(Command<T> command) {
		Async<T> async = new Async<T>(command);
		return async.execute();
	}
	
}
