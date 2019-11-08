package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.*;

/**
 * ir value array
 * @author anaofind
 */
public class IRArray implements IRValue{

	/**
	 * values
	 */
	private IRValue[] values;
	
	/**
	 * construct
	 * @param irValues the values
	 */
	public IRArray(IRValue...irValues) {
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
		DataValue[] datas = new DataValue[this.values.length];
		for (int i = 0; i<datas.length; i++) {
			datas[i] = this.values[i].toDataValue();
		}
		return new DataArray(datas);
	}

}
