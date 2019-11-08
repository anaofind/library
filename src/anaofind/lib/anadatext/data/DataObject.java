package anaofind.lib.anadatext.data;

import java.util.*;

/**
 * data object
 * @author anaofind
 */
public class DataObject implements DataValue{

	/**
	 * the values
	 */
	private Map<String, DataValue> values = new HashMap<String, DataValue>();
	
	/**
	 * add a data value
	 * @param attributeName the attribute name
	 * @param value the value
	 */
	public void add(String attributeName, DataValue value) {
		this.values.put(attributeName, value);
	}
	
	@Override
	public DataValue getDataValue(String attributeName) {
		return this.values.get(attributeName);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.values.size() == 0;
	}

	@Override
	public String getContainsString() {
		String description = "Object : {";
		for (String attribute : this.values.keySet()) {
			description += "\n    " + attribute + " -> " + this.values.get(attribute).getContainsString();
		}
		description += "}\n";
		return description;
	}
}
