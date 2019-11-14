package anaofind.lib.anadatair.ir;

import java.util.*;

import anaofind.lib.anadatair.data.*;

/**
 * ir value object
 * @author anaofind
 */
public class IRObject implements IRConverter{

	/**
	 * values
	 */
	private Map<String, IRConverter> values = new HashMap<String, IRConverter>();
	
	/**
	 * add value
	 * @param attributeName the name of attribute
	 * @param value the value
	 */
	public void addAttribute(String attributeName, IRConverter value) {
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
		DataSettable data = new DataSettable();
		for (String attribute : this.values.keySet()) {
			data.addData(attribute, this.values.get(attribute).toDataValue());
		}
		return data.toGettable();
	}
}
