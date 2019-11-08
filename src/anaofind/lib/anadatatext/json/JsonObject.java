package anaofind.lib.anadatatext.json;

import java.util.*;

/**
 * json object
 * @author anaofind
 *
 */
public class JsonObject implements JsonConverter{

	/**
	 * map of values
	 */
	private Map<String, JsonConverter> values = new HashMap<String, JsonConverter>();
	
	@Override
	public String toTextJson() {
		if (values.size() == 0) {
			return "{}";
		}
		String stringElements = "";
		for (String key: values.keySet()) {
			stringElements += "\"" + key + "\"" + ":" + values.get(key).toTextJson() + ",";
		}
		stringElements = stringElements.substring(0, stringElements.length()-1);
		return "{" + stringElements + "}";
	}

	/**
	 * add value
	 * @param key the key
	 * @param value the value
	 */
	public void add(String key, JsonConverter value) {
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
	public String toTextIR() {
		String ir = "(object:";
		for (String attributeName : this.values.keySet()) {
			ir += "<" + attributeName + ">" + this.values.get(attributeName).toTextIR();
		}
		ir += ")";
		return ir;
	}
}
