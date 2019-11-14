package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.data.*;

/**
 * ir value string
 * @author anaofind
 */
public class IRString implements IRConverter{

	/**
	 * the value
	 */
	private String value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRString(String value) {
		this.value = value;
	}
	
	@Override
	public String toTextIR() {
		return "(string:" + this.value + ")";
	}

	@Override
	public DataValue toDataValue() {
		DataSettable data = new DataSettable();
		data.addString(this.value);
		return data.toGettable();
	}
}
