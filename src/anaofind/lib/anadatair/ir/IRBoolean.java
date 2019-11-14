package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.data.*;

/**
 * the ir value boolean
 * @author anaofind
 */
public class IRBoolean implements IRConverter{

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
		DataSettable data = new DataSettable();
		data.addBoolean(this.value);
		return data.toGettable();
	}
}
