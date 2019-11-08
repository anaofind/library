package anaofind.lib.anadatatext.ir;

import java.util.*;

import anaofind.lib.anadatext.data.*;

/**
 * ir value object
 * @author anaofind
 */
public class IRObject implements IRValue{

	/**
	 * values
	 */
	private Map<String, IRValue> values = new HashMap<String, IRValue>();
	
	/**
	 * add value
	 * @param attributeName the name of attribute
	 * @param value the value
	 */
	public void addAttribute(String attributeName, IRValue value) {
		Objects.requireNonNull(attributeName);
		Objects.requireNonNull(value);
		this.values.put(attributeName, value);
	}
	
	@Override
	public String toTextIR() {
		return "(object:" + this.attributesToIR() + ")";
	}
	
	/**
	 * get ir attributes
	 * @return the ir attributes
	 */
	private String attributesToIR() {
		String attributesIR = "";
		for (String attributeName: this.values.keySet()) {
			attributesIR += "<" + attributeName + ">" + this.values.get(attributeName).toTextIR();
		}
		return attributesIR;
	}

	@Override
	public DataValue toDataValue() {
		DataObject object = new DataObject();
		for (String attributeName : this.values.keySet()) {
			object.add(attributeName, this.values.get(attributeName).toDataValue());
		}
		return object;
	}
	
}
