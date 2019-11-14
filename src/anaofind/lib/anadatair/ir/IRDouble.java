package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.data.*;

/**
 * ir value double
 * @author anaofind
 */
public class IRDouble implements IRConverter{

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
		DataSettable data = new DataSettable();
		data.addDouble(this.value);
		return data.toGettable();
	}

}
