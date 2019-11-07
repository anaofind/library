package anaofind.lib.anadatatext.json;

import java.util.*;

/**
 * json object
 * @author anaofind
 *
 */
public class JsonObject implements JsonValue{

	/**
	 * map of values
	 */
	private Map<String, JsonValue> values = new HashMap<String, JsonValue>();
	
	@Override
	public String toJson() {
		if (values.size() == 0) {
			return "{}";
		}
		String stringElements = "";
		for (String key: values.keySet()) {
			stringElements += "\"" + key + "\"" + ":" + values.get(key).toJson() + ",";
		}
		stringElements = stringElements.substring(0, stringElements.length()-1);
		return "{" + stringElements + "}";
	}

	/**
	 * add value
	 * @param key the key
	 * @param value the value
	 */
	public void add(String key, JsonValue value) {
		values.put(key, value);
	}
	
	/**
	 * remove value
	 * @param key the key
	 */
	public void remove(String key) {
		values.remove(key);
	}

	@Override
	public String toIR() {
		String ir = "(object:";
		for (String attributeName : this.values.keySet()) {
			ir += "<" + attributeName + ">" + this.values.get(attributeName).toIR();
		}
		ir += ")";
		return ir;
	}
}
