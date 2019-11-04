package anaofind.lib.anajson;

import java.util.*;

/**
 * json object
 * @author anaofind
 *
 */
public class JsonObject implements JsonValue{

	/**
	 * map of elements
	 */
	private Map<String, JsonValue> elements = new HashMap<String, JsonValue>();
	
	@Override
	public String getString() {
		if (elements.size() == 0) {
			return "{}";
		}
		String stringElements = "";
		for (String key: elements.keySet()) {
			stringElements += "\"" + key + "\"" + ":" + elements.get(key).getString() + ",";
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
		elements.put(key, value);
	}
	
	/**
	 * remove value
	 * @param key the key
	 */
	public void remove(String key) {
		elements.remove(key);
	}
}
