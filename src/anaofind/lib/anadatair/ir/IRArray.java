package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.data.*;

/**
 * ir value array
 * @author anaofind
 */
public class IRArray implements IRConverter{

	/**
	 * values
	 */
	private IRConverter[] values;
	
	/**
	 * construct
	 * @param irValues the values
	 */
	public IRArray(IRConverter...irValues) {
		this.values = irValues;
	}
	
	@Override
	public String toTextIR() {
		return "(array:" + this.valuesToIR() + ")";
	}
	
	/**
	 * get ir values
	 * @return the ir values
	 */
	private String valuesToIR() {
		String valuesIR = "";
		for (IRConverter value : this.values) {
			valuesIR += value.toTextIR();
		}
		return valuesIR;
	}

	@Override
	public DataValue toDataValue() {
		DataSettable data = new DataSettable();
		for (IRConverter value : this.values) {
			data.addData(value.toDataValue());
		}
		return data.toGettable();
	}
}
