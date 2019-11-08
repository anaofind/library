package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.*;

/**
 * the ir value boolean
 * @author anaofind
 */
public class IRBoolean implements IRValue{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * construct 
	 * @param value the value
	 */
	public IRBoolean(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toTextIR() {
		return "(boolean:" + this.value + ")";
	}

	@Override
	public DataValue toDataValue() {
		return new DataBoolean(this.value);
	}

}
