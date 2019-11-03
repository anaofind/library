package anaofind.lib.ananetwork.exception;

/**
 * data not found exception
 * @author anaofind
 */
public class DataNotFoundException extends Exception{

	/**
	 * the serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construct
	 * @param dataName
	 */
	public DataNotFoundException(String dataName) {
		super("the data " + dataName + " not found");
	}
	
}
