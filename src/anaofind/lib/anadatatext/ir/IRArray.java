package anaofind.lib.anadatatext.ir;

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
	public String toIR() {
		return "(array:" + this.valuesToIR() + ")";
	}
	
	/**
	 * get ir values
	 * @return the ir values
	 */
	private String valuesToIR() {
		String valuesIR = "";
		for (IRValue value : this.values) {
			valuesIR += value.toIR();
		}
		return valuesIR;
	}

}
