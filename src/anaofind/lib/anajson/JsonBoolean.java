package anaofind.lib.anajson;

/**
 * json boolean
 * @author anaofind
 *
 */
public class JsonBoolean implements JsonValue{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public JsonBoolean (boolean value) {
		this.value = value;
	}
	
	@Override
	public String getString() {
		return "" + value;
	}


	
}
