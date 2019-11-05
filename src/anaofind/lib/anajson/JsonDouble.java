package anaofind.lib.anajson;

/**
 * json double
 * @author anaofind
 *
 */
public class JsonDouble implements JsonValue{

	/**
	 * the value
	 */
	private double value;
	
	/**
	 * construct 
	 * @param value the value
	 */
	public JsonDouble (double value) {
		this.value = value;
	}
	
	@Override
	public String getString() {
		return "" + this.value;
	}

}
