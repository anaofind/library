package anaofind.lib.anajson;

/**
 * json string
 * @author anaofind
 */
public class JsonString implements JsonValue{

	/**
	 * the value
	 */
	private String value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public JsonString(String value) {
		this.value = value;
	}
	
	@Override
	public String getString() {
		return "\"" + this.value + "\"";
	}

}
