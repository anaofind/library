package anaofind.lib.anadatair.json;

import java.util.*;

import anaofind.lib.anadatair.visitor.VisitorJSON;

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
	
	/**
	 * add value
	 * @param key the key
	 * @param value the value
	 */
	public void addAttribute(String key, JsonValue value) {
		values.put(key, value);
	}
	
	/**
	 * remove value
	 * @param key the key
	 */
	public void remove(String key) {
		values.remove(key);
	}
	
	/**
	 * getter values
	 * @return the values
	 */
	public Map<String, JsonValue> getValues() {
		return Collections.unmodifiableMap(values);
	}

	@Override
	public void accept(VisitorJSON visitor) {
		visitor.visitObjectJSON(this);
	}
}
