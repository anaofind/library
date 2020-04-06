package anaofind.lib.anadatair;

import java.util.*;

import anaofind.lib.anadatair.json.JsonArray;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;
import anaofind.lib.anadatair.util.UtilAnadatair;

/**
 * the anadatair array type
 * @author anaofind
 */
public class AnadatairArray implements Anadatair{

	/**
	 * the values
	 */
	private List<Anadatair> values = new ArrayList<Anadatair>();
	
	/**
	 * construct 
	 * @param values the values to add
	 */
	public AnadatairArray(Object...values) {
		this.add(values);
	}
	
	/**
	 * 
	 * @param values the array of values
	 */
	public void add(Object...values) {
		for (Object value : values) {
			Anadatair data = UtilAnadatair.encode(value);
			if (data != null) {
				this.values.add(data);
			}
		}
	}
	
	/**
	 * method indicate if array is an array of unique type
	 * @param type the type
	 * @return boolean : true if array is array of type | false else
	 */
	public boolean isArrayOf(String type) {
		for (Anadatair value : this.values) {
			if (! value.getType().equals(type)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Anadatair getData(String attribute) {
		return null;
	}

	@Override
	public Anadatair getData(int index) {
		if (index >= 0 && index < this.values.size()) {
			return this.values.get(index);
		}
		return null;
	}

	@Override
	public String getString(String attribute) {
		return null;
	}

	@Override
	public String getString(int index) {
		if (this.contains(index, TypeResolver.STRING)) {
			return this.values.get(index).getString();
		}
		return null;
	}

	@Override
	public Integer getInteger(String attribute) {
		return null;
	}

	@Override
	public Integer getInteger(int index) {
		if (this.contains(index, TypeResolver.INT)) {
			return this.values.get(index).getInteger();
		}
		return null;
	}
	
	@Override
	public Long getLong(String attribute) {
		return null;
	}

	@Override
	public Long getLong(int index) {
		if (this.contains(index, TypeResolver.LONG)) {
			return this.values.get(index).getLong();
		}
		return null;
	}

	@Override
	public Double getDouble(String attribute) {
		return null;
	}

	@Override
	public Double getDouble(int index) {
		if (this.contains(index, TypeResolver.DOUBLE) || this.contains(index, TypeResolver.INT)) {
			return this.values.get(index).getDouble();
		}
		return null;
	}

	@Override
	public Boolean getBoolean(String attribute) {
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		if (this.contains(index, TypeResolver.BOOLEAN)) {
			return this.values.get(index).getBoolean();
		}
		return null;
	}

	@Override
	public int size() {
		return this.values.size();
	}

	@Override
	public String getType() {
		return TypeResolver.ARRAY;
	}

	@Override
	public JsonValue toJson() {
		JsonValue[] jsonArray = new JsonValue[this.values.size()];
		for (int i = 0; i<this.values.size(); i++) {
			jsonArray[i] = this.values.get(i).toJson();
		}
		return new JsonArray(jsonArray);
	}

	@Override
	public boolean equals(Anadatair other) {
		if (this.size() == other.size() && other.getType().equals(TypeResolver.ARRAY)) {
			for (int i = 0; i<this.values.size(); i++) {
				if (!other.contains(i) || !this.values.get(i).equals(other.getData(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
