package anaofind.lib.anajson;

import java.util.Objects;

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
		Objects.requireNonNull(value);
		this.value = value;
	}
	
	@Override
	public String getString() {
		return "\"" + this.value + "\"";
	}

}
