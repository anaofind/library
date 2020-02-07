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

	@Override
	public String toString() {
		String array = "[";
		int size = this.values.size();
		for (int i = 0 ; i<size; i++) {
			array += this.values.get(i).toString();
			if (i<size-1) {
				array += ",";
			}
		}
		array += "]";
		return array;
	}
}
