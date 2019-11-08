package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.DataDouble;
import anaofind.lib.anadatext.data.DataValue;

/**
 * ir value double
 * @author anaofind
 */
public class IRDouble implements IRValue{

	private double value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRDouble(double value) {
		this.value = value;
	}
	
	@Override
	public String toTextIR() {
		return "(double:" + this.value+")";
	}

	@Override
	public DataValue toDataValue() {
		return new DataDouble(this.value);
	}

}
