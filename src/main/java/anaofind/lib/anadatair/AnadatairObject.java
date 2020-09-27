package anaofind.lib.anadatair;

import java.util.*;

import anaofind.lib.anadatair.json.JsonObject;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;
import anaofind.lib.anadatair.util.UtilAnadatair;

/**
 * anadatair object type
 * @author leo_r
 *
 */
public class AnadatairObject implements Anadatair{

	/**
	 * the values
	 */
	private Map<String, Anadatair> values = new HashMap<String, Anadatair>();
				
	/**
	 * get attributes
	 * @return the list of attributes
	 */
	public Set<String> attributes() {
		return Collections.unmodifiableSet(this.values.keySet());
	}
	
	/**
	 * add data
	 * @param attribute the attribute
	 * @param data the data
	 */
	public void add(String attribute, Object object) {
		Anadatair data = UtilAnadatair.encode(object);
		if (data != null) {
			this.values.put(attribute, data);
		}
	}
	
	@Override
	public Anadatair getData(String attribute) {
		if (this.values.containsKey(attribute)) {
			return this.values.get(attribute);
		}
		return null;
	}

	@Override
	public Anadatair getData(int index) {
		return null;
	}

	@Override
	public int size() {
		return this.values.size();
	}
	
	@Override
	public Object getValue() {
		Map<String, Object> value = new HashMap<String, Object>();
		for (String attribute : this.values.keySet()) {
			value.put(attribute, this.values.get(attribute).getValue());
		}
		return value;
	}

	@Override
	public String getType() {
		return TypeResolver.OBJECT;
	}

	@Override
	public JsonValue toJson() {
		JsonObject jsonObject = new JsonObject();
		for (String attribute : this.values.keySet()) {
			jsonObject.addAttribute(attribute, this.getData(attribute).toJson());
		}
		return jsonObject;
	}

	@Override
	public boolean equals(Anadatair other) {
		if (other.size() == this.size() && other.getType().equals(TypeResolver.OBJECT)) {
			for (String attribute : this.values.keySet()) {
				if (!other.contains(attribute) || !other.getData(attribute).equals(this.values.get(attribute))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
 
}
