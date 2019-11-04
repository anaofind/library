package anaofind.lib.anajson;


/**
 * json array
 * @author anaofind
 *
 */
public class JsonArray implements JsonValue{

	/**
	 * the json values
	 */
	private JsonValue[] elements;
	
	/**
	 * construct
	 * @param jsonValues the array of json values
	 */
	public JsonArray(JsonValue...jsonValues) {
		this.elements = jsonValues;
	}
	
	@Override
	public String getString() {
		if (elements.length == 0) {
			return "[]";
		}
		String stringElements = "";
		for (JsonValue value: elements) {
			stringElements += value.getString() + ",";
		}
		stringElements = stringElements.substring(0, stringElements.length()-1);
		return "[" + stringElements + "]";
	}
	
}
