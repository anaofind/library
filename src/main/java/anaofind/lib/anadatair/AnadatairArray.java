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
	
	/**
	 * method indicate the type of array
	 * @return the type of array
	 */
	public String getTypeOfArray() {
		List<String> allTypeInArray = new ArrayList<String>();
		if (this.values.size() > 0) {
			for (Anadatair value : this.values) {
				String type = value.getType();
				if (type.equals(TypeResolver.ARRAY)) {
					type = ((AnadatairArray) value).getTypeOfArray();
				}
				if (! allTypeInArray.contains(type)) {
					allTypeInArray.add(type);
				}
			}
			if (allTypeInArray.size() == 1) {
				return allTypeInArray.get(0);	
			}
		}
		return TypeResolver.OBJECT;
	}
	
	/**
	 * get dimension of array
	 * @return the dimension of array
	 */
	public int getDimension() {
		int dimension = 1;
		if (this.isArrayOf(TypeResolver.ARRAY) && this.values.size() > 0) {
			List<Integer> dimensionChildrens = new ArrayList<Integer>();
			for (Anadatair value : this.values) {
				int dc = ((AnadatairArray) value).getDimension();
				dimensionChildrens.add(dc);
			}
			Collections.sort(dimensionChildrens);
			dimension += dimensionChildrens.get(0);
		}
		return dimension;
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
	public Object getValue() {
		List<Object> valueList = new ArrayList<Object>();
		for (int i = 0; i<this.values.size(); i++) {
			Object valueI = this.values.get(i).getValue();
			valueList.add(valueI);
		}
		return valueList.toArray();
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
