package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.visitor.VisitorJSON;

import java.util.*;

/**
 * json array
 * @author anaofind
 *
 */
public class JsonArray implements JsonValue{

	/**
	 * the json values
	 */
	private List<JsonValue> values = new ArrayList<JsonValue>();
	
	/**
	 * construct
	 * @param jsonValues the array of json values
	 */
	public JsonArray(JsonValue...jsonValues) {
		for (JsonValue value : jsonValues) {
			this.values.add(value);	
		}
	}
	
	/**
	 * getter values clone
	 * @return the values clone
	 */
	public List<JsonValue> getValues() {
		return Collections.unmodifiableList(this.values);
	}

	@Override
	public void accept(VisitorJSON visitor) {
		visitor.visitArrayJSON(this);
	}
	
	
}
