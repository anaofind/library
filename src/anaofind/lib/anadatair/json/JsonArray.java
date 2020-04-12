package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairArray;
import anaofind.lib.anadatair.util.Printer;

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
	
	/**
	 * only primitive value
	 * @return boolean : true if array contains only primitive value | false else
	 */
	private boolean onlyPrimitiveValue() {
		for (JsonValue value : this.values) {
			if (value instanceof JsonArray || value instanceof JsonObject) {
				return false;
			}
		}
		return true;
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

	@Override
	public Anadatair toAnadatair() {
		AnadatairArray air = new AnadatairArray();
		for (JsonValue value : this.values) {
			air.add(value.toAnadatair());
		}
		return air;
	}

	@Override
	public String prettyString(int tabulation) {
		String tab = Printer.tabulation(tabulation);
		String nextTab = Printer.tabulation(tabulation+1);
		boolean onlyPrimitiveValue = this.onlyPrimitiveValue();
		String array = "[ ";
		if (! onlyPrimitiveValue) {
			array += "\n";
		}
		int size = this.values.size();
		
		for (int i = 0 ; i<size; i++) {
			if (! onlyPrimitiveValue) {
				array += nextTab;
			}
			array += this.values.get(i).prettyString(tabulation+1);
			if (i<size-1) {
				array += ", ";
			}
			if (! onlyPrimitiveValue) {
				array += "\n";
			}
		}
		if (! onlyPrimitiveValue) {
			array += "\n" + tab;
		}
		array += " ]";
		return array;
	}
}
