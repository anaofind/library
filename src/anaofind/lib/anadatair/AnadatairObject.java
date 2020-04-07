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
	public String getString(String attribute) {
		if (this.contains(attribute, TypeResolver.STRING)) {
			return this.getData(attribute).getString();
		}
		return null;
	}

	@Override
	public String getString(int index) {
		return null;
	}

	@Override
	public Integer getInteger(String attribute) {
		if (this.contains(attribute, TypeResolver.INT)) {
			return this.getData(attribute).getInteger();
		}
		return null;
	}

	@Override
	public Integer getInteger(int index) {
		return null;
	}
	
	@Override
	public Long getLong(String attribute) {
		if (this.contains(attribute, TypeResolver.LONG)) {
			return this.getData(attribute).getLong();
		}
		return null;
	}

	@Override
	public Long getLong(int index) {
		return null;
	}

	@Override
	public Double getDouble(String attribute) {
		if (this.contains(attribute, TypeResolver.DOUBLE) || this.contains(attribute, TypeResolver.INT)) {
			return this.getData(attribute).getDouble();
		}
		return null;
	}

	@Override
	public Double getDouble(int index) {
		return null;
	}

	@Override
	public Boolean getBoolean(String attribute) {
		if (this.contains(attribute, TypeResolver.BOOLEAN)) {
			return this.getData(attribute).getBoolean();
		}
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		return null;
	}

	@Override
	public int size() {
		return this.values.size();
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
