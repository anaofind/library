package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.DataInteger;
import anaofind.lib.anadatext.data.DataValue;

/**
 * ir value integer
 * @author anaofind
 */
public class IRInteger implements IRValue{

	/**
	 * the value
	 */
	private int value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRInteger(int value) {
		this.value = value;
	}
	
	@Override
	public String toTextIR() {
		return "(integer:" + this.value + ")";
	}

	/**
	 * to array of IRInteger
	 * @param values the values
	 * @return the array of IRInteger
	 */
	public static IRInteger[] toArray(int...values) {
		IRInteger[] array = new IRInteger[values.length];
		for (int i = 0; i<array.length; i++) {
			array[i] = new IRInteger(values[i]);
		}
		return array;
	}

	@Override
	public DataValue toDataValue() {
		return new DataInteger(this.value);
	}
	
}
