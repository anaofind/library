package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.DataString;
import anaofind.lib.anadatext.data.DataValue;

/**
 * ir value string
 * @author anaofind
 */
public class IRString implements IRValue{

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
		return new DataString(this.value);
	}

}
