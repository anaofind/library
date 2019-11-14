package anaofind.lib.anadatair.json;


/**
 * json array
 * @author anaofind
 *
 */
public class JsonArray implements JsonConverter{

	/**
	 * the json values
	 */
	private JsonConverter[] elements;
	
	/**
	 * construct
	 * @param jsonValues the array of json values
	 */
	public JsonArray(JsonConverter...jsonValues) {
		this.elements = jsonValues;
	}
	
	@Override
	public String toTextJson() {
		if (elements.length == 0) {
			return "[]";
		}
		String stringElements = "";
		for (JsonConverter value: elements) {
			stringElements += value.toTextJson() + ",";
		}
		stringElements = stringElements.substring(0, stringElements.length()-1);
		return "[" + stringElements + "]";
	}

	@Override
	public String toTextIR() {
		String ir = "(array:";
		for (JsonConverter value : this.elements) {
			ir += value.toTextIR();
		}
		ir += ")";
		return ir;
	}
	
}
