package anaofind.lib.anadatair.json;

import java.util.*;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairSettable;

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
	public String toString() {
		String object = "{";
		int size = this.values.size();
		int i = 0;
		for (String key : this.values.keySet()) {
			object += String.format("\"%s\":%s", key, this.values.get(key).toString());
			if (i<size-1) {
				object += ",";
			}
			i++;
		}
		object += "}";
		return object;
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		for (String attribute : this.values.keySet()) {
			air.addData(attribute, this.values.get(attribute).toAnadatair());
		}
		return air.toGettable();
	}
	
	
}
